# Use OpenJDK 11 as the base image
FROM openjdk:11

# Copy the JAR file into the container
COPY target/chatbot*.jar /usr/src/chatbot.jar

# Copy the application.properties file into a specific configuration path
COPY src/main/resources/application.properties /opt/conf/application.properties

# Run the application with the custom configuration path
CMD ["java", "-jar", "/usr/src/chatbot.jar", "--spring.config.location=file:/opt/conf/application.properties"]
