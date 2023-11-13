function addTask() {
    var newTask = document.getElementById('newTask').value;
    if (newTask.trim() === '') {
        alert('할 일을 입력하세요!');
        return;
    }
    var li = document.createElement('li');
    li.textContent = newTask + ' ';

    var deleteButton = document.createElement('button');
    deleteButton.textContent = '제거';
    deleteButton.onclick = function() {
        li.remove();
    };

    li.appendChild(deleteButton);
    document.getElementById('taskList').appendChild(li);
    document.getElementById('newTask').value = '';
}
