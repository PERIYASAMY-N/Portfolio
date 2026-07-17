/**
 * Admin Authentication Logic
 */

document.addEventListener('DOMContentLoaded', () => {
  const loginForm = document.getElementById('login-form');
  const errorDiv = document.getElementById('login-error');

  // Check if we are on login page but already have a token
  if (window.location.pathname.endsWith('login.html')) {
    if (api.getToken()) {
      window.location.href = 'dashboard.html';
    }
  }

  if (loginForm) {
    loginForm.addEventListener('submit', async (e) => {
      e.preventDefault();
      errorDiv.style.display = 'none';

      const username = document.getElementById('username').value;
      const password = document.getElementById('password').value;
      const btn = loginForm.querySelector('button');

      try {
        btn.disabled = true;
        btn.textContent = 'Logging in...';

        const res = await api.login({ username, password });

        if (res.success && res.data && res.data.token) {
          localStorage.setItem('portfolio_token', res.data.token);
          window.location.href = 'dashboard.html';
        } else {
          errorDiv.textContent = res.message || 'Login failed';
          errorDiv.style.display = 'block';
        }
      } catch (err) {
        errorDiv.textContent = 'Network error. Please try again.';
        errorDiv.style.display = 'block';
      } finally {
        btn.disabled = false;
        btn.textContent = 'Login to Dashboard';
      }
    });
  }
});

function logout() {
  localStorage.removeItem('portfolio_token');
  window.location.href = 'login.html';
}

function checkAuth() {
  if (!api.getToken() && !window.location.pathname.endsWith('login.html')) {
    window.location.href = 'login.html';
  }
}
