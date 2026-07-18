/**
 * Environment Configuration
 * This file is loaded before api.js to set the correct API URL
 *
 * Public portfolio  → hosted on Vercel  → calls Render backend
 * Admin dashboard   → runs locally only → calls localhost:8080
 */

const getApiBaseUrl = () => {
  const host = window.location.hostname;

  // Production: Vercel hosted public portfolio
  if (host.includes('vercel.app') || host.includes('onrender.com')) {
    return 'https://portfolio-backend-6dd2.onrender.com';
  }

  // Local development (public portfolio or admin)
  return 'http://localhost:8080';
};

window.API_BASE_URL = getApiBaseUrl();
