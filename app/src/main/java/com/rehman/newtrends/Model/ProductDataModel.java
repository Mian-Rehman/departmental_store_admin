package com.rehman.newtrends.Model;

public class ProductDataModel
{
    String productCategory;
    String productTitle;
    String productDescription;
    String productPrice;
    String productDiscountPrice;
    String productColor;
    String productKey;
    String productCoverImage;

    public ProductDataModel(String productCategory, String productTitle, String productDescription, String productPrice, String productDiscountPrice, String productColor, String productKey, String productCoverImage) {
        this.productCategory = productCategory;
        this.productTitle = productTitle;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productDiscountPrice = productDiscountPrice;
        this.productColor = productColor;
        this.productKey = productKey;
        this.productCoverImage = productCoverImage;
    }

    public ProductDataModel() {
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDiscountPrice() {
        return productDiscountPrice;
    }

    public void setProductDiscountPrice(String productDiscountPrice) {
        this.productDiscountPrice = productDiscountPrice;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getProductCoverImage() {
        return productCoverImage;
    }

    public void setProductCoverImage(String productCoverImage) {
        this.productCoverImage = productCoverImage;
    }
}
