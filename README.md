VM args:
1. digitailor api gateway
-DPORT=8011
-DAPPLICATION_NAME=digi-tailor-api-gateway
-DLOG_FILE_NAME=digi-tailor-api-gateway
-DEUREKA_SERVER=http://localhost:8010/eureka/

2. digitailor customer api
-DPORT=8022
-DAPPLICATION_NAME=digi-tailor-customer-api
-DLOG_FILE_NAME=digi-tailor-customer-api
-DEUREKA_SERVER=http://localhost:8010/eureka/
-DPRIMARY_DATABASE_URL=
-DPRIMARY_DATABASE_USERNAME=
-DPRIMARY_DATABASE_PASSWORD=

3. digitailot dress api
-DPORT=8023
-DAPPLICATION_NAME=digi-tailor-dress-api
-DLOG_FILE_NAME=digi-tailor-dress-api
-DEUREKA_SERVER=http://localhost:8010/eureka/
-DPRIMARY_DATABASE_URL=
-DPRIMARY_DATABASE_USERNAME=
-DPRIMARY_DATABASE_PASSWORD=

4. digitailotr eureka server
-DPORT=8010
-DAPPLICATION_NAME=digi-tailor-eureka-server
-DLOG_FILE_NAME=digi-tailor-eureka-server

5. digitialor help api
-DPORT=8021
-DAPPLICATION_NAME=digi-tailor-help-api
-DLOG_FILE_NAME=digi-tailor-help-api
-DEUREKA_SERVER=http://localhost:8010/eureka/


7. digi tailor invoice api
-DPORT=8025
-DAPPLICATION_NAME=digi-tailor-invoice-api
-DLOG_FILE_NAME=digi-tailor-invoice-api
-DEUREKA_SERVER=http://localhost:8010/eureka/

8. digitailor measurement api
-DPORT=8024
-DAPPLICATION_NAME=digi-tailor-measurement-api
-DLOG_FILE_NAME=digi-tailor-measurement-api
-DEUREKA_SERVER=http://localhost:8010/eureka/

9. digi tailor shop api
-DPORT=8026
-DAPPLICATION_NAME=digi-tailor-shop-api
-DLOG_FILE_NAME=digi-tailor-shop-api
-DEUREKA_SERVER=http://localhost:8010/eureka/
-DPRIMARY_DATABASE_URL=
-DPRIMARY_DATABASE_USERNAME=
-DPRIMARY_DATABASE_PASSWORD=

10. digitailar user api
-DPORT=8020
-DAPPLICATION_NAME=digi-tailor-user-api
-DLOG_FILE_NAME=digi-tailor-user-api
-DEUREKA_SERVER=http://localhost:8010/eureka/
-DPRIMARY_DATABASE_URL=
-DPRIMARY_DATABASE_USERNAME=
-DPRIMARY_DATABASE_PASSWORD=


swagger urls

digi-tailor-shop-api: http://localhost:8026/swagger-ui/index.html
digi-tailor-user-api: http://localhost:8020/swagger-ui/index.html
digi-tailor-customer-api: http://localhost:8022/swagger-ui/index.html
digi-tailor-dress-api: http://localhost:8023/swagger-ui/index.html
