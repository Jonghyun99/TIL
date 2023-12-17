// public/script.js
const socket = io();

const form = document.getElementById('form');
const input = document.getElementById('input');
const messages = document.getElementById('messages');

form.addEventListener('submit', function(e) {
    e.preventDefault();
    if (input.value) {
        socket.emit('chat message', input.value);
        input.value = '';
    }
});

socket.on('chat message', function(msg) {
    const item = document.createElement('li');
    item.textContent = `원본: ${msg.original} / 번역: ${msg.translated}`;
    messages.appendChild(item);
    window.scrollTo(0, document.body.scrollHeight);
});