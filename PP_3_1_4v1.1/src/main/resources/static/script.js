function fetchAndDisplayUsers() {
    fetch('http://localhost:8080/admin')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Получение ответа как текст
        })
        .then(html => {
            const parser = new DOMParser();
            const doc = parser.parseFromString(html, 'text/html');
            const tableBody = doc.querySelector('#users-table tbody');

            if (tableBody) {
                const destinationTableBody = document.querySelector('#users-table tbody');
                destinationTableBody.innerHTML = ''; // Очистить таблицу перед добавлением новых данных
                destinationTableBody.innerHTML = tableBody.innerHTML; // Вставка HTML в целевую таблицу
            } else {
                console.error('Table body not found in the HTML');
            }
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
}
document.addEventListener('DOMContentLoaded', () => {
    fetchAndDisplayUsers();
});
