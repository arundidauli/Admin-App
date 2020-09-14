package com.wingshield.technologies.adminapp.category;

public class ProductCategory {
    private String Category_id;
    private String Category_image;
    private String Category_name;

    public ProductCategory() {
    }

    public String getCategory_id() {
        return Category_id;
    }

    public void setCategory_id(String category_id) {
        Category_id = category_id;
    }

    public String getCategory_image() {
        return Category_image;
    }

    public void setCategory_image(String category_image) {
        Category_image = category_image;
    }

    public String getCategory_name() {
        return Category_name;
    }

    public void setCategory_name(String category_name) {
        Category_name = category_name;
    }
}
