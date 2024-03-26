package com.inventory.inventoryservice.Service;

import com.inventory.inventoryservice.Dto.InventoryResponse;
import com.inventory.inventoryservice.Model.Inventory;
import com.inventory.inventoryservice.Repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
   @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skucode) {
     return inventoryRepository.findByskucodeIn(skucode).stream().map(inventory-> mapToResponse(inventory)).toList();

    }

    private InventoryResponse mapToResponse(Inventory inventory) {

       return InventoryResponse.builder().skucode(inventory.getSkucode()).isInStock(inventory.getQuantity()>0).build();
    }
}
