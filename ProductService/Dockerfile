# Sử dụng image chứa JDK 17
FROM openjdk:17-alpine

# Thiết lập thư mục làm việc
WORKDIR /app

# Sao chép file JAR đã đóng gói từ thư mục target vào thư mục /app trong container
COPY target/ProductService-0.0.1-SNAPSHOT.jar /app/ProductService-0.0.1-SNAPSHOT.jar

# Expose cổng 8082
EXPOSE 8082

# Khởi chạy ứng dụng khi container được khởi động
ENTRYPOINT ["java", "-jar", "ProductService-0.0.1-SNAPSHOT.jar"]
