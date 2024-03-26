package com.program.orderservice.OrderService;

import com.program.orderservice.Config.WebClientConfig;
import com.program.orderservice.OrderDto.InventoryResponse;
import com.program.orderservice.OrderDto.OrderLineItemsDto;
import com.program.orderservice.OrderDto.OrderRequest;
import com.program.orderservice.OrderModel.Order;
import com.program.orderservice.OrderModel.OrderLineItems;
import com.program.orderservice.OrderRepository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private final WebClient.Builder webClientBuilder;
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
    List<OrderLineItems> orderLineItems    = orderRequest.getOrderLineItemsDtoList().stream().map(orderLineItemsDto -> mapToOrder(orderLineItemsDto)).toList();
     order.setOrderLineItemsList(orderLineItems);
     List<String> skucodes = order.getOrderLineItemsList().stream().map(orderLineItem->orderLineItem.getSkuCode()).toList();

     // call inventory service and place order if product is in stock
//   InventoryResponse[] responses= webClient.get().uri("http://localhost:8082/api/inventory", uriBuilder -> uriBuilder.queryParam("skucode", skucodes).build()).
//                     retrieve().
//                      bodyToMono(InventoryResponse[].class).
//                      block();
        InventoryResponse[] responses = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skucodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

  boolean allProductsInStock= Arrays.stream(responses).allMatch(response-> response.isInStock());
// boolean[] res =  response.stream().filter(invetory -> inventory.isInStock = true).map(inventory->incentory.isInStock)
   if(allProductsInStock) {
       orderRepo.save(order);
   }
   else {
       throw new IllegalArgumentException("Product is not in stock");
   }
    }

    private OrderLineItems mapToOrder(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems item = new OrderLineItems();
        item.setId(orderLineItemsDto.getId());
        item.setQuantity(orderLineItemsDto.getQuantity());
        item.setPrice(orderLineItemsDto.getPrice());
        item.setSkuCode(orderLineItemsDto.getSkuCode());
        return item;
    }
}
