/**
 * Environment Configuration
 * This file is loaded before api.js to set the correct API URL
 */

// Determine the API base URL based on the environment
const getApiBaseUrl = () => {
  // Check if we're in production (Vercel deployment)
  if (window.location.hostname.includes('vercel.app')) {
    return 'https://portfolio-backend-6dd2.onrender.com';
  }
  
  // Local development
  return 'http://localhost:8080';
};

// Set global API base URL
window.API_BASE_URL = getApiBaseUrl();
