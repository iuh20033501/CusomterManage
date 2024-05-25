# Sử dụng image chứa JDK 17
FROM openjdk:17-alpine

# Thiết lập thư mục làm việc
WORKDIR /app

# Sao chép file JAR đã đóng gói từ thư mục target vào thư mục /app trong container
COPY target/AnalysisService-0.0.1-SNAPSHOT.jar /app/AnalysisService-0.0.1-SNAPSHOT.jar

# Expose cổng 8084
EXPOSE 8084

# Khởi chạy ứng dụng khi container được khởi động
ENTRYPOINT ["java", "-jar", "AnalysisService-0.0.1-SNAPSHOT.jar"]
