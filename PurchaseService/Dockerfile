# Sử dụng image chứa JDK 17
FROM openjdk:17-alpine

# Thiết lập thư mục làm việc
WORKDIR /app

# Sao chép file JAR đã đóng gói từ thư mục target vào thư mục /app trong container
COPY target/PurchaseService-0.0.1-SNAPSHOT.jar /app/PurchaseService-0.0.1-SNAPSHOT.jar

# Expose cổng 8083
EXPOSE 8083

# Khởi chạy ứng dụng khi container được khởi động
ENTRYPOINT ["java", "-jar", "PurchaseService-0.0.1-SNAPSHOT.jar"]
