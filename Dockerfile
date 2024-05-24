# Dockerfile  
FROM node:8  
WORKDIR /app
COPY . . 
EXPOSE 8081  
CMD node index.js