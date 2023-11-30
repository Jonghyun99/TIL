let books = []; 

document.addEventListener('DOMContentLoaded', function () {
    loadBooks();
    document.getElementById('add-book-form').addEventListener('submit', addBook);
    document.getElementById('edit-book-form').addEventListener('submit', editBookSubmit);
});

function loadBooks() {
    fetch('/books')
        .then(response => response.json())
        .then(data => {
            books = data;
            const list = document.getElementById('books-list');
            list.innerHTML = '';
            books.forEach(book => {
                const li = document.createElement('li');
                li.innerHTML = `제목: ${book.title}, 저자: ${book.author}
                                <button onclick="showEditForm(${book.id})">수정</button>
                                <button onclick="deleteBook(${book.id})">삭제</button>`;
                list.appendChild(li);
            });
        });
}

function addBook(event) {
    event.preventDefault();
    const title = document.getElementById('title').value;
    const author = document.getElementById('author').value;

    fetch('/books', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ title, author }),
    })
    .then(response => response.json())
    .then(book => {
        document.getElementById('title').value = '';
        document.getElementById('author').value = '';
        loadBooks();
    })
    .catch(error => console.error('Error:', error));
}

function deleteBook(id) {
    fetch(`/books/${id}`, {
        method: 'DELETE',
    })
    .then(() => {
        loadBooks();
    })
    .catch(error => console.error('Error:', error));
}

function showEditForm(id) {
    const book = books.find(b => b.id === id);
    if (book) {
        document.getElementById('edit-id').value = book.id;
        document.getElementById('edit-title').value = book.title;
        document.getElementById('edit-author').value = book.author;
        document.getElementById('edit-form-container').style.display = 'block';
    }
}

function closeEditForm() {
    document.getElementById('edit-form-container').style.display = 'none';
}

function editBookSubmit(event) {
    event.preventDefault();
    const id = document.getElementById('edit-id').value;
    const title = document.getElementById('edit-title').value;
    const author = document.getElementById('edit-author').value;

    fetch(`/books/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ title, author }),
    })
    .then(() => {
        closeEditForm();
        loadBooks();
    })
    .catch(error => console.error('Error:', error));
}
