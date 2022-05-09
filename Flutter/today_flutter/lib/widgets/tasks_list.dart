import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:today_flutter/models/task_data.dart';
import 'task_tile.dart';

class TasksList extends StatelessWidget {
  TasksList({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Consumer<TaskData>(
      builder: (BuildContext context, taskData, Widget? child) {
        return ListView.builder(
          itemBuilder: (context, index) {
            final task = taskData.tasks![index];
            return TextButton(
              style: TextButton.styleFrom(
                padding: const EdgeInsets.all(0.0),
              ),
              onPressed: () {},
              onLongPress: () {
                showDialog(
                  context:context,
                  barrierDismissible: true,
                  builder: (BuildContext context) {
                    return AlertDialog(
                      title: Text('delete Todo?'),
                      content: SingleChildScrollView(
                        child: ListBody(
                          children: [Text('AlertDialog'), Text('Press Okay')],
                        ),
                      ),
                      actions: [
                        TextButton(
                          child: Text('Delete!'),
                          onPressed: () {
                            taskData.deleteTask(index);
                            Navigator.pop(context);
                          },
                        ),
                        TextButton(
                          onPressed: () {
                            Navigator.pop(context);
                          },
                          child: Text('Cancel'),
                        ),
                      ],
                    );
                  }
                );
              },
              child: TaskTile(
                taskTitle: task.name,
                isChecked: task.isDone,
                checkboxCallback: (bool? checkboxState) {
                  taskData.updateTask(task);
                },
              ),
            );
          },
          itemCount: taskData.taskCount,
        );
      },
    );
  }
}
