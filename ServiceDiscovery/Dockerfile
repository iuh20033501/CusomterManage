# Sử dụng image chứa JDK 17
FROM openjdk:17-alpine

# Thiết lập thư mục làm việc
WORKDIR /app

# Sao chép file JAR đã đóng gói từ thư mục target vào thư mục /app trong container
COPY target/ServiceDiscovery-0.0.1-SNAPSHOT.jar /app/ServiceDiscovery-0.0.1-SNAPSHOT.jar

# Expose cổng 3000
EXPOSE 3000

# Khởi chạy ứng dụng khi container được khởi động
ENTRYPOINT ["java", "-jar", "ServiceDiscovery-0.0.1-SNAPSHOT.jar"]
