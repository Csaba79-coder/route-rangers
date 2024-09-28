![route_rangers.png](/src/main/resources/static/images/route_rangers.png)

<p align="center">
    <img src="https://readme-typing-svg.herokuapp.com/?lines=Welcome%20to%20RouteRangersApp;&font=Pacifico&center=true&width=650&height=120&color=58a6ff&vCenter=true&size=45">
</p>

<p align="center" style="font-size: 28px; font-family: 'Pacifico', cursive;">
    Supervisor: Zoltan Sule PhD
</p>
<div style="display:flex; align-items:center; justify-content: center;">
    <div style="margin-right: 20px; font-size: 28px; font-family: 'Pacifico', cursive;">
        <p>Adam Buzas</p>
        <p>Attila David</p>
        <p>Bence Magos</p>
        <p>Csaba Vadasz</p>
    </div>
    <img src="/src/main/resources/static/images/pannon_logo.png" alt="University of Pannonia Logo" style="width:250px;"/>
</div>

# Fullstack Software development project

This project is recommended to be run in a Docker environment for consistency and ease of setup across different systems.
Docker ensures that all dependencies are correctly configured and minimizes the "it works on my machine" problem.

# Project Overview: Domestic Freight Transportation Optimization

The goal of this project is to implement and test route planning algorithms and software aimed at optimizing domestic freight transportation. The primary objective is to minimize costs by reducing the total distance traveled by trucks. The project requires handling multiple trucks, each departing from a base location, delivering to designated destinations, and returning to the base.

Key considerations include selecting the optimal carrier, managing maintenance needs, vehicle availability, and driver scheduling. It is crucial to ensure continuous work for carriers and maximize their working hours. The route planning aims to ensure cost-efficiency, with a focus on minimizing the distance traveled and optimizing transportation costs.

## Backend Repository

This is a backend project. If you need to navigate to the frontend, please visit the [RouteRangers Frontend Repository](https://github.com/lnxx-56/RouteRangers).

## Technologies Used

- **Java Development Kit (JDK)**: 21
- **Spring Boot**: 3
- **Spring Framework**: 6
- **Maven**: Project management
- **MySQL Connector**
- **Spring Data JPA**
- **Spring Web**
- **Spring DevTools**
- **Spring Security**
- **Lombok**
- **Model Mapper**
- **API Contract**: OpenAPI (Swagger Codegen 3)
- **Docker**
- **Docker Compose**
- **MySQL Server**

## Getting Started

### Prerequisites

- JDK 21
- Maven
- MySQL Server

### Clone the Repository

To get started, clone the repository to your local machine:

```bash
git clone https://github.com/Csaba79-coder/route-rangers
cd route-rangers
```

Docker setup (optional) - see the Docker Configuration section below.

Do not forget, Docker must be installed on your machine. Docker Desktop is recommended for Windows and Mac users.

## Git Configuration for Cross-Platform Development

When working on a project that may involve different operating systems (such as Windows and Linux), it's essential to ensure that line endings are handled consistently. This is particularly important in environments using Docker, where Linux line endings (`LF`) are required. Below are some useful Git commands to help you configure your environment correctly.

### 1. Set Line Ending Configuration

To automatically handle line endings, run the following command:

```bash
git config --global core.autocrlf input
```
or 
```bash
git config --global core.autocrlf true
```
### 2. Remove Files from Git Tracking - if necessary
`git rm --cached -r .`
### 3. Reset Local Changes - if necessary
`git reset --hard`

## Docker Configuration

This project uses Docker for containerization. Below are the key environment variables for the MySQL database and the application configuration.

## Docker & Project Setup Information

| Name                    | Value             | Remark                                                                 |
|-------------------------|-------------------|------------------------------------------------------------------------|
| Spring application name | route-rangers     | The default name of the Spring application.                            |
| Application (host) port | 8080              | Port for accessing the app from the host.                              |
| Docker Container name   | app               | The name specified for the container in the `docker-compose.yml` file. |
| Docker container port   | 8080              | Port the app listens on inside the container.                          |
| JAVA_HOME               | /opt/java/openjdk | Default location of the JDK in the Docker container.                   |
| MySQL Server port       | 3306              | MySQL database default port.                                           |
| MYSQL_DATABASE          | route_rangers     | Name of the MySQL database used by the app.                            |
| MYSQL_ROOT_PASSWORD     | verysecret        | Simple; change for production!                                         |
| MYSQL_USER              | myuser            | The default database user.                                             |
| MYSQL_PASSWORD          | secret            | Simple; change for production!                                         |

<p style="color: red;"><strong>Security Note:</strong> For security reasons, do not use the example passwords in a production environment. Make sure to set strong, unique passwords.</p>

### Docker Commands
Recommended Docker commands for building and running the application:

```bash
docker build -t app .
docker run -d -p 8080:8080 --name app app
```

### Docker Compose

To run this application, you can use the following `docker-compose.yml` file:

```yaml
services:
  app:
    build:
      context: .
    container_name: app
    depends_on:
      - mysql
    restart: unless-stopped
    environment:
      - JAVA_HOME=/opt/java/openjdk
    ports:
      - '8080:8080'
    volumes:
      - ./:/app

  mysql:
    image: 'mysql:latest'
    environment:
      - 'MYSQL_DATABASE=route_rangers'
      - 'MYSQL_PASSWORD=secret'
      - 'MYSQL_ROOT_PASSWORD=verysecret'
      - 'MYSQL_USER=myuser'
    ports:
      - '3306'
```

```bash
docker-compose up
```
## Testing

This project utilizes unit testing to ensure code quality and functionality. The following testing dependencies are included:

- **JUnit 5**: A popular testing framework for Java that supports writing and executing tests.
- **Spring Test**: Provides integration testing support for Spring applications.

You can run the tests using your IDE or by executing the following command in your project directory:

```bash
./mvnw test
```

## License

This project is licensed under the MIT License. The MIT License permits the following:

- You can freely copy, modify, distribute, and use the project for any purpose, including commercial purposes.
- The original author's (or authors') name and the license terms must be included in all copies or substantial portions of the software.

For more details regarding the use of this project, please refer to the [LICENSE](LICENSE) file.

## Debugging Wisdom

<p align="center">
  <img src="/src/main/resources/static/images/debugging.png" alt="Debugging" />
</p>
