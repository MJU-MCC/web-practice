# Use the official Node.js LTS image as a base image
FROM node:14.17.6-alpine

# Set the working directory to /app
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY ./package.json ./package-lock.json ./

# Install dependencies
RUN npm install
COPY ./ .
# Build the React app
RUN npm run build

# Expose port 3000
EXPOSE 3000

# Command to run the React app
CMD ["npm", "start"]
