---------------USERPROFILE-----------------------
--public class UserProfile {
--@Id
--@GeneratedValue
--private Long id;
--private String name;

INSERT INTO USERPROFILE (name) values ('Developer');
INSERT INTO USERPROFILE (name) values ('Manager');



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

INSERT INTO TASKTYPE (name) values ('NEW FEATURE');
INSERT INTO TASKTYPE (name) values ('BUG');
INSERT INTO TASKTYPE (name) values ('ENHANCEMENT');
INSERT INTO TASKTYPE (name) values ('ANALYSIS');



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

INSERT INTO TASK (name) values ('Create Login Screen');
INSERT INTO TASK (name) values ('Backend implementation for Login feature');


--------------TASKLOG------------
--public class TaskLog {
--@Id
--@GeneratedValue
--private Long id;
--private String description;
--private float spentHrs;
--private Date createdDate;

INSERT INTO TASKLOG (description, spentHrs, createdDate) values ('Making analysis on task', 4.5, null);
INSERT INTO TASKLOG (description, spentHrs, createdDate) values ('Development and Unit Testing', 6, null);


-----------------WORKFORCE----------------
--public class Workforce {
--@Id
--@GeneratedValue
--private Long id;
--private String name;
--private String email;
--@OneToOne
--private UserProfile userProfile;
INSERT INTO WORKFORCE (name, email) values ('Shekhar KUMAR', 'shekharkumargupta@gmail.com');
INSERT INTO WORKFORCE (name, email) values ('Lakhvir BANSAL', 'lakhvir@gmail.com');


-----------------PROJECT----------------
--public class Project{
--@Id
--@GeneratedValue
--private Long id;
--private String name;
--@OneToMany
--private Set<Task> tasks;
--@OneToMany
--private Set<Workforce> resources;
--private Date created;


INSERT INTO PROJECT (name) values ('White Label');
