![Architecture](https://github.com/user-attachments/assets/f47d934a-1056-43e7-beee-065bd5ed8c6c)

---------------------------------------------------------------------------------------------
![domain](https://github.com/user-attachments/assets/e47ffeb0-333c-4c50-a88b-2d0ae7b98695)

---------------------------------------------------------------------------------------------
![class](https://github.com/user-attachments/assets/6c3d210c-6600-4096-9746-d9ff1ead04d7)

---------------------------------------------------------------------------------------------
![ERD](https://github.com/user-attachments/assets/1b059e21-b18d-43db-b9de-08ecd76f8282)


If we start with the Tasks lists page, we will need a way of getting a list of all task-lists and a way  create task lists:

GET       /task-lists    List Task Lists
POST      /task-lists    Create Task Lists

On the Task List Page, we’ll need a way to edit a task list and delete a task list. Also, because of page refreshes and the like, it would also be beneficial to to get the details of one task list by using it’s ID:

GET     /task-lists/{task_list_id}    Get Task List by ID
PUT     /task-lists/{task_list_id}    Update Task list 
DELETE  /task-lists/{task_list_id}    Delete Task List

Moving onto Tasks, the task list page will need a way of listing all tasks for a task list and creating a task: 

GET       /task-lists/{task_list_id}/tasks  List Tasks
POST      /task-lists/{task_list_id}/tasks  Create Task

Once we have created a task we would need a way to edit the task and delete the task:

GET     /task-lists/{task_list_id}/tasks/{task_id}    Get Task by ID
PUT     /task-lists/{task_list_id}/tasks/{task_id}    Update Task
DELETE  /task-lists/{task_list_id}/tasks/{task_id}    Delete Task

As tasks have a “has a” relationship with tasks and cannot exist without being associated with a task list, we use a URL structure for tasks which also specifies the task list ID.


So, at least initially, we can fulfill the requirements of the project by implementing the following endpoints:

GET     /task-lists                                   List Task Lists
POST    /task-lists                                   Create Task Lists
GET     /task-lists/{task_list_id}                    Get Task List by ID
PUT     /task-lists/{task_list_id}                    Update Task list 
DELETE  /task-lists/{task_list_id}                    Delete Task List

GET     /task-lists/{task_list_id}/tasks              List Tasks
POST    /task-lists/{task_list_id}/tasks              Create Task
GET     /task-lists/{task_list_id}/tasks/{task_id}    Get Task by ID
PUT     /task-lists/{task_list_id}/tasks/{task_id}    Update Task
DELETE  /task-lists/{task_list_id}/task/{task_id}     Delete Task

Summary

The REST API offers create, read, update and delete (CRUD) operations for Task and TaskList.

The Task URL path includes the TaskList URL path, representing their “has a” relationship.
