import 'package:flutter/foundation.dart';
import 'package:today_flutter/models/task.dart';
import 'dart:collection';

class TaskData extends ChangeNotifier {
  List<Task> _tasks = [
    Task(name: 'Buy milk'),
    Task(name: 'Buy eggs'),
    Task(name: 'Buy bread'),
  ];

  get taskCount => _tasks.length;

  UnmodifiableListView<Task>? get tasks {
    return UnmodifiableListView(_tasks);
  }

  void addTask(String newTaskTitle){

    final task = Task(name: newTaskTitle);
    _tasks.add(task);
    notifyListeners();
  }

  void updateTask(Task task){
    task.toggleDone();
    notifyListeners();
  }

  void deleteTask(int index){
    _tasks.removeAt(index);
    notifyListeners();
  }

}
