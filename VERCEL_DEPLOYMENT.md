# 🚀 Vercel Frontend Deployment Guide

## Prerequisites
- GitHub account with your portfolio repository pushed
- Vercel account (free tier available at [vercel.com](https://vercel.com))
- Backend deployed on Render (from previous step)

---

## Step 1: Update Frontend Configuration

Before deploying, you need to update the API URL to point to your Render backend.

### 1.1 Edit `frontend/env-config.js`
Open the file and replace the URL:

```javascript
window.ENV = {
  // Update this URL with your actual Render backend URL
  API_URL: 'https://portfolio-backend-xyz.onrender.com'
};
```

Replace `portfolio-backend-xyz` with your actual Render backend URL!

### 1.2 Commit and Push Changes
```bash
cd d:\portfolio
git add frontend/env-config.js
git commit -m "Configure backend API URL for production"
git push origin main
```

---

## Step 2: Deploy to Vercel

### 2.1 Sign Up / Log In
1. Go to [vercel.com](https://vercel.com)
2. Click **"Sign Up"** (or Log In if you have an account)
3. Choose **"Continue with GitHub"**
4. Authorize Vercel to access your GitHub repositories

### 2.2 Import Your Project
1. On Vercel Dashboard, click **"Add New..."** → **"Project"**
2. Find your repository: `PERIYASAMY-N/Portfolio`
3. Click **"Import"**

### 2.3 Configure Project
Fill in these settings:

**Project Settings:**
- **Project Name**: `portfolio` (or your preferred name)
- **Framework Preset**: `Other` (we have a custom setup)
- **Root Directory**: Click **"Edit"** → Select `frontend`
- **Build Command**: Leave empty or use `echo "No build required"`
- **Output Directory**: Leave as `.` (current directory)
- **Install Command**: Leave empty

**Environment Variables:**
- Not needed (we're using `env-config.js`)

### 2.4 Deploy
1. Click **"Deploy"**
2. Vercel will start deploying your application
3. Wait 1-2 minutes for deployment
4. Once completed, you'll see: 🎉 **"Congratulations!"**

### 2.5 Get Your Frontend URL
Your portfolio will be available at:
```
https://your-portfolio.vercel.app
```

Or a custom subdomain you chose!

---

## Step 3: Update Backend CORS

Now that you have your Vercel URL, update the backend CORS settings:

### 3.1 Update Render Environment Variables
1. Go to [Render Dashboard](https://dashboard.render.com/)
2. Select your **"portfolio-backend"** service
3. Click **"Environment"** tab
4. Update `CORS_ORIGINS` variable:
   ```
   https://your-portfolio.vercel.app
   ```
   *(Replace with your actual Vercel URL)*
5. Click **"Save Changes"**
6. Wait for service to redeploy (1-2 minutes)

---

## Step 4: Test Your Portfolio

### 4.1 Visit Your Portfolio
Open: `https://your-portfolio.vercel.app`

You should see your portfolio loading!

### 4.2 Test Contact Form
1. Scroll to the **Contact** section
2. Fill out the form
3. Click **"Send Message"**
4. You should see a success message ✅

### 4.3 Test Admin Login (Locally Only)
Since admin is not deployed, test it locally:
1. Open `d:\portfolio\frontend\admin\login.html` in browser
2. Update `frontend/js/api.js` line 7 to point to Render backend
3. Try logging in with your admin credentials

---

## Step 5: Custom Domain (Optional)

### 5.1 Add Custom Domain
1. On Vercel project page, click **"Settings"**
2. Click **"Domains"**
3. Click **"Add"**
4. Enter your custom domain (e.g., `periyasamy.dev`)
5. Follow DNS configuration instructions

### 5.2 Update CORS Again
After adding custom domain, update Render `CORS_ORIGINS`:
```
https://your-portfolio.vercel.app,https://periyasamy.dev
```

---

## 🎨 Customization

### Update Site Metadata
Edit `frontend/index.html`:
```html
<title>Your Name - Portfolio</title>
<meta name="description" content="Your custom description" />
```

### Commit and Push:
```bash
git add frontend/index.html
git commit -m "Update site metadata"
git push origin main
```

Vercel will **auto-deploy** on every push! 🔄

---

## ⚙️ Vercel Configuration

Your `vercel.json` is already configured with:
- ✅ Static file serving
- ✅ Security headers (X-Frame-Options, CSP, etc.)
- ✅ SPA routing (redirects to index.html)

No additional configuration needed!

---

## 🔍 Monitoring & Analytics

### View Deployment Logs
1. Go to Vercel Dashboard
2. Select your project
3. Click **"Deployments"**
4. Click on any deployment to view logs

### Enable Analytics (Optional)
1. Go to project **"Analytics"** tab
2. Enable **Vercel Analytics** (free tier available)
3. Get insights on page views, performance, etc.

---

## 🐛 Troubleshooting

### API Requests Failing:
**Issue**: Frontend can't connect to backend

**Solutions**:
1. Verify `env-config.js` has correct Render URL
2. Check CORS settings on Render backend
3. Open browser DevTools → Console for errors
4. Verify backend is "Live" on Render

### 404 Errors on Refresh:
**Issue**: Page shows 404 when refreshing

**Solution**: Already handled by `vercel.json` rewrites

### Images Not Loading:
**Issue**: Profile photos or project images not showing

**Solutions**:
1. Images are uploaded to backend at `/uploads/`
2. Backend serves them at `https://backend-url.com/uploads/filename`
3. Ensure backend file upload is working
4. Check backend logs for file storage issues

### Environment Changes Not Reflecting:
**Issue**: Updates to `env-config.js` not showing

**Solutions**:
1. Hard refresh: `Ctrl + Shift + R` (Windows) or `Cmd + Shift + R` (Mac)
2. Clear browser cache
3. Wait 1-2 minutes for Vercel CDN to update

---

## 🚀 Performance Tips

### Optimize Images
- Use WebP format for better compression
- Resize images before uploading (max 1920px width)
- Enable lazy loading (already in code)

### Enable Vercel Speed Insights
1. Go to project **"Speed Insights"** tab
2. Enable for free performance monitoring
3. Get Core Web Vitals metrics

---

## 🔄 Continuous Deployment

Vercel automatically deploys on every push to `main` branch:

1. Make changes locally
2. Commit and push to GitHub:
   ```bash
   git add .
   git commit -m "Update portfolio"
   git push origin main
   ```
3. Vercel automatically detects and deploys! ✨

No manual deployment needed!

---

## 📝 Deployment Checklist

- ✅ Backend deployed on Render
- ✅ Frontend `env-config.js` updated with backend URL
- ✅ Changes committed and pushed to GitHub
- ✅ Project imported to Vercel
- ✅ Root directory set to `frontend`
- ✅ Deployed successfully
- ✅ CORS updated on backend with Vercel URL
- ✅ Contact form tested and working
- ✅ All sections loading correctly

---

## 🎉 Success!

Your portfolio is now live:
- **Frontend**: `https://your-portfolio.vercel.app`
- **Backend**: `https://portfolio-backend-xyz.onrender.com`

Share your portfolio with the world! 🌍

---

## 📞 Support

If you encounter any issues:
1. Check Vercel deployment logs
2. Check Render backend logs
3. Verify all URLs and CORS settings
4. Test API endpoints directly with Postman/curl

---

**Last Updated**: July 17, 2026
