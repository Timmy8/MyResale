## Instruction for using 'My Resale' created by 'Timmy8'

### Information about application creation
- My Resale web application was created using ***Spring Boot, Spring MVC, Spring Security, Spring Data***.

- Also, ***Spring starter data JPA, mysql connector, spring starter validation*** used for databases.

- Libraries for development ***lombok*** and ***devtools***.

- The views were created using ***thymeleaf*** and ***thymeleaf extras spring security***.

- Working with ***official Telegram API*** for notification bot.

- ***Spring docker compose*** for docker.

### information about the start of the project (Application properties)

If you ***don't have*** your own database, 
uncomment ***docker compose dependency*** in ***pom.xml*** file.

If ***you have*** your own database, change **Database Properties** to yours:
>spring.datasource.url=**'your database address:port'**
> 
> spring.datasource.username=**'your database username'**
> 
> spring.datasource.password=**'your database password'**

For adding admin user, change **Admin Data**:
>security.admin.login=**'your login'**
> 
> security.admin.password=**'your password'**
> 
> security.admin.email=**'your email'**

If you want to add test data to the site, change **starter value**:
> security.starter.data.setup=**true**


To add your own telegram bot, change **Telegram Properties**:
> telegram.bot.token=**'your telegram bot token'**
> 
> telegram.bot.name=**'your telegram bot name'**

If you want to see all debug processes, **remove the # sign** in **debug** section
> logging.level.web=debug