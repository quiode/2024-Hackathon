FROM node:20-alpine AS build

WORKDIR /app

RUN npm install -g pnpm

RUN npm install -g @angular/cli

COPY package.json pnpm-lock.yaml ./

RUN pnpm install

COPY . .

RUN pnpm build

FROM nginx:alpine

COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html

EXPOSE 80