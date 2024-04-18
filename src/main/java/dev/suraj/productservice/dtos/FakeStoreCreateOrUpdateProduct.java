package dev.suraj.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCreateOrUpdateProduct {
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}
