VM args:
1. digi-tailor api gateway
   -DPORT=8011
   -DAPPLICATION_NAME=digi-tailor-api-gateway
   -DLOG_FILE_NAME=digi-tailor-api-gateway
   -DEUREKA_SERVER=http://localhost:8010/eureka/

2. digi-tailor eureka server
   -DPORT=8010
   -DAPPLICATION_NAME=digi-tailor-eureka-server
   -DLOG_FILE_NAME=digi-tailor-eureka-server

3. digi-tailor main api
    -DPORT=8030
    -DAPPLICATION_NAME=digi-tailor-main-api
    -DLOG_FILE_NAME=digi-tailor-main-api
    -DEUREKA_SERVER=http://localhost:8010/eureka/
    -DPRIMARY_DATABASE_URL=jdbc:mysql://localhost:3306/demo_tailor_shop
    -DPRIMARY_DATABASE_USERNAME=root
    -DPRIMARY_DATABASE_PASSWORD=mazhar

swagger urls:
1. digi-tailor-main-api: http://localhost:8030/swagger-ui/index.html
