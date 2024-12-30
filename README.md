### Task Manager with User Authentication  

This project is a full-stack **Task Management Application** built with a focus on efficient task organization, user-specific roles, and secure authentication mechanisms. It includes advanced features such as **JWT-based authentication**, **OAuth2 login**, and user email verification for account activation.  

---

#### 🚀 **Features**  

1. **Task Management**:  
   - **Task Lists**: Organize tasks into manageable lists.  
   - **Tasks**: Add, update, and delete tasks effortlessly.  
   - **Task Status**: Track tasks with statuses like Pending, In Progress, and Completed.  
   - **Task Priority**: Set task priorities to stay organized.  
   - **Due Dates**: Manage deadlines effectively by associating tasks with due dates.  

2. **User Management**:  
   - **User-based Login**: Secure login system with role-based access control.  
   - **Multiple Roles**: Supports Admin, Manager, and User roles for tailored access.  
   - **User Validation**: 
      - Account activation via SMTP email token.  
      - Ensures secure and verified user registrations.  

3. **Authentication & Authorization**:  
   - **Spring Security**: Manages security at every level.  
   - **JWT Authentication**: Stateless and secure token-based authentication.  
   - **OAuth2 Login**: Social login options for enhanced usability.  

4. **Backend Robustness**:  
   - Comprehensive entity validation to ensure data integrity.  
   - Secure API endpoints with role-based access.  
   - Email notifications for account-related events.  

5. **Frontend Integration**:  
   - Intuitive and user-friendly frontend interface for task management.  
   - Real-time interaction with the backend API.  
   - Responsive design for seamless usability across devices.  

---

#### 🛠 **Technologies Used**  

- **Backend**: Spring Boot, Spring Security, JWT, OAuth2, SMTP for email services.  
- **Frontend**: Modern web technologies  Angular
- **Database**: MySQL for data persistence.  
- **Security**:  
   - `BCryptPasswordEncoder` for password hashing.  
   - CSRF protection using `SecurityFilterChain`.  
   - JWT token generation, validation, and expiration handling.  
- **Validation**: Hibernate Validator for entity-level validation.  

---

#### 📌 **How It Works**  

1. **User Registration & Validation**:  
   - New users register with their email and receive a token to validate their account.  
   - Account activation is required before login.  

2. **Login Options**:  
   - **JWT Authentication** for API interactions.  
   - **OAuth2 Login** for seamless integration with social providers.  

3. **Task Management**:  
   - Users can create and manage task lists and tasks.  
   - Task statuses, priorities, and due dates are tracked.  

4. **Role-based Access Control**:  
   - Admins manage all users and tasks.  
   - Managers oversee specific teams or lists.  
   - Users focus on personal task management.  

---

#### 📝 **Learning Outcomes**  

- Building a **full-stack application** with a modular architecture.  
- Implementing advanced **Spring Security** features, including JWT and OAuth2.  
- Developing user validation workflows using **SMTP email services**.  
- Managing roles and permissions in a scalable way.  
- Creating a responsive and interactive **frontend interface** for task management.  

Explore the repository for a detailed implementation and start managing your tasks efficiently today! 😊  
