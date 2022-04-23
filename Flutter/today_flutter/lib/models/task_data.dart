import 'package:flutter/foundation.dart';
import 'package:today_flutter/models/task.dart';

class TaskData extends ChangeNotifier {
  List<Task> tasks = [
    Task(name: 'Buy milk'),
    Task(name: 'Buy eggs'),
    Task(name: 'Buy bread'),
  ];

  get taskCount => tasks.length;
}
