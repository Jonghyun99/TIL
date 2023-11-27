const express = require('express');
const router = express.Router();

let books = []; // 임시 데이터 저장소

router.get('/', (req, res) => {
    res.status(200).json(books);
});

router.get('/:id', (req, res) => {
    const book = books.find(b => b.id === parseInt(req.params.id));
    if (!book) return res.status(404).send('The book with the given ID was not found.');
    res.status(200).json(book);
});

router.post('/', (req, res) => {
    const book = {
        id: books.length + 1,
        title: req.body.title,
        author: req.body.author
    };
    books.push(book);
    res.status(201).send(book);
});

router.put('/:id', (req, res) => {
    let book = books.find(b => b.id === parseInt(req.params.id));
    if (!book) return res.status(404).send('The book with the given ID was not found.');

    book.title = req.body.title;
    book.author = req.body.author;
    res.status(200).send(book);
});

router.delete('/:id', (req, res) => {
    books = books.filter(b => b.id !== parseInt(req.params.id));
    res.status(204).send();
});

module.exports = router;
