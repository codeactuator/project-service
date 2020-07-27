-----------------TASKSTATUS----------------
--public class TaskStatus {
--@Id
--@GeneratedValue
--private Long id;
--private String name;

DROP TABLE IF EXISTS TASKSTATUS;

CREATE TABLE TASKSTATUS (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);



-----------------TASKTYPE----------------
--public class TaskType {
--@Id
--@GeneratedValue
--private Long id;
--private String name;

DROP TABLE IF EXISTS TASKTYPE;

CREATE TABLE TASKTYPE (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);


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


DROP TABLE IF EXISTS TASK;

CREATE TABLE TASK (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  taskType INT DEFAULT NULL,
  assignee INT DEFAULT NULL,
  assignedBy INT DEFAULT NULL,
  createdOn DATE DEFAULT NULL,
  status INT DEFAULT NULL,
  subTasks INT DEFAULT NULL,
  logs INT DEFAULT NULL
);


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

DROP TABLE IF EXISTS PROJECT;

CREATE TABLE PROJECT (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  tasks INT DEFAULT NULL,
  resources INT DEFAULT NULL,
  created DATE DEFAULT NULL
);


-----------------WORKFORCE----------------
--@Id
--@GeneratedValue
--private Long id;
--private String employeeCode;
--private String name;
--private String email;
--@OneToOne
--private Role role;



DROP TABLE IF EXISTS WORKFORCE;

CREATE TABLE WORKFORCE (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  employeeCode VARCHAR(250) NOT NULL,
  name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL,
  role INT DEFAULT NULL
);