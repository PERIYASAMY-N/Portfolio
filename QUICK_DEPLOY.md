# Quick Deployment Checklist

## 🚀 Backend (Render)

### 1. Create Web Service
- Connect GitHub repo
- Runtime: **Java**
- Build: `cd backend && mvn clean package -DskipTests`
- Start: `java -jar backend/target/portfolio-cms-1.0.0.jar`

### 2. Environment Variables
```
SPRING_PROFILES_ACTIVE=prod
JWT_SECRET=<generate-strong-secret>
ADMIN_USERNAME=admin
ADMIN_PASSWORD=<generate-strong-password>
ADMIN_EMAIL=your-email@example.com
DATABASE_URL=<from-postgres-database>
FRONTEND_URL=<your-vercel-url>
```

### 3. Create PostgreSQL Database
- Name: `portfolio-db`
- Copy Internal Database URL to `DATABASE_URL` env var

### 4. Deploy & Test
- Wait for build (5-10 min)
- Test: `https://your-app.onrender.com/actuator/health`
- **Save your backend URL!**

---

## 🎨 Frontend (Vercel)

### 1. Update API URL
Edit `frontend/env-config.js`:
```javascript
return 'https://your-backend.onrender.com'; // ← YOUR RENDER URL
```

### 2. Commit & Push
```bash
git add frontend/env-config.js
git commit -m "Update production API URL"
git push origin main
```

### 3. Deploy to Vercel
```bash
vercel --prod
```

Or use Vercel Dashboard:
- Import GitHub repo
- Root Directory: `frontend`
- Deploy

### 4. Update Backend CORS
Go to Render → Environment Variables:
```
FRONTEND_URL=https://your-app.vercel.app
```

---

## ✅ Final Checks

- [ ] Backend health check works
- [ ] Frontend loads without errors
- [ ] API calls successful (check console)
- [ ] Admin login works
- [ ] Contact form works
- [ ] Images load properly

---

## 📝 Important URLs to Save

```
Backend URL: https://________________.onrender.com
Frontend URL: https://________________.vercel.app
Admin Panel: https://________________.vercel.app/admin/login.html

Admin Username: ________________
Admin Password: ________________ (KEEP SECRET!)
```

---

## 🔧 Quick Fixes

**CORS Errors?**
→ Check `FRONTEND_URL` in Render matches your Vercel URL exactly

**API 500 Errors?**
→ Check Render logs & verify `DATABASE_URL` is set

**Slow First Load?**
→ Normal on Render free tier (services sleep after 15 min inactivity)

**Images Not Loading?**
→ Upload test image via admin panel & check browser console

---

## 📚 Full Guide

See `DEPLOYMENT_GUIDE.md` for detailed instructions and troubleshooting.
