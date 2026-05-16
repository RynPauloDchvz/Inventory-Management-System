System Operation Guide
Prepared by: Mr. Demetrial

1. Adding Products
Navigate to the Product Entry section on the main dashboard. Enter the item name, initial stock count, and price. Submit the form to save the item to the database. It will immediately appear on the inventory list.

2. Updating Current Stock
If there are new deliveries from suppliers, locate the item on the main table. Go to the Tracking column, input the new total stock count, and press the Update Total button to refresh the database records.

3. Deducting Sales
To record a transaction, find the specific item on the list. Under the Sales column, type the exact number of items sold to the customer. Click Deduct Sale. The system will automatically subtract this from your current stock.

4. Monitoring Inventory Levels
Always check the status color of the items. The system is programmed to change the stock indicator to red if the quantity drops to 10 or below. This serves as a warning to restock the item to avoid zero inventory.


# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/4.0.6/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/4.0.6/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/4.0.6/reference/web/servlet.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/4.0.6/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Thymeleaf](https://docs.spring.io/spring-boot/4.0.6/reference/web/servlet.html#web.servlet.spring-mvc.template-engines)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

