const express = require('express');
const app = express();
const booksRouter = require('./books'); // books 라우터 모듈 가져오기

app.use(express.json());
app.use(express.static('public'));
app.use('/books', booksRouter); // books 라우터를 '/books' 경로에 연결

const port = process.env.PORT || 3000;
app.listen(port, () => console.log(`Listening on port ${port}...`));
