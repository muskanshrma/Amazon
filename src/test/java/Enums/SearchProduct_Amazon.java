package Enums;

public enum SearchProduct_Amazon {
    SearchProduct ("OnePlus Nord CE 2 5G (Bahamas Blue, 8GB RAM, 128GB Storage)"),
    product_quantity ( "4");

    private String name;
    SearchProduct_Amazon(String name) {
        this.name = name;
    }

    public String getResourcesName() {
        return name;
    }
}