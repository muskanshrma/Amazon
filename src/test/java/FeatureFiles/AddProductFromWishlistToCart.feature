Feature: AddProductFromWishlistToCart

Scenario: Search for a product and Add it to cart
Given User is on Homepage
When User search for product
And User clicks on product and add product to cart
Then Product is added to cart successfully