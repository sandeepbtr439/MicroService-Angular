package com.program.orderservice.OrderDto;

import com.program.orderservice.OrderModel.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderRequest {
    private List<OrderLineItemsDto> orderLineItemsDtoList;
}

