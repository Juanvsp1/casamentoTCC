const baseUrl = 'http://localhost:8080/auth';

// Login
document.getElementById('form').addEventListener('submit', async (event) => {
event.preventDefault();
    
const email = document.getElementById('loginEmail').value;
const password = document.getElementById('loginPassword').value;

try {
    const response = await fetch(`${baseUrl}/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, password })
        });

        if (response.ok) {
            const data = await response.json();
            alert('Login successful! Token: ' + data.token);
            console.log(data.token); // Use o token conforme necessário
        } else {
            alert('Login failed!');
        }
    } catch (error) {
        console.error('Error:', error);
    }
});




