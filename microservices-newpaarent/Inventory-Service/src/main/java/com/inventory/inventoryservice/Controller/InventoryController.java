package com.inventory.inventoryservice.Controller;

import com.inventory.inventoryservice.Dto.InventoryResponse;
import com.inventory.inventoryservice.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;


//    public boolean isInStock(@PathVariable("skucode") String skucode)
//    it will only take one sku code, but if a customer buys multiple products we will have multiple skucode.
//    url would then look like http://localhost:8082/iphone13, pnjfdn13,samsung 17,....
//    which is inconvinient
//    therefore instead we will use requestparameter which allows us to have following url
//    http://localhost:8082/api/inventory?sku-code=ipone13&skucode=samsung&skucode=ejiwjw18 and so on{
//    public boolean isInStock(@PathVariable("skucode") String skucode)
@GetMapping
@ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skucode) {
       return inventoryService.isInStock(skucode);

    }

}
