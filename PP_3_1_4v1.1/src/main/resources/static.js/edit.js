document.querySelector('#users-table').addEventListener('click', event => {
    if (event.target.classList.contains('edit-btn')) {
        const userId = event.target.dataset.id;
        fetch(`/admin/${userId}`)
            .then(response => response.json())
            .then(user => {
                document.querySelector('#edit-id').value = user.id;
                document.querySelector('#edit-username').value = user.username;
                document.querySelector('#edit-email').value = user.email;
                document.querySelector('#edit-password').value = '';
                const rolesSelect = document.querySelector('#edit-roles');
                rolesSelect.innerHTML = '';
                user.roles.forEach(role => {
                    const option = document.createElement('option');
                    option.value = role.name;
                    option.selected = true;
                    rolesSelect.appendChild(option);
                });
                $('#edit-modal').modal('show');
            });
    } else if (event.target.classList.contains('delete-btn')) {
        const userId = event.target.dataset.id;
        fetch(`/admin/${userId}`, { method: 'DELETE' })
            .then(() => {
                event.target.closest('tr').remove();
            });
    }
});

document.querySelector('#save-edits').addEventListener('click', event => {
    const user = {
        id: document.querySelector('#edit-id').value,
        username: document.querySelector('#edit-username').value,
        email: document.querySelector('#edit-email').value,
        password: document.querySelector('#edit-password').value,
        roles: Array.from(document.querySelector('#edit-roles').selectedOptions).map(option => ({ name: option.value }))
    };
    fetch(`/admin/${user.id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(user)
    })
        .then(() => {
            $('#edit-modal').modal('hide');
            
            const row = document.querySelector(`#users-table tbody tr[data-id="${user.id}"]`);
            row.querySelector('td:nth-child(2)').textContent = user.username;
            row.querySelector('td:nth-child(3)').textContent = user.email;
            row.querySelector('td:nth-child(4)').textContent = user.roles.join(', ');
        });
});
