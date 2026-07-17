# 🎯 Render Backend Deployment Guide

## Prerequisites
- GitHub account with your portfolio repository pushed
- Render account (free tier available at [render.com](https://render.com))
- MySQL database (Render provides free PostgreSQL, or use external MySQL)

---

## Step 1: Create MySQL Database (Optional)

### Option A: Use Render PostgreSQL (Free)
1. Go to [Render Dashboard](https://dashboard.render.com/)
2. Click **"New +"** → **"PostgreSQL"**
3. Configure:
   - **Name**: `portfolio-db`
   - **Database**: `portfolio_db`
   - **User**: Auto-generated
   - **Region**: Choose nearest
   - **Plan**: Free
4. Click **"Create Database"**
5. Copy the **Internal Database URL** (starts with `postgresql://`)

### Option B: Use External MySQL (e.g., FreeMySQLHosting, Railway)
1. Get MySQL credentials from your provider
2. Format as: `jdbc:mysql://HOST:PORT/DATABASE?createDatabaseIfNotExist=true`

---

## Step 2: Deploy Backend to Render

### 2.1 Create New Web Service
1. Go to [Render Dashboard](https://dashboard.render.com/)
2. Click **"New +"** → **"Web Service"**
3. Connect your GitHub repository: `PERIYASAMY-N/Portfolio`
4. Click **"Connect"**

### 2.2 Configure Service
Fill in these settings:

**Basic Settings:**
- **Name**: `portfolio-backend` (or your preferred name)
- **Region**: Choose nearest region
- **Branch**: `main`
- **Root Directory**: `backend`
- **Runtime**: `Java`
- **Build Command**: `mvn clean package -DskipTests`
- **Start Command**: `java -jar target/portfolio-0.0.1-SNAPSHOT.jar`

**Instance Type:**
- Select **"Free"** (good for portfolio projects)

### 2.3 Add Environment Variables
Click **"Advanced"** and add these environment variables:

| Key | Value | Notes |
|-----|-------|-------|
| `JAVA_VERSION` | `17` | Required |
| `SPRING_PROFILES_ACTIVE` | `prod` | Use production config |
| `DATABASE_URL` | `jdbc:mysql://your-host:3306/portfolio_db` | Your MySQL connection string |
| `DB_USERNAME` | `your_db_user` | Database username |
| `DB_PASSWORD` | `your_db_password` | Database password |
| `JWT_SECRET` | Generate a random 256-bit string | **Important: Use a secure random string!** |
| `ADMIN_USERNAME` | `admin` | Your admin username |
| `ADMIN_PASSWORD` | `YourSecurePassword123!` | **Change this!** |
| `ADMIN_EMAIL` | `your@email.com` | Your admin email |
| `CORS_ORIGINS` | Will update after Vercel deployment | Leave as `*` temporarily |

**How to generate JWT_SECRET:**
```bash
# Use this command to generate a secure JWT secret
node -e "console.log(require('crypto').randomBytes(64).toString('base64'))"
```

Or use an online generator: [RandomKeygen](https://randomkeygen.com/)

### 2.4 Deploy
1. Click **"Create Web Service"**
2. Render will start building your application
3. Wait 5-10 minutes for the first deployment
4. Once deployed, you'll see: ✅ **"Live"**

### 2.5 Get Your Backend URL
Your backend will be available at:
```
https://portfolio-backend-xyz.onrender.com
```

Copy this URL - you'll need it for frontend deployment!

---

## Step 3: Test Your Backend

### Test the API:
```bash
# Test profile endpoint
curl https://your-backend-url.onrender.com/api/public/profile

# Test login endpoint
curl -X POST https://your-backend-url.onrender.com/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"YourPassword"}'
```

You should see JSON responses!

---

## Step 4: Update CORS Settings

Once you deploy the frontend on Vercel (next step), update the `CORS_ORIGINS` environment variable:

1. Go to your Render service
2. Click **"Environment"** tab
3. Update `CORS_ORIGINS` to your Vercel URL:
   ```
   https://your-portfolio.vercel.app
   ```
4. Click **"Save Changes"**
5. Service will auto-redeploy

---

## ⚙️ Important Notes

### Free Tier Limitations:
- ⏰ **Spins down after 15 minutes of inactivity**
- 🐌 **First request after sleep takes 30-60 seconds to wake up**
- 💾 **750 hours/month free (enough for personal portfolio)**

### Database Considerations:
- If using Render PostgreSQL (free), it expires after 90 days
- Consider using Railway MySQL or PlanetScale for permanent free MySQL
- Update `spring.datasource` settings if switching from MySQL to PostgreSQL

### Monitoring:
- Check **"Logs"** tab for errors
- Check **"Metrics"** for performance
- Set up **"Health Check"** at `/api/public/profile`

---

## 🔒 Security Checklist

- ✅ Changed default admin password
- ✅ Set strong JWT secret (64+ characters)
- ✅ CORS configured to allow only your frontend domain
- ✅ No sensitive data in environment variables exposed
- ✅ Database credentials are secure

---

## 🐛 Troubleshooting

### Build Failed:
- Check Java version is 17
- Verify Maven build command
- Check logs for detailed errors

### Service Not Starting:
- Verify Start Command is correct
- Check application logs
- Ensure database connection is valid

### Database Connection Issues:
- Verify DATABASE_URL format
- Check database credentials
- Ensure database server is accessible

### CORS Errors:
- Update CORS_ORIGINS with your Vercel URL
- Include `https://` in the origin
- Redeploy after updating

---

## 📝 Next Steps

1. ✅ Backend deployed on Render
2. ⏭️ Deploy frontend on Vercel (see `VERCEL_DEPLOYMENT.md`)
3. 🔄 Update CORS settings after frontend deployment
4. 🎉 Your portfolio is live!

---

**Your Backend URL**: `https://portfolio-backend-xyz.onrender.com`

Save this URL for the Vercel deployment!
