# Portfolio Deployment Guide

This guide will walk you through deploying your portfolio with:
- **Backend**: Render (Spring Boot + MySQL)
- **Frontend**: Vercel (Static HTML/CSS/JS)

---

## Prerequisites

1. GitHub account with your portfolio repository
2. [Render](https://render.com) account
3. [Vercel](https://vercel.com) account

---

## Part 1: Deploy Backend to Render

### Step 1: Create a New Web Service

1. Go to [Render Dashboard](https://dashboard.render.com/)
2. Click **"New +"** → **"Web Service"**
3. Connect your GitHub repository: `PERIYASAMY-N/Portfolio`

### Step 2: Configure the Service

Fill in the following settings:

- **Name**: `portfolio-backend` (or your preferred name)
- **Region**: Choose the closest to you
- **Branch**: `main`
- **Root Directory**: Leave blank
- **Runtime**: `Java`
- **Build Command**:
  ```bash
  cd backend && mvn clean package -DskipTests
  ```
- **Start Command**:
  ```bash
  java -jar backend/target/portfolio-cms-1.0.0.jar
  ```

### Step 3: Add Environment Variables

Click **"Advanced"** and add these environment variables:

| Key | Value |
|-----|-------|
| `SPRING_PROFILES_ACTIVE` | `prod` |
| `JWT_SECRET` | Click "Generate" (or use a strong 256-bit secret) |
| `ADMIN_USERNAME` | `admin` (or your choice) |
| `ADMIN_PASSWORD` | Click "Generate" (or set a strong password - **SAVE THIS!**) |
| `ADMIN_EMAIL` | `your-email@example.com` |
| `FRONTEND_URL` | `https://your-vercel-app.vercel.app` (will update later) |

### Step 4: Create PostgreSQL Database (Render Free Tier)

Since Render's free tier doesn't include MySQL, we'll use PostgreSQL:

1. Go to Render Dashboard → **"New +"** → **"PostgreSQL"**
2. **Name**: `portfolio-db`
3. **Database**: `portfolio_db`
4. **User**: `portfolio_user`
5. **Region**: Same as your web service
6. Click **"Create Database"**

### Step 5: Connect Database to Web Service

1. Go back to your **Web Service** settings
2. Scroll to **"Environment Variables"**
3. Add a new environment variable:
   - **Key**: `DATABASE_URL`
   - **Value**: Copy the **Internal Database URL** from your PostgreSQL database page
     (Format: `postgresql://user:pass@host:port/dbname`)

### Step 6: Update Backend for PostgreSQL

**Important**: Since Render free tier uses PostgreSQL, not MySQL, you need to update:

#### Update `backend/pom.xml`:

Replace the MySQL dependency:
```xml
<!-- Remove this -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>

<!-- Add this -->
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

#### Update `backend/src/main/resources/application-prod.properties`:

```properties
# Database Configuration
spring.datasource.url=${DATABASE_URL}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

### Step 7: Deploy

1. Click **"Create Web Service"**
2. Wait for the build to complete (5-10 minutes)
3. Once deployed, you'll get a URL like: `https://portfolio-backend.onrender.com`
4. **Test it**: Visit `https://your-backend-url.onrender.com/actuator/health`
   - Should return: `{"status":"UP"}`

### Step 8: Save Backend URL

**Important**: Copy your Render backend URL (e.g., `https://portfolio-backend-xyz.onrender.com`)
You'll need this for the frontend deployment!

---

## Part 2: Deploy Frontend to Vercel

### Step 1: Update Frontend Configuration

Before deploying, update the backend URL in your code:

1. Edit `frontend/env-config.js`:

```javascript
const getApiBaseUrl = () => {
  if (window.location.hostname.includes('vercel.app')) {
    return 'https://your-backend-url.onrender.com'; // ← PUT YOUR RENDER URL HERE
  }
  return 'http://localhost:8080';
};
```

2. Commit and push:
```bash
git add frontend/env-config.js
git commit -m "Update production API URL"
git push origin main
```

### Step 2: Deploy to Vercel

#### Option A: Using Vercel CLI

1. Install Vercel CLI:
   ```bash
   npm install -g vercel
   ```

2. Navigate to your project:
   ```bash
   cd d:\portfolio
   ```

3. Login to Vercel:
   ```bash
   vercel login
   ```

4. Deploy:
   ```bash
   vercel --prod
   ```

5. Follow the prompts:
   - **Set up and deploy**: Y
   - **Which scope**: (select your account)
   - **Link to existing project**: N
   - **Project name**: `portfolio` (or your choice)
   - **Directory**: `./frontend`
   - **Build settings**: Accept defaults

#### Option B: Using Vercel Dashboard

1. Go to [Vercel Dashboard](https://vercel.com/dashboard)
2. Click **"Add New..."** → **"Project"**
3. Import your GitHub repository: `PERIYASAMY-N/Portfolio`
4. Configure:
   - **Framework Preset**: Other
   - **Root Directory**: `frontend`
   - **Build Command**: Leave empty (static site)
   - **Output Directory**: Leave empty
   - **Install Command**: Leave empty
5. Click **"Deploy"**

### Step 3: Get Your Vercel URL

Once deployed, you'll get a URL like: `https://portfolio-username.vercel.app`

### Step 4: Update Backend CORS

1. Go back to **Render Dashboard** → Your Web Service
2. Update the `FRONTEND_URL` environment variable:
   ```
   https://your-actual-vercel-url.vercel.app
   ```
3. Click **"Save Changes"**
4. Wait for the service to restart

---

## Part 3: Testing Your Deployment

### Test Backend

1. Visit: `https://your-backend.onrender.com/actuator/health`
   - Should return: `{"status":"UP"}`

2. Test API endpoint:
   ```
   https://your-backend.onrender.com/api/public/profile
   ```

### Test Frontend

1. Visit: `https://your-app.vercel.app`
2. Check if the portfolio loads correctly
3. Open browser console (F12) → Look for any errors
4. Test the contact form
5. Check if images load

### Admin Login

1. Visit: `https://your-app.vercel.app/admin/login.html`
2. Use the credentials you set:
   - Username: (from `ADMIN_USERNAME`)
   - Password: (from `ADMIN_PASSWORD` - the one you saved!)

---

## Part 4: Configure Custom Domain (Optional)

### For Vercel (Frontend)

1. Go to your project settings in Vercel
2. Click **"Domains"**
3. Add your custom domain
4. Follow DNS configuration instructions

### For Render (Backend)

1. Go to your web service settings
2. Click **"Custom Domains"**
3. Add your custom domain (e.g., `api.yourdomain.com`)
4. Update DNS records as instructed

---

## Troubleshooting

### Backend Issues

**Issue**: 502 Bad Gateway
- **Solution**: Check logs in Render dashboard. Ensure environment variables are set correctly.

**Issue**: Database connection errors
- **Solution**: Verify `DATABASE_URL` is set correctly. Check PostgreSQL database is running.

**Issue**: CORS errors
- **Solution**: Verify `FRONTEND_URL` environment variable matches your Vercel URL exactly.

### Frontend Issues

**Issue**: API calls fail
- **Solution**: Check `env-config.js` has correct backend URL. Open browser console for error details.

**Issue**: 404 errors
- **Solution**: Ensure `vercel.json` routes are configured correctly.

**Issue**: Images don't load
- **Solution**: Check backend `/api/public/media` endpoint. Verify file upload configuration.

---

## Important Notes

1. **Render Free Tier**: Services sleep after 15 minutes of inactivity. First request may take 30-60 seconds.

2. **Database Backups**: Render free tier PostgreSQL doesn't include automatic backups. Consider upgrading or manual exports.

3. **File Uploads**: Render's ephemeral filesystem means uploaded files will be lost on restart. Consider using AWS S3 or Cloudinary for production.

4. **Environment Variables**: Never commit sensitive data! Always use environment variables.

5. **HTTPS**: Both Render and Vercel provide free SSL certificates automatically.

---

## Cost Summary

- **Render**: Free tier (with limitations)
- **Vercel**: Free tier (generous limits for personal projects)
- **Total**: $0/month for basic usage

For production with higher traffic, consider:
- Render: $7/month (starter plan)
- Database: $7/month (PostgreSQL starter)
- File Storage: AWS S3 (~$1-5/month)

---

## Next Steps

1. ✅ Test all functionality on production
2. ✅ Update portfolio content via admin panel
3. ✅ Share your portfolio URL!
4. 📊 Monitor usage in Render and Vercel dashboards
5. 🔧 Set up error monitoring (Sentry, LogRocket, etc.)

---

## Support

If you encounter issues:
1. Check Render logs: Dashboard → Your Service → Logs
2. Check Vercel logs: Dashboard → Your Project → Deployments → View Logs
3. Check browser console (F12) for frontend errors

---

**Congratulations! Your portfolio is now live! 🎉**
