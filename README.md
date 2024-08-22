
# Setup and Deployment Instructions

## Prerequisites

Ensure you have `git` and `docker` installed on your EC2 instance:

```bash
sudo yum install git -y
sudo yum install docker -y
```

## Docker Setup

Start the Docker service:

```bash
sudo service docker start
```

Add the `ec2-user` to the Docker group:

```bash
sudo usermod -aG docker ec2-user
```

## Git Operations

Pull the latest application code from your repository:

```bash
git pull
```

## MySQL Setup with Docker

1. **Pull the MySQL Image**  
   Fetch the MySQL 8.0 image from Docker Hub:

   ```bash
   docker pull mysql:8.0
   ```

2. **Create a Docker Network**  
   Create a dedicated network for your Spring Boot application and MySQL:

   ```bash
   docker network create sb-mysql-network
   ```

3. **Verify the Network**  
   List all available Docker networks:

   ```bash
   docker network ls
   ```

4. **Run MySQL Container**  
   Launch a MySQL container connected to the network:

   ```bash
   docker run --name mysqldb --network sb-mysql-network -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=database_name -d mysql:8.0
   ```

5. **Inspect the Network**  
   To inspect the details of the created network:

   ```bash
   docker inspect sb-mysql-network
   ```

6. **Execute the MySQL Container**  
   List all running containers:

   ```bash
   docker ps -a
   ```

   Access the MySQL container and connect to the MySQL database:

   ```bash
   docker exec -it <container_id> bash
   mysql -u root -p
   ```

## Application Setup

1. **Pull the Application Code**  
   In another terminal session or tab, pull the latest application code:

   ```bash
   git pull
   ```

2. **Install Maven**  
   Install Maven to build the application:

   ```bash
   sudo yum install maven -y
   ```

3. **Build the Application**  
   Clean and package the application:

   ```bash
   mvn clean package
   ```

4. **Build the Docker Image**  
   Create a Docker image for your Spring Boot application:

   ```bash
   docker build -t sb-mysql-img .
   ```

5. **Run the Application Container**  
   Start your Spring Boot application container and connect it to the MySQL network:

   ```bash
   docker run --name sb-mysql-app --network sb-mysql-network -p 4041:4041 -d sb-mysql-img
   ```

## AWS Security Group Configuration

1. **Edit Inbound Rules**  
   Go to AWS Security Groups and add an inbound rule:

   - **Port:** `4041`

2. **Verify Network Connection**  
   Ensure that both the application and MySQL containers are connected to the `sb-mysql-network`:

   ```bash
   docker inspect sb-mysql-network
   ```
