
##리액트
#FROM node:14.17.6-alpine
#WORKDIR /app
#COPY ./package.json ./package-lock.json ./
#RUN npm install
#COPY ./ .
#RUN npm run build
#EXPOSE 3000
#CMD ["npm", "start"]

# 리액트
FROM node:14.17.6-alpine
WORKDIR /app
# 로컬의 package.json 및 package-lock.json을 이미지 내 /app으로 복사
COPY ./package.json .
COPY ./package-lock.json .
RUN npm install
# 로컬의 모든 파일을 이미지 내 /app으로 복사
COPY ./ .
RUN npm run build
EXPOSE 3000
CMD ["npm", "start"]


