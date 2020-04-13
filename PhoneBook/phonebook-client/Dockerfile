FROM node:12.4.0-alpine as frontend-build
WORKDIR /client/phonebook

COPY package.json package-lock.json ./
RUN npm install

COPY . ./
RUN npm run build

FROM nginx:1.12-alpine
COPY --from=frontend-build /client/phonebook/build /usr/share/nginx/html

EXPOSE 80

ENTRYPOINT ["nginx","-g","daemon off;"]
