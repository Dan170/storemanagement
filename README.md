# Store Management
This is a Store Management project created by Daniel Popescu. The project has the following requirements:

- Create an API that acts as a store management tool
- Create a Github profile if you don't have one
- Use git in a verbose manner, push even if you wrote only one class
- Create a Java, maven based project, Springboot for the web part
- No front-end, you can focus on backend, no need to overcomplicate the structure
- Implement basic functions, for example: add-product, find-product, change-price or others
- Optional: Implement a basic authentication mechanism and role based endpoint access
- Design error mechanism and handling plus logging
- Write unit tests, at least for one class
- Use Java 9+ features
- Add a small Readme to document the project

The project is based on Java 17, Maven 3.3.2, SpringBoot 3.3.4. The project has an in memory H2 Database created when 
the application is run. 

Run the clean install configuration, then run the StoreManagementApplications configuration, the H2 console can be 
accessed here http://localhost:8080/h2-console, with username **store_manager** and password **store_password** .

The app requires basic authentication for accessing the products. There are 2 pre-configured in memory users:

- manager with password: password
- employee with password: password

The root path for requests is: http://localhost:8080/products. There is the possibility to access this welcome 
endpoint without authenticating first http://localhost:8080/products/welcome.

The DB is pre-populated with one demo entry. The payload for adding new products is:

```
{
"name": "Football",
"currentPrice": 18.0,
"description": "Sports item",
"quantity": 10,
"weight": 400.0
}
```
