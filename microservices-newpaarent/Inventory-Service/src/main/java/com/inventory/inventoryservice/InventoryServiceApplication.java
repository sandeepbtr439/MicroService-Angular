package com.inventory.inventoryservice;

import com.inventory.inventoryservice.Model.Inventory;
import com.inventory.inventoryservice.Repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkucode("iphone13");
			inventory.setQuantity(15);

			Inventory inventory1 = new Inventory();
			inventory1.setSkucode("iphone14");
			inventory1.setQuantity(0);
			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);


		};
	}
}
