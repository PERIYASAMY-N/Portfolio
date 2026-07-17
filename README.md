# 🚀 Portfolio CMS

A modern, dynamic portfolio website with a powerful Content Management System. Built with Spring Boot backend and vanilla JavaScript frontend.

## ✨ Features

- 🎨 **Dynamic Content Management** - Update your portfolio in real-time
- 🔐 **Secure Admin Panel** - JWT-based authentication
- 📱 **Fully Responsive** - Works on all devices
- 🌓 **Dark/Light Mode** - User preference support
- 📧 **Contact Form** - Direct message submissions
- 🖼️ **Media Management** - Upload and manage images
- 📄 **Resume Management** - Multiple resume versions
- 🎓 **Education & Experience** - Timeline view
- 🏆 **Certifications & Achievements** - Showcase your credentials
- 💼 **Projects Portfolio** - Featured work with search

## 🛠️ Tech Stack

### Backend
- **Java 17** with Spring Boot 3.2.5
- **Spring Security** with JWT authentication
- **Spring Data JPA** with Hibernate
- **MySQL/PostgreSQL** database
- **Maven** for dependency management

### Frontend
- **HTML5, CSS3, JavaScript** (Vanilla)
- **Font Awesome** icons
- **Google Fonts** (Inter & Space Grotesk)
- Responsive Grid & Flexbox layouts

## 📁 Project Structure

```
portfolio/
├── backend/                    # Spring Boot backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/          # Java source code
│   │   │   └── resources/     # Configuration files
│   │   └── test/              # Test files
│   └── pom.xml               # Maven configuration
│
├── frontend/                   # Frontend application
│   ├── admin/                 # Admin panel pages
│   ├── css/                   # Stylesheets
│   ├── js/                    # JavaScript files
│   ├── index.html            # Main page
│   └── env-config.js         # Environment configuration
│
├── .gitignore                 # Git ignore rules
├── render.yaml               # Render deployment config
├── vercel.json               # Vercel deployment config
├── DEPLOYMENT_GUIDE.md       # Detailed deployment guide
└── QUICK_DEPLOY.md           # Quick deployment checklist
```

## 🚀 Quick Start

### Local Development

#### Prerequisites
- Java 17+
- Maven 3.6+
- MySQL 8.0+ (or PostgreSQL for production)
- Node.js (optional, for local server)

#### Backend Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/PERIYASAMY-N/Portfolio.git
   cd Portfolio
   ```

2. **Configure database**
   
   Create MySQL database:
   ```sql
   CREATE DATABASE portfolio_db;
   ```

3. **Update configuration**
   
   Edit `backend/src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/portfolio_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

4. **Run the backend**
   ```bash
   cd backend
   mvn spring-boot:run
   ```
   
   Backend will start at: `http://localhost:8080`

#### Frontend Setup

1. **Open frontend**
   
   Simply open `frontend/index.html` in your browser, or use a local server:
   
   ```bash
   # Using Python
   cd frontend
   python -m http.server 5500
   
   # Using Node.js
   npx serve frontend
   ```

2. **Access the site**
   - Main site: `http://localhost:5500`
   - Admin panel: `http://localhost:5500/admin/login.html`

3. **Default admin credentials**
   - Username: `admin`
   - Password: `Admin@123`
   
   ⚠️ **Change these immediately in production!**

## 🌐 Deployment

### Option 1: Render (Backend) + Vercel (Frontend)

This is the recommended free hosting solution.

**Quick Deploy:**

1. **Deploy Backend to Render**
   - Connect GitHub repository
   - Use build command: `cd backend && mvn clean package -DskipTests`
   - Use start command: `java -jar backend/target/portfolio-cms-1.0.0.jar`
   - Add PostgreSQL database
   - Set environment variables

2. **Deploy Frontend to Vercel**
   - Connect GitHub repository
   - Set root directory to `frontend`
   - Update API URL in `frontend/env-config.js`

📖 **Full deployment guide:** See [DEPLOYMENT_GUIDE.md](DEPLOYMENT_GUIDE.md)

📝 **Quick checklist:** See [QUICK_DEPLOY.md](QUICK_DEPLOY.md)

### Option 2: Other Platforms

- **Heroku**: Use similar configuration to Render
- **AWS**: EC2 + RDS or Elastic Beanstalk
- **Azure**: App Service + Azure Database
- **DigitalOcean**: App Platform or Droplet
- **Netlify**: For frontend (similar to Vercel)

## 🔐 Security Features

- ✅ JWT-based authentication
- ✅ Password encryption with BCrypt
- ✅ CORS protection
- ✅ SQL injection prevention
- ✅ XSS protection
- ✅ Secure file upload validation
- ✅ Environment-based configuration

## 📸 Screenshots

### Main Portfolio
![Portfolio Home](https://via.placeholder.com/800x400?text=Portfolio+Home)

### Admin Dashboard
![Admin Dashboard](https://via.placeholder.com/800x400?text=Admin+Dashboard)

## 🎯 API Endpoints

### Public Endpoints
- `GET /api/public/profile` - Get profile information
- `GET /api/public/skills` - Get all skills
- `GET /api/public/projects` - Get all projects
- `GET /api/public/projects/{id}` - Get single project
- `GET /api/public/certifications` - Get certifications
- `GET /api/public/education` - Get education history
- `GET /api/public/experience` - Get work experience
- `GET /api/public/achievements` - Get achievements
- `POST /api/public/messages` - Send contact message

### Admin Endpoints (Requires JWT)
- `POST /api/auth/login` - Admin login
- `GET /api/admin/dashboard/stats` - Get dashboard statistics
- `PUT /api/admin/profile` - Update profile
- `POST /api/admin/skills` - Create skill
- `PUT /api/admin/skills/{id}` - Update skill
- `DELETE /api/admin/skills/{id}` - Delete skill
- ... (and many more)

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is open source and available under the [MIT License](LICENSE).

## 👤 Author

**PERIYASAMY N**

- GitHub: [@PERIYASAMY-N](https://github.com/PERIYASAMY-N)
- Portfolio: [Live Demo](https://your-portfolio.vercel.app)

## 🙏 Acknowledgments

- Font Awesome for icons
- Google Fonts for typography
- Spring Boot community
- All open source contributors

## 📞 Support

If you have any questions or need help with deployment:

1. Check the [DEPLOYMENT_GUIDE.md](DEPLOYMENT_GUIDE.md)
2. Open an issue on GitHub
3. Contact me through the portfolio contact form

---

**⭐ If you find this project useful, please consider giving it a star!**

Made with ❤️ by PERIYASAMY N
