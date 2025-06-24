package edu.grsu.practice.practice.integration.price;

import edu.grsu.practice.practice.model.Price;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "price-service", url = "http://localhost:8081")
public interface PriceServiceClient {
    @GetMapping("/api/price/{from}/{where}")
    Price getPrice(@PathVariable("from") String from, @PathVariable("where") String where);
}
