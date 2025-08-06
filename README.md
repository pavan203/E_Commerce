# E-Commerce Application

This project is a full-stack e-commerce web application built with:

- **Backend:** Spring Boot  
- **Frontend:** React.js  
- **Database:** PostgreSQL  

---

## Project Structure

/WebApp # Spring Boot backend
/ecom-frontend-3 # React frontend

---

## Backend (Spring Boot)

- Built with Java and Spring Boot framework.  
- Provides RESTful APIs for product management.  
- Connects to PostgreSQL database.  
- Handles image upload for products.  

### How to run backend

1. Set up PostgreSQL and create a database.  
2. Configure `application.properties` with your DB credentials.  
3. Build and run the backend:

./mvnw spring-boot:run
Frontend (React)
Developed using React.js.

Provides UI to manage products (view, edit, add).

Communicates with backend API.

How to run frontend

cd ecom-frontend-3
npm install
npm start
Frontend runs on http://localhost:5173 (default Vite React port).

Database (PostgreSQL)
Used for persisting product data.

Ensure PostgreSQL server is running and accessible.

Notes
CORS is enabled on backend to allow frontend communication.

Ensure backend runs on port 8080 and frontend on 5173 for smooth interaction.

---

git add README.md
git commit -m "Add README for Spring Boot + React + PostgreSQL app"
git push
