# AutsProject
To start project:
1) If application.properties doesn't already contain, then put spring.profiles.active=dev in it
2) Create application-dev.properties file in resource directory
3) Fill the following properties:
    spring.application.name=(your_projectName)

    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:(yourDbServerPort)/(yourDatabaseName)
    spring.datasource.username=(yourUserName)
    spring.datasource.password=(yourPassword)
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver