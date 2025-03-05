package org.example.springbootmysql.model;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {

    private List<Long> productIds;
}
