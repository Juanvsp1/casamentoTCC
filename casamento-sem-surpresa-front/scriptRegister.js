const baseUrl = 'http://localhost:8080/auth';

// Register
document.getElementById('formRegister').addEventListener('submit', async (event) => {
  event.preventDefault();

  const email = document.getElementById('registerEmail').value;
  const password = document.getElementById('registerPassword').value;
  const firstName = document.getElementById('registerFirstName').value;
  const lastName = document.getElementById('registerLastName').value;

  try {
      const response = await fetch(`${baseUrl}/register`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
              email,
              password,
              firstName,
              lastName,
              role: 'ADMIN' // Ajuste conforme necessário
          })
      });

      if (response.ok) {
          alert('Registration successful!');
      } else {
          alert('Registration failed!');
      }
  } catch (error) {
      console.error('Error:', error);
    }
});