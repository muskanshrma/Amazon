package Enums;

public enum SearchProduct_Amazon {
    SearchProduct ("OnePlus 10T 5G (Moonstone Black, 16GB RAM, 256GB Storage)"),
    product_quantity ( "4");

    private String name;
    SearchProduct_Amazon(String name) {
        this.name = name;
    }

    public String getResourcesName() {
        return name;
    }
}