const express = require('express');
const cors = require('cors');
const app = express();
const port = 3000;

app.use(cors());
app.use(express.json());

app.post('/tasks', (req, res) => {
    const task = req.body.task;
    console.log(`Task received: ${task}`);
    // 여기에 데이터베이스 로직을 추가할 수 있습니다.
    res.json({ task: task, status: 'success' });
});

app.listen(port, () => {
    console.log(`Server listening at http://localhost:${port}`);
});
