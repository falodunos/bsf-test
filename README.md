## PROBLEM STATEMENT
Rules:

    1. One supervisor can have multiple subordinates.
    2. Any subordinate can be also a supervisor
    3. Number of levels in the hierarchy is infinity
    4. Service should be authenticated by JWT,
    6. Data about logged in user should be returned by endpoint /me.
    6. I want to have paginated list of employees,
    7. Possibility to add new employee to the hierarchy
    8. move employee in the hierarchy should be possible
    9. in case of deleting supervisor, exception should be thrown if it has children
    10. expecting to have flatten json structure for visualization the tree by react-sortable-plugin (https://github.com/frontend-collective/react-sortable-tree)

We are normally using PostgreSQL or MySQL for development. Although we suggest using H2 in memory DB for this task, to make it easier to run.
Add readme.md file to your repository with run instructions

## API SOLUTION AND DOCUMENTATIONS

IDE: IntelliJ
Target Java Version: `Oracle Open JDK 17.0.2`
DBMS: `MySQL`
Database: `CREATE DATABASE bsf_db;`
Username / Password: `root / password`
Postman Documentation: `https://github.com/falodunos/bsf-test/blob/development/API%20DOCUMENTATION/BSF%20API.postman_collection.json`
#
`There are two implementations included in this project:`
- The Authorization Server:
  - File path on the repository: `https://github.com/falodunos/bsf-test/blob/development/JAR%20FILES/bsf-authorization-server-0.1.0-SNAPSHOT.jar`
  - RUN COMMAND: 
    - cd `'path to jar file path'`
    - Enter `java -jar bsf-authorization-server-0.1.0-SNAPSHOT.jar` then press enter key on the keyboard
- The Resource Server
  - File path on the repository: `https://github.com/falodunos/bsf-test/blob/development/JAR%20FILES/bsf-resource-server-1.0.jar`
  - RUN COMMAND: 
    - `'path to jar file path'`
    - Enter `java -jar bsf-resource-server-1.0.jar` and press enter key on the keyboard

## AVAILABLE ENDPOINTS
- New Access Token:
  - URI: `http://localhost:8081/bsf/auth/token`
  - DESCRIPTION: `Helps to generate JWT authentication token by using username and password`
    `{ "username":"olugbenga@bsf.com", "password":"123"}`
- Access Token via Refresh Token: 
  - URI: `http://localhost:8081/bsf/auth/token/refresh`
  - DESCRIPTION: `Help to generate Access Token by using an existing refresh token`
- Logged In User Details:
  - URI: `http://localhost:8081/api/bsf/me`
  - DESCRIPTION: `Get the details of logged in user.`
    `You must include a valid authentication token when isueing request to this endpoint`
- Create Employee:
  - URI: `http://localhost:8081/api/bsf/employee/create`
  - DESCRIPTION: `Help to create a new employee`
- Add Employee To Level:
  - URI: `http://localhost:8081/api/bsf/employee/addLevel`
  - DESCRIPTION: `Add an employee to a particular level in the hierarchical tree`
  - Delete Employee:
    - URI: `http://localhost:8081/api/bsf/employee/4`
      - DESCRIPTION: `Delete or remove a certail employee from the database table`
- Paginated List:
  - URI: `http://localhost:8081/api/bsf/employees?page=0&size=2`
  - DESCRIPTION: `Returns a paginated list of employees`