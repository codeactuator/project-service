---------------USERPROFILE-----------------------
--public class UserProfile {
--@Id
--@GeneratedValue
--private Long id;
--private String name;

INSERT INTO USERPROFILE (id, name) values (1, 'Developer');
INSERT INTO USERPROFILE (id, name) values (2, 'Manager');



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


--------------TASKLOG------------
--public class TaskLog {
--@Id
--@GeneratedValue
--private Long id;
--private String description;
--private float spentHrs;
--private Date createdDate;

INSERT INTO TASKLOG (id, description, spentHrs, createdDate) values (1, 'Making analysis on task', 4.5, null);
INSERT INTO TASKLOG (id, description, spentHrs, createdDate) values (2, 'Development and Unit Testing', 6, null);


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
--private String name;
--private String email;
--@OneToOne
--private UserProfile userProfile;
INSERT INTO WORKFORCE (id, name, email, userProfile) values (1, 'Shekhar KUMAR', 'shekharkumargupta@gmail.com', 1);
INSERT INTO WORKFORCE (id, name, email, userProfile) values (2, 'Lakhvir BANSAL', 'lakhvir@gmail.com', 2);
