# git-integration
Web application that provides information about git repositories, making it possible to navigate through the commits, filtering them by repository, author, branch, etc.

# Instalation
This is a springboot application, so you only need to run the war file by entering its settings via the command line.
Example: To configure the application port, just run: java -jar git-integration.war --server.port=2114 

## Configurable parameters:

### server.port
Configure the application's port

### repositories.path
Physical location where the bare git repositories are located

### gitlab.url
If the gitlab address is configured with the location where the repositories are located, clicking a commit will open the git lab page with the information of this commit.

### default.queries.limit
Sets the default limit of results brought in queries

### refresh.expression
The cron expression used to perform the automatic data integration.
Example: 0 30 * * * ?

### Database configuration parameters:
- spring.datasource.driver-class-name
- spring.datasource.url
- spring.datasource.username
- spring.datasource.password
- spring.jpa.database-platform

You can find an example of setting these parameters on the page: https://github.com/emanuelcruzrodrigues/git-integration/blob/master/src/main/resources/application.properties
