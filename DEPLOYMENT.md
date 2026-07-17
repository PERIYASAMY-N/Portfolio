# 🚀 Deployment Guide

## What's Protected

Your portfolio is now properly configured with `.gitignore` to protect:

### ❌ NOT Pushed to GitHub (Private):
- `frontend/admin/` - Admin dashboard (login.html, dashboard.html)
- `uploads/` - User uploaded files
- `profile.json` & `new-profile.json` - Profile data
- `backend/jdk-17.0.11+9/` - JDK installation
- `backend/target/` - Build artifacts
- `audit_report.md` - Internal documentation

### ✅ Pushed to GitHub (Public):
- `frontend/` - Public portfolio (index.html, CSS, JS)
- `backend/src/` - Backend source code
- `backend/pom.xml` - Maven configuration
- `.gitignore` - Protection rules
- `README.md` - Documentation

---

## 📤 Push to GitHub

Run this command to push your code:

```bash
git push -u origin main
```

If the repository already exists on GitHub, you may need to force push (first time only):

```bash
git push -u origin main --force
```

---

## 🌐 Frontend Deployment Options

### Option 1: GitHub Pages (Recommended for Portfolio)
1. Go to your GitHub repository
2. Click **Settings** → **Pages**
3. Under "Source", select branch `main` and folder `/frontend`
4. Click **Save**
5. Your site will be live at: `https://periyasamy-n.github.io/Portfolio/`

### Option 2: Netlify
1. Go to [netlify.com](https://netlify.com)
2. Click "Add new site" → "Import an existing project"
3. Connect to your GitHub repository
4. Set **Base directory**: `frontend`
5. Set **Publish directory**: `frontend`
6. Click **Deploy**

### Option 3: Vercel
1. Go to [vercel.com](https://vercel.com)
2. Click "New Project"
3. Import your GitHub repository
4. Set **Root Directory**: `frontend`
5. Click **Deploy**

---

## 🖥️ Backend Deployment

### Option 1: Railway
1. Go to [railway.app](https://railway.app)
2. Click "New Project" → "Deploy from GitHub repo"
3. Select your Portfolio repository
4. Add environment variables:
   - `JAVA_VERSION=17`
   - `MAVEN_OPTS=-Xmx512m`
5. Railway will detect the Maven project and deploy automatically

### Option 2: Render
1. Go to [render.com](https://render.com)
2. Click "New +" → "Web Service"
3. Connect your GitHub repository
4. Set **Root Directory**: `backend`
5. Set **Build Command**: `mvn clean package`
6. Set **Start Command**: `java -jar target/portfolio-0.0.1-SNAPSHOT.jar`
7. Click **Create Web Service**

### Option 3: Heroku
1. Install Heroku CLI
2. Run:
```bash
heroku create periyasamy-portfolio-api
git subtree push --prefix backend heroku main
```

---

## 🔐 Security Checklist

Before deploying:

1. ✅ Admin dashboard is excluded from GitHub
2. ✅ Update `application.properties` with production database
3. ✅ Change default admin password in production
4. ✅ Set strong JWT secret key
5. ✅ Configure CORS to allow only your frontend domain
6. ✅ Use HTTPS for both frontend and backend
7. ✅ Don't commit `.env` files or secrets

---

## 🔄 Update Backend API URL

After deploying the backend, update the API URL in your frontend:

Edit `frontend/js/api.js` and change:
```javascript
const API_BASE_URL = 'https://your-backend-url.com/api';
```

---

## 📝 Access Admin Dashboard Locally

Since the admin dashboard is not pushed to GitHub, you can only access it locally:

1. Open `frontend/admin/login.html` in your browser locally
2. Or set up a separate private repository for admin-only deployment

---

## 🆘 Need Help?

If you encounter any issues:
1. Check `.gitignore` is properly configured
2. Run `git status` to see what will be pushed
3. Verify admin folder is not listed
4. Contact support if needed

---

**Last Updated**: July 17, 2026
