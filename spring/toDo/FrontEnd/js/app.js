document.addEventListener('DOMContentLoaded', () => {
    const registerForm = document.getElementById('registerForm');
    const loginForm = document.getElementById('loginForm');
    const taskForm = document.getElementById('taskForm');
    const taskList = document.getElementById('taskList');

    const apiUrl = 'http://localhost:8080/api';

    registerForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const email = document.getElementById('registerEmail').value;
        const password = document.getElementById('registerPassword').value;
        await registerUser(email, password);
    });

    loginForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const email = document.getElementById('loginEmail').value;
        const password = document.getElementById('loginPassword').value;
        await loginUser(email, password);
    });

    taskForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const title = document.getElementById('taskTitle').value;
        const description = document.getElementById('taskDescription').value;
        const dueDate = document.getElementById('taskDueDate').value;
        await createTask(title, description, dueDate);
    });

    const registerUser = async (email, password) => {
        const response = await fetch(`${apiUrl}/users/register`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password })
        });
        if (response.ok) {
            alert('User registered successfully');
        } else {
            alert('Error registering user');
        }
    };

    const loginUser = async (email, password) => {
        const response = await fetch(`${apiUrl}/users/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password })
        });
        if (response.ok) {
            alert('User logged in successfully');
        } else {
            alert('Invalid email or password');
        }
    };

    const createTask = async (title, description, dueDate) => {
        const userId = 1; // Replace with actual user ID from login
        const response = await fetch(`${apiUrl}/tasks/create`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ title, description, dueDate, userId })
        });
        if (response.ok) {
            alert('Task created successfully');
            loadTasks();
        } else {
            alert('Error creating task');
        }
    };

    const loadTasks = async () => {
        const response = await fetch(`${apiUrl}/tasks/all`);
        if (response.ok) {
            const tasks = await response.json();
            taskList.innerHTML = '';
            tasks.forEach(task => {
                const li = document.createElement('li');
                li.textContent = `${task.title} - ${task.description} - ${task.dueDate}`;
                taskList.appendChild(li);
            });
        } else {
            alert('Error loading tasks');
        }
    };

    loadTasks();
});
