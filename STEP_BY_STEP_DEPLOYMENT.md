# 🚀 Step-by-Step Deployment Guide

Follow these exact steps to deploy your portfolio. This should take about 30-40 minutes total.

---

## 📋 Before You Start

**You will need:**
- ✅ GitHub account (you already have: PERIYASAMY-N)
- ✅ Email address for account verification
- ✅ Pen and paper (to write down passwords and URLs)

**Create these accounts (FREE):**
1. Render account: https://dashboard.render.com/register
2. Vercel account: https://vercel.com/signup

---

# PART 1: Deploy Backend to Render ⚙️

**Time:** ~20 minutes

---

## Step 1: Create Render Account (3 minutes)

1. **Go to:** https://dashboard.render.com/register

2. **Sign up using GitHub:**
   - Click "Sign in with GitHub"
   - Authorize Render to access your GitHub
   - Complete your profile

3. **Verify your email** (check inbox)

---

## Step 2: Create PostgreSQL Database (5 minutes)

1. **In Render Dashboard**, click **"New +"** (top right)

2. **Select "PostgreSQL"**

3. **Fill in database details:**
   ```
   Name: portfolio-db
   Database: portfolio_db
   User: portfolio_user
   Region: Choose closest to you (e.g., Oregon (US West) or Frankfurt (EU))
   ```

4. **Plan:** Leave as **"Free"**

5. **Click "Create Database"**

6. **Wait for database to be created** (1-2 minutes)
   - You'll see "Available" status when ready

7. **⚠️ IMPORTANT - Copy Database URL:**
   - In your database page, find **"Internal Database URL"**
   - It looks like: `postgresql://portfolio_user:password@dpg-xxxx.oregon-postgres.render.com/portfolio_db`
   - **Copy this entire URL** (you'll need it in Step 4)
   - **Paste it somewhere safe** (Notepad)

---

## Step 3: Create Web Service (2 minutes)

1. **In Render Dashboard**, click **"New +"** again

2. **Select "Web Service"**

3. **Connect GitHub Repository:**
   - Click "Connect account" if not already connected
   - Find and select: **"PERIYASAMY-N/Portfolio"**
   - Click "Connect"

---

## Step 4: Configure Web Service (5 minutes)

**Fill in these details exactly:**

### Basic Settings:
```
Name: portfolio-backend
Region: Same as your database (e.g., Oregon)
Branch: main
Root Directory: (leave blank)
Runtime: Java
```

### Build & Deploy:
```
Build Command:
cd backend && mvn clean package -DskipTests

Start Command:
java -jar backend/target/portfolio-cms-1.0.0.jar
```

### Instance Type:
```
Select: Free
```

---

## Step 5: Add Environment Variables (5 minutes)

**Click "Advanced" button** to show environment variables section.

**Add these variables one by one:**

### Variable 1: Database URL
```
Key:   DATABASE_URL
Value: [PASTE the Internal Database URL you copied in Step 2]
```

### Variable 2: Active Profile
```
Key:   SPRING_PROFILES_ACTIVE
Value: prod
```

### Variable 3: JWT Secret
```
Key:   JWT_SECRET
Value: [Click "Generate" button - Render will create a random secret]
```

### Variable 4: Admin Username
```
Key:   ADMIN_USERNAME
Value: admin
```

### Variable 5: Admin Password
```
Key:   ADMIN_PASSWORD
Value: [Click "Generate" button]
⚠️ COPY THIS PASSWORD IMMEDIATELY!
Write it down: Admin Password: ________________
```

### Variable 6: Admin Email
```
Key:   ADMIN_EMAIL
Value: your-email@example.com  [Use your real email]
```

### Variable 7: Frontend URL (Temporary)
```
Key:   FRONTEND_URL
Value: http://localhost:5500
[We'll update this later after deploying frontend]
```

---

## Step 6: Deploy Backend (10 minutes)

1. **Click "Create Web Service"** (at the bottom)

2. **Wait for deployment** (This takes 5-10 minutes)
   - You'll see logs scrolling
   - Wait for: **"Your service is live 🎉"**

3. **Copy your backend URL:**
   - At the top of the page, you'll see: `https://portfolio-backend-xxxx.onrender.com`
   - **Copy this URL**
   - **Write it down:**
     ```
     Backend URL: https://________________________.onrender.com
     ```

---

## Step 7: Test Backend (1 minute)

1. **Open a new browser tab**

2. **Visit:** `https://your-backend-url.onrender.com/actuator/health`
   - Replace `your-backend-url` with your actual URL

3. **You should see:**
   ```json
   {"status":"UP"}
   ```

4. **✅ If you see this, backend is working!**

5. **❌ If you see an error:**
   - Wait 1-2 more minutes (service might still be starting)
   - Refresh the page
   - Check Render logs for errors

---

# PART 2: Deploy Frontend to Vercel 🎨

**Time:** ~10 minutes

---

## Step 8: Update Frontend with Backend URL (3 minutes)

**⚠️ CRITICAL STEP - Don't skip this!**

1. **Open your project folder:**
   ```
   d:\portfolio\frontend\env-config.js
   ```

2. **Find this line (around line 8):**
   ```javascript
   return 'https://your-render-app.onrender.com';
   ```

3. **Replace with YOUR backend URL:**
   ```javascript
   return 'https://portfolio-backend-xxxx.onrender.com';
   ```
   [Use the URL you copied in Step 6]

4. **Save the file**

5. **Commit and push to GitHub:**
   
   Open PowerShell in your portfolio folder:
   ```powershell
   cd d:\portfolio
   
   git add frontend/env-config.js
   
   git commit -m "Update production API URL"
   
   git push origin main
   ```

6. **Wait for push to complete** (should see "main -> main")

---

## Step 9: Create Vercel Account (2 minutes)

1. **Go to:** https://vercel.com/signup

2. **Sign up with GitHub:**
   - Click "Continue with GitHub"
   - Authorize Vercel

3. **Complete your profile**

---

## Step 10: Deploy to Vercel (5 minutes)

### Method A: Using Vercel Dashboard (Recommended)

1. **In Vercel Dashboard**, click **"Add New..."** → **"Project"**

2. **Import Git Repository:**
   - Find **"PERIYASAMY-N/Portfolio"**
   - Click **"Import"**

3. **Configure Project:**
   ```
   Project Name: portfolio (or your choice)
   Framework Preset: Other
   Root Directory: frontend  [Click "Edit" and type "frontend"]
   Build Command: (leave empty)
   Output Directory: (leave empty)
   Install Command: (leave empty)
   ```

4. **Click "Deploy"**

5. **Wait for deployment** (1-2 minutes)
   - You'll see build logs
   - Wait for: **"Congratulations!"**

6. **Copy your frontend URL:**
   - You'll see: `https://portfolio-xxxx.vercel.app`
   - **Click "Visit"** to open your portfolio
   - **Copy the URL**
   - **Write it down:**
     ```
     Frontend URL: https://________________________.vercel.app
     ```

---

### Method B: Using Vercel CLI (Alternative)

If the dashboard method doesn't work, try CLI:

1. **Open PowerShell:**
   ```powershell
   cd d:\portfolio
   ```

2. **Install Vercel CLI:**
   ```powershell
   npm install -g vercel
   ```

3. **Login to Vercel:**
   ```powershell
   vercel login
   ```
   - Follow the prompts
   - Verify in your email

4. **Deploy:**
   ```powershell
   vercel --prod
   ```

5. **Answer prompts:**
   ```
   Set up and deploy: Y
   Which scope: (select your account)
   Link to existing project: N
   Project name: portfolio
   In which directory: ./frontend
   ```

6. **Copy the URL** shown at the end

---

## Step 11: Update Backend CORS Settings (2 minutes)

**⚠️ IMPORTANT - Frontend won't work without this!**

1. **Go back to Render Dashboard**

2. **Open your "portfolio-backend" web service**

3. **Go to "Environment" tab** (left sidebar)

4. **Find "FRONTEND_URL" variable**

5. **Click "Edit"** (pencil icon)

6. **Update value to your Vercel URL:**
   ```
   https://portfolio-xxxx.vercel.app
   ```
   [Use the URL from Step 10]

7. **Click "Save Changes"**

8. **Wait for service to restart** (1-2 minutes)
   - You'll see "Deploying..." then "Live"

---

# PART 3: Test Your Portfolio 🎉

**Time:** ~5 minutes

---

## Step 12: Test Frontend (3 minutes)

1. **Open your frontend URL:** `https://your-app.vercel.app`

2. **Check these things:**
   - ✅ Page loads (not blank)
   - ✅ Your name appears
   - ✅ Sections display
   - ✅ No error messages

3. **Open Browser Console** (Press F12)
   - Look for any red errors
   - Should see mostly green/blue messages

4. **Test Navigation:**
   - ✅ Click on different sections
   - ✅ Scroll through page
   - ✅ Toggle dark/light mode

---

## Step 13: Test Admin Panel (2 minutes)

1. **Go to:** `https://your-app.vercel.app/admin/login.html`

2. **Login with credentials:**
   ```
   Username: admin
   Password: [The password you copied in Step 5]
   ```

3. **If login works:**
   - ✅ You'll see the admin dashboard
   - ✅ You can see statistics

4. **Test uploading:**
   - Try uploading a profile picture
   - Try creating a new skill
   - Try adding a project

---

## Step 14: Test Contact Form (1 minute)

1. **Go back to main site**

2. **Scroll to "Contact" section**

3. **Fill out the form:**
   ```
   Name: Test User
   Email: test@example.com
   Subject: Test Message
   Message: This is a test
   ```

4. **Click "Send Message"**

5. **Check for success message**

6. **Go to Admin Panel** → **Messages**
   - You should see your test message

---

# ✅ FINAL CHECKLIST

## Your Live URLs

**Fill these in:**

```
✅ Backend (Render):
   https://_________________________________.onrender.com

✅ Frontend (Vercel):
   https://_________________________________.vercel.app

✅ Admin Panel:
   https://_________________________________.vercel.app/admin/login.html
```

## Your Credentials

**Keep these SECRET:**

```
🔐 Admin Username: admin
🔐 Admin Password: _________________________________
🔐 Admin Email: _________________________________

💾 Database URL: (saved in Render environment variables)
```

---

# 🎯 What to Do Next

## 1. Update Your Content (30 minutes)

Login to admin panel and:

1. **Profile:**
   - Upload your photo
   - Update name, title, bio
   - Add social links
   - Update contact info

2. **Skills:**
   - Add your programming languages
   - Add frameworks and tools
   - Set proficiency levels

3. **Projects:**
   - Add your best projects
   - Upload project images
   - Add descriptions and links

4. **Experience:**
   - Add work experience
   - Add job descriptions

5. **Education:**
   - Add your degrees
   - Add institutions

6. **Certifications:**
   - Upload certificates
   - Add certificate details

7. **Achievements:**
   - Add awards
   - Add accomplishments

8. **Resume:**
   - Upload your latest resume PDF

---

## 2. Share Your Portfolio 🎉

Share your URL on:

- ✅ LinkedIn profile
- ✅ GitHub profile README
- ✅ Resume/CV
- ✅ Email signature
- ✅ Twitter/X bio
- ✅ Job applications

---

## 3. Monitor Performance 📊

**Check these regularly:**

1. **Render Dashboard:**
   - Check service status
   - Monitor build logs
   - Check database usage

2. **Vercel Dashboard:**
   - Check deployment status
   - View analytics
   - Monitor bandwidth

---

# 🆘 Troubleshooting

## Problem: Backend shows 502 Error

**Solution:**
1. Go to Render → Your Service → Logs
2. Look for red error messages
3. Common issues:
   - Database URL wrong → Check environment variable
   - Service still starting → Wait 1-2 more minutes
   - Out of memory → Try redeploying

## Problem: Frontend shows "Failed to fetch"

**Solution:**
1. Check `frontend/env-config.js` has correct backend URL
2. Check backend is running (visit `/actuator/health`)
3. Check CORS setting in Render (FRONTEND_URL variable)
4. Open browser console (F12) for detailed error

## Problem: Admin Login Fails

**Solution:**
1. Check username is exactly: `admin`
2. Check password (look in Render environment variables)
3. Check browser console for errors
4. Try resetting admin password in database

## Problem: Images Don't Upload

**Solution:**
1. Check file size (must be under 10MB)
2. Check file format (jpg, png, gif, webp)
3. Check backend logs for errors
4. Try a different image

## Problem: Render Service "Sleeping"

**Solution:**
1. This is normal on free tier
2. Service sleeps after 15 minutes of inactivity
3. First request after sleep takes 30-60 seconds
4. Upgrade to paid plan ($7/month) to prevent sleep

---

# 💰 Upgrade Options (Optional)

## Current Setup (FREE)
- Render Free Tier: $0/month
- Vercel Free Tier: $0/month
- **Total: $0/month**

**Limitations:**
- Backend sleeps after 15 min inactivity
- Limited database storage (1GB)
- Limited bandwidth
- No automatic backups

## Recommended Production Setup
- Render Starter: $7/month (no sleep, more resources)
- PostgreSQL Starter: $7/month (more storage, backups)
- Custom Domain: $10-15/year
- **Total: ~$15/month + domain**

**Benefits:**
- No service sleep
- Better performance
- Automatic backups
- Professional domain
- More storage

---

# 🎓 Learning Resources

## Render Documentation
- Docs: https://render.com/docs
- Support: https://render.com/docs/support

## Vercel Documentation
- Docs: https://vercel.com/docs
- Support: https://vercel.com/support

## Troubleshooting
1. Check service logs (Render/Vercel dashboards)
2. Check browser console (F12 key)
3. Read error messages carefully
4. Google specific error messages
5. Check GitHub Issues for similar problems

---

# 🎉 Congratulations!

Your portfolio is now LIVE on the internet! 🌍

**You've successfully:**
- ✅ Deployed backend to Render
- ✅ Deployed frontend to Vercel
- ✅ Connected database
- ✅ Set up admin panel
- ✅ Tested everything

**Next steps:**
1. Update your content via admin panel
2. Share your portfolio URL everywhere
3. Keep your portfolio updated with new projects
4. Monitor your dashboards regularly

---

**🌟 Your portfolio URL is now your professional presence online!**

**🚀 Keep building, keep learning, keep growing!**

---

## 📝 Notes Section

Use this space to write down important information:

```
Deployment Date: _______________

Any Issues Faced:
_________________________________________________
_________________________________________________
_________________________________________________

Solutions Applied:
_________________________________________________
_________________________________________________
_________________________________________________

Future Improvements Planned:
_________________________________________________
_________________________________________________
_________________________________________________
```

---

**Made with ❤️ by PERIYASAMY N**

*Good luck with your portfolio! May it bring you great opportunities! 🎯*
