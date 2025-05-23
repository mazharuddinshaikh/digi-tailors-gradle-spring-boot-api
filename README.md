VM args:
1. digi-tailor api gateway
   -DPORT=8011
   -DAPPLICATION_NAME=digi-tailor-api-gateway
   -DLOG_FILE_NAME=digi-tailor-api-gateway
   -DEUREKA_SERVER=http://localhost:8010/eureka/

2. digi-tailor customer api
   -DPORT=8022
   -DAPPLICATION_NAME=digi-tailor-customer-api
   -DLOG_FILE_NAME=digi-tailor-customer-api
   -DEUREKA_SERVER=http://localhost:8010/eureka/
   -DPRIMARY_DATABASE_URL=
   -DPRIMARY_DATABASE_USERNAME=
   -DPRIMARY_DATABASE_PASSWORD=

3. digi-tailor dress api
   -DPORT=8023
   -DAPPLICATION_NAME=digi-tailor-dress-api
   -DLOG_FILE_NAME=digi-tailor-dress-api
   -DEUREKA_SERVER=http://localhost:8010/eureka/
   -DPRIMARY_DATABASE_URL=
   -DPRIMARY_DATABASE_USERNAME=
   -DPRIMARY_DATABASE_PASSWORD=

4. digi-tailor eureka server
   -DPORT=8010
   -DAPPLICATION_NAME=digi-tailor-eureka-server
   -DLOG_FILE_NAME=digi-tailor-eureka-server

5. digi-tailor help api
   -DPORT=8021
   -DAPPLICATION_NAME=digi-tailor-help-api
   -DLOG_FILE_NAME=digi-tailor-help-api
   -DEUREKA_SERVER=http://localhost:8010/eureka/

6. digi-tailor invoice api
   -DPORT=8025
   -DAPPLICATION_NAME=digi-tailor-invoice-api
   -DLOG_FILE_NAME=digi-tailor-invoice-api
   -DEUREKA_SERVER=http://localhost:8010/eureka/

7. digi-tailor measurement api
   -DPORT=8024
   -DAPPLICATION_NAME=digi-tailor-measurement-api
   -DLOG_FILE_NAME=digi-tailor-measurement-api
   -DEUREKA_SERVER=http://localhost:8010/eureka/

8. digi-tailor shop api
   -DPORT=8026
   -DAPPLICATION_NAME=digi-tailor-shop-api
   -DLOG_FILE_NAME=digi-tailor-shop-api
   -DEUREKA_SERVER=http://localhost:8010/eureka/
   -DPRIMARY_DATABASE_URL=
   -DPRIMARY_DATABASE_USERNAME=
   -DPRIMARY_DATABASE_PASSWORD=

9. digi-tailor user api
   -DPORT=8020
   -DAPPLICATION_NAME=digi-tailor-user-api
   -DLOG_FILE_NAME=digi-tailor-user-api
   -DEUREKA_SERVER=http://localhost:8010/eureka/
   -DPRIMARY_DATABASE_URL=
   -DPRIMARY_DATABASE_USERNAME=
   -DPRIMARY_DATABASE_PASSWORD=

10. digi-tailor main api
    -DPORT=8030
    -DAPPLICATION_NAME=digi-tailor-main-api
    -DLOG_FILE_NAME=digi-tailor-main-api
    -DEUREKA_SERVER=http://localhost:8010/eureka/
    -DPRIMARY_DATABASE_URL=jdbc:mysql://localhost:3306/demo_tailor_shop
    -DPRIMARY_DATABASE_USERNAME=root
    -DPRIMARY_DATABASE_PASSWORD=mazhar

swagger urls:
1. digi-tailor-shop-api: http://localhost:8026/swagger-ui/index.html
2. digi-tailor-user-api: http://localhost:8020/swagger-ui/index.html
3. digi-tailor-customer-api: http://localhost:8022/swagger-ui/index.html
4. digi-tailor-dress-api: http://localhost:8023/swagger-ui/index.html
5. digi-tailor-main-api: http://localhost:8030/swagger-ui/index.html
