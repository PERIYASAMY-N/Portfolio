# 🚀 Quick Deployment Checklist

## Your Portfolio Deployment Plan

**Backend**: Render → **Frontend**: Vercel

---

## 📋 Deployment Steps (In Order)

### Step 1: Push to GitHub ✅
```bash
cd d:\portfolio
git push -u origin main
```

---

### Step 2: Deploy Backend on Render 🎯

1. Go to [render.com](https://render.com) → Sign up/Login
2. Click **"New +"** → **"Web Service"**
3. Connect GitHub → Select `PERIYASAMY-N/Portfolio`
4. Configure:
   - **Root Directory**: `backend`
   - **Build Command**: `mvn clean package -DskipTests`
   - **Start Command**: `java -jar target/portfolio-0.0.1-SNAPSHOT.jar`
5. Add Environment Variables (see `RENDER_DEPLOYMENT.md` for details)
6. Click **"Create Web Service"**
7. Wait 5-10 minutes
8. **Copy your backend URL**: `https://portfolio-backend-xyz.onrender.com`

📖 **Detailed Guide**: [RENDER_DEPLOYMENT.md](./RENDER_DEPLOYMENT.md)

---

### Step 3: Update Frontend Configuration 🔧

1. Open `frontend/env-config.js`
2. Replace the API URL with your Render backend URL:
   ```javascript
   window.ENV = {
     API_URL: 'https://portfolio-backend-xyz.onrender.com'  // Your actual URL
   };
   ```
3. Commit and push:
   ```bash
   git add frontend/env-config.js
   git commit -m "Configure production API URL"
   git push origin main
   ```

---

### Step 4: Deploy Frontend on Vercel 🌐

1. Go to [vercel.com](https://vercel.com) → Sign up/Login with GitHub
2. Click **"Add New..."** → **"Project"**
3. Import `PERIYASAMY-N/Portfolio`
4. Configure:
   - **Root Directory**: `frontend`
   - **Framework Preset**: Other
   - **Build Command**: Leave empty
5. Click **"Deploy"**
6. Wait 1-2 minutes
7. **Copy your frontend URL**: `https://your-portfolio.vercel.app`

📖 **Detailed Guide**: [VERCEL_DEPLOYMENT.md](./VERCEL_DEPLOYMENT.md)

---

### Step 5: Update Backend CORS 🔄

1. Go back to Render Dashboard
2. Select your backend service
3. Click **"Environment"** tab
4. Update `CORS_ORIGINS`:
   ```
   https://your-portfolio.vercel.app
   ```
5. Click **"Save Changes"** (service will auto-redeploy)

---

### Step 6: Test Everything ✅

Visit your portfolio: `https://your-portfolio.vercel.app`

**Test Checklist:**
- [ ] Home page loads
- [ ] About section displays
- [ ] Skills section displays
- [ ] Projects section displays
- [ ] Contact form works (send a test message)
- [ ] All navigation links work

---

## 📝 Your URLs

After deployment, save these URLs:

| Service | URL | Status |
|---------|-----|--------|
| **Frontend (Vercel)** | `https://_____.vercel.app` | ⏳ |
| **Backend (Render)** | `https://_____.onrender.com` | ⏳ |
| **GitHub Repo** | `https://github.com/PERIYASAMY-N/Portfolio` | ✅ |

---

## 🔐 Admin Access

Your admin dashboard is **NOT deployed** publicly (as configured in `.gitignore`).

**To access admin locally:**
1. Open `d:\portfolio\frontend\admin\login.html` in browser
2. Update API URL in `frontend/js/api.js` to point to production backend
3. Login with credentials set in Render environment variables

---

## ⚡ Quick Commands

### Push updates to production:
```bash
git add .
git commit -m "Update portfolio"
git push origin main
```
Both Render and Vercel will **auto-deploy** on every push! 🔄

### View logs:
- **Render**: Dashboard → Your Service → "Logs" tab
- **Vercel**: Dashboard → Your Project → "Deployments"

---

## 🐛 Common Issues

| Issue | Solution |
|-------|----------|
| **API requests failing** | Check `env-config.js` has correct Render URL |
| **CORS errors** | Update CORS_ORIGINS on Render with Vercel URL |
| **Backend slow/not responding** | Free tier spins down after 15min, first request takes 30-60s |
| **Build failed on Render** | Check logs, verify Java version is 17 |
| **Changes not reflecting** | Wait 1-2 minutes for CDN cache, hard refresh browser |

---

## 📚 Documentation

- **Main README**: [README.md](./README.md)
- **Render Guide**: [RENDER_DEPLOYMENT.md](./RENDER_DEPLOYMENT.md)
- **Vercel Guide**: [VERCEL_DEPLOYMENT.md](./VERCEL_DEPLOYMENT.md)
- **General Deployment**: [DEPLOYMENT.md](./DEPLOYMENT.md)

---

## 🎉 Success Criteria

- ✅ Code pushed to GitHub (admin folder excluded)
- ✅ Backend deployed and live on Render
- ✅ Frontend deployed and live on Vercel
- ✅ CORS configured correctly
- ✅ Contact form working
- ✅ All sections loading properly
- ✅ No console errors in browser DevTools

---

## 🚀 You're Ready!

Follow these steps in order and your portfolio will be live in **~20 minutes**!

**Questions?** Check the detailed guides linked above.

---

**Last Updated**: July 17, 2026
