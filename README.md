# vehiclemanagement

Import this project in eclipse/sts or any other compatible IDE
import as maven project.

change the database credentials in application.properties with your mysql database credentials
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD


GET : http://localhost:8282/secure/car/{carid}
    AUTH -> Basic Auth Username : ecs Password : ecs
    SAMPLE RESPONSE ->  
      {
      "message": null,
      "status": true,
      "result": {
          "id": "93c56f20-bd46-4ac3-934e-45d61ba5fcd9",
          "make": "maruti",
          "model": "baleno",
          "colour": "white",
          "year": 2019,
          "soundLikeModel": "baleno, balena, boloney, ballina, bellino"
      },
      "data": null,
      "errorArguments": []
    }

POST : http://localhost:8282/secure/car
       AUTH -> Basic Auth Username : ecs Password : ecs
       REQUEST -> 
                {
                "make" : "maruti",
                "model" : "baleno",
                "colour" : "white",
                "year" : "2019"
              }
      
PUT : http://localhost:8282/secure/car/{carid}
      AUTH -> Basic Auth Username : ecs Password : ecs
      REQUEST -> 
            {
        "make" : "maruti",
        "model" : "baleno",
        "colour" : "white",
        "year" : "2019"
      }
      
      
DELETE : http://localhost:8282/secure/car/{carid}
       AUTH -> Basic Auth Username : ecs Password : ecs
