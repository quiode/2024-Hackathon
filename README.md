# 2024 Hackathon

A Repository for all the code for the [2024 Hackathon](https://viscon.vis.ethz.ch/2024/hackathon).

## Used Tools

### Frontend

- [Angular](https://angular.dev/)
- [Tailwind](https://tailwindcss.com/)
- [RxJS](https://rxjs.dev/)

### Backend

- [Spring Boot](https://spring.io/projects/spring-boot)
- [PostgreSQL](https://www.postgresql.org/)

## Dev Setup

### Recommended IDE's

- Frontend: [VS-Code](https://code.visualstudio.com/)
- Backend: [IntelliJ](https://www.jetbrains.com/idea/)

### VS-Code

Open the `dev/vscode.code-workspace` file to load the workspace.

Install all recommended extensions.

### Frontend

Install [pnpm](https://pnpm.io/installation).

Run `pnpm install` to install all dependencies.

Execute `pnpm start` to start the dev server.

### Backend

Use Java Version `21`.

Install the dependencies by going inside the `backend` folder and start the `mvnw` executable with the argument `install` like this: `./mvnw install`.

Start the backend by executing the main function inside the `BackendApplication.java` class.

### Docker (DB)

Install [Docker](https://www.docker.com/).

Inside the dev folder execute `docker compose up`. This will start the PostgreSQL database, which is reachable
at port *5432* with username *admin* and password *1234*.

You can also type `docker compose up -d` to start the database in the background.

A folder, called `db` inside dev will be created which contains the database data. To clear the database, delete this folder.

## Building

// TODO

## Code Style

So far only one style guide: Make as much **immutable** as possible!
