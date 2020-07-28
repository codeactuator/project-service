-----------------TASKSTATUS----------------
--public class TaskStatus {
--@Id
--@GeneratedValue
--private Long id;
--private String name;

INSERT INTO TASKSTATUS (name) values ('NOT STARTED');
INSERT INTO TASKSTATUS (name) values ('IN PROGRESS');
INSERT INTO TASKSTATUS (name) values ('DONE');


-----------------TASKTYPE----------------

--public class TaskType {
--@Id
--@GeneratedValue
--private Long id;
--private String name;

INSERT INTO TASKTYPE (id, name) values (1, 'NEW FEATURE');
INSERT INTO TASKTYPE (id, name) values (2, 'BUG');
INSERT INTO TASKTYPE (id, name) values (3, 'ENHANCEMENT');
INSERT INTO TASKTYPE (id, name) values (4, 'ANALYSIS');



-----------------TASK----------------
--public class Task {
--@Id
--@GeneratedValue
--private Long id;
--private String name;
--@OneToOne
--private TaskType taskType;
--private Long assignee;
--private Long assignedBy;
--private Date createdOn;
--@OneToOne
--private TaskStatus status;
--@OneToMany
--private Set<Task> subTasks;
--@OneToMany
--private Set<TaskLog> logs;

INSERT INTO TASK (id, name, taskType, assignee, assignedBy, createdOn, status, subTasks, logs) values (1, 'Create Login Screen', 1, 1, 2, null, 1, null, null);
INSERT INTO TASK (id, name, taskType, assignee, assignedBy, createdOn, status, subTasks, logs) values (2, 'Backend implementation for Login feature', 1, 1, 2, null, 1, null, null);



-----------------PROJECT----------------
--public class Project{
--@Id
--@GeneratedValue
--private Long id;
--private String name;
--@OneToMany
--private Set<Task> tasks;
--@ElementCollection
--private Set<Workforce> resources;
--private Date created;


INSERT INTO PROJECT (id, name, tasks, resources, created) values (1, 'Create Login feature', 1, 1, null);




-----------------WORKFORCE----------------
--public class Workforce {
--@Id
--@GeneratedValue
--private Long id;
--private String employeeCode;
--private String name;
--private String email;
--@OneToOne
--private Role role;
INSERT INTO WORKFORCE (id, employeeCode, name, email) values (1, 'E1001', 'Shekhar KUMAR', 'shekharkumargupta@gmail.com');
INSERT INTO WORKFORCE (id, employeeCode, name, email) values (2, 'E1002', 'Lakhvir BANSAL', 'lakhvir@gmail.com');
