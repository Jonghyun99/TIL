// server.js
require('dotenv').config();
const express = require('express');
const request = require('request');
const socketIO = require('socket.io');
const http = require('http');

const app = express();
const server = http.createServer(app);
const io = socketIO(server);

const CLIENT_ID = 'k0n0yk9nmq'; // 네이버 클라우드 플랫폼에서 발급받은 ID
const CLIENT_SECRET = 'jBpJ8teBQXQLxHpaBAONg56YChn0bBhFnNAhG2jJ'; // 네이버 클라우드 플랫폼에서 발급받은 Secret

app.use(express.static('public'));

io.on('connection', (socket) => {
    console.log('사용자가 연결되었습니다.');

    socket.on('chat message', (msg) => {
        translate(msg, 'en', (translatedText) => {
            io.emit('chat message', { original: msg, translated: translatedText });
        });
    });

    socket.on('disconnect', () => {
        console.log('사용자가 연결을 끊었습니다.');
    });
});

function translate(text, targetLanguage, callback) {
    const api_url = 'https://naveropenapi.apigw.ntruss.com/nmt/v1/translation';
    const options = {
        url: api_url,
        form: { source: 'ko', target: targetLanguage, text: text },
        headers: { 'X-NCP-APIGW-API-KEY-ID': CLIENT_ID, 'X-NCP-APIGW-API-KEY': CLIENT_SECRET }
    };

    request.post(options, (error, response, body) => {
        if (!error && response.statusCode == 200) {
            const translatedText = JSON.parse(body).message.result.translatedText;
            callback(translatedText);
        } else {
            console.error('번역 중 오류 발생:', error);
            callback(text);
        }
    });
}

const PORT = process.env.PORT || 3000;
server.listen(PORT, () => {
    console.log(`서버가 ${PORT}번 포트에서 실행중입니다.`);
});
