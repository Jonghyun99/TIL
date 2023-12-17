function addTask() {
    var taskInput = document.getElementById('taskInput');
    var task = taskInput.value;

    if (task.trim() === '') {
        alert('할 일을 입력해주세요.');
        return;
    }

    fetch('http://localhost:3000/tasks', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ task: task }),
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        displayTask(data.task);
    })
    .catch((error) => {
        console.error('Error:', error);
    });

    taskInput.value = '';
}

function displayTask(task) {
    var taskList = document.getElementById('taskList');
    var listItem = document.createElement('li');
    listItem.textContent = task;
    taskList.appendChild(listItem);
}
