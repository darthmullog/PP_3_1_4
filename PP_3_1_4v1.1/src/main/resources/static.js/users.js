fetch('/admin')
    .then(response => response.json())
    .then(users => {
        const tableBody = document.querySelector('#users-table tbody');
        users.forEach(user => {
            const row = document.createElement('tr');
            row.innerHTML = `
        <td>${user.id}</td>
        <td>${user.username}</td>
        <td>${user.email}</td>
        <td>${user.roles.map(role => role.name).join(', ')}</td>
      `;
            tableBody.appendChild(row);
        });
    })
    .catch(error => console.error('Error fetching users:', error));