# 🚀 Vercel Frontend Deployment Guide

## Prerequisites
- GitHub account with the Portfolio repository
- Vercel account (sign up at https://vercel.com)

---

## Step-by-Step Deployment

### 1️⃣ **Sign in to Vercel**
1. Go to https://vercel.com
2. Click **"Sign Up"** or **"Login"**
3. Choose **"Continue with GitHub"**
4. Authorize Vercel to access your GitHub account

---

### 2️⃣ **Import Your Project**
1. Click **"Add New..."** → **"Project"**
2. Find and select your repository: **`PERIYASAMY-N/Portfolio`**
3. Click **"Import"**

---

### 3️⃣ **Configure Project Settings**

#### **Root Directory**
- Set **Root Directory** to: `frontend`
- Click the **Edit** button next to "Root Directory"
- Type: `frontend`
- Click **Continue**

#### **Framework Preset**
- Select: **Other** (since this is a vanilla HTML/CSS/JS project)

#### **Build Settings**
- **Build Command**: Leave empty or set to `echo "No build needed"`
- **Output Directory**: Leave as `.` (current directory)
- **Install Command**: Leave empty

---

### 4️⃣ **Deploy**
1. Click **"Deploy"**
2. Wait for deployment to complete (~1-2 minutes)
3. You'll see: ✅ **"Congratulations! Your project has been deployed"**

---

### 5️⃣ **Get Your Deployment URL**
After deployment completes, you'll get a URL like:
```
https://portfolio-[random].vercel.app
```

---

### 6️⃣ **Update Backend CORS Settings**

Once you have your Vercel URL, update the backend:

1. Go to Render Dashboard: https://dashboard.render.com
2. Select your **portfolio-backend** service
3. Go to **Environment** tab
4. Find the `FRONTEND_URL` variable
5. Update it to your Vercel URL: `https://portfolio-[your-url].vercel.app`
6. Click **"Save Changes"**
7. Render will automatically redeploy the backend

---

## 🎉 Your Portfolio is Live!

- **Frontend**: `https://portfolio-[your-url].vercel.app`
- **Backend**: `https://portfolio-backend-6dd2.onrender.com`
- **Admin Panel**: `https://portfolio-[your-url].vercel.app/admin/`

---

## 🔧 Troubleshooting

### Frontend loads but admin login doesn't work
- **Cause**: Backend is still being fixed (we'll continue tomorrow)
- **Solution**: Wait for backend deployment to succeed

### CSS/JS not loading
- **Check**: Make sure all file paths in `index.html` are relative (no leading `/`)
- **Example**: Use `css/style.css` not `/css/style.css`

### CORS errors in browser console
- **Solution**: Update `FRONTEND_URL` in Render backend with your actual Vercel URL

---

## 📝 Next Steps (Tomorrow)

1. Fix backend database connection
2. Test admin login functionality
3. Verify portfolio data loads correctly
4. Update any remaining hardcoded URLs

---

## 🔄 Redeploying

Vercel automatically redeploys when you push to GitHub:
1. Make changes locally
2. `git add .`
3. `git commit -m "your message"`
4. `git push origin main`
5. Vercel detects the push and redeploys automatically!

---

## 🌟 Custom Domain (Optional)

To use your own domain:
1. Go to your Vercel project
2. Click **"Settings"** → **"Domains"**
3. Add your domain
4. Follow DNS configuration instructions

---

**Need help?** Check Vercel docs: https://vercel.com/docs
