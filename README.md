#Q&A-Platform
>[Sample Page](http://blog.proinlab.com/qna-example/qna.html), see also [helloworld-qna](http://daejeon.gdg.kr/helloworld/qna), [helloworld-qna-list](http://daejeon.gdg.kr/helloworld/qna/speaker.html)

Q&A Platform written by java(servlet), html, js.

Any requests are welcomed!

##Requirements
- Apache Tomcat
- MySQL 

##Used Libraries
- jquery mobile
- mysql-connector-java(jdbc)

##Database Schema
####table info : 
	CREATE TABLE qna (
		id int(10) unsigned NOT NULL AUTO_INCREMENT,
		speaker varchar(30) NOT NULL DEFAULT '',
		user varchar(100) NOT NULL DEFAULT '',
		msg varchar(200) NOT NULL DEFAULT '',
		date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP 		ON UPDATE CURRENT_TIMESTAMP,
		PRIMARY KEY (`id`)
	) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

##REST API
- ###Resource : /Check (GET)
	- Request
		- `type` (String) : check, login, logout
		- `user` (String) : user's email, only for type 'check'
	- Result(JSON Object)
		- code : true, false
		- user : user's email
	- Example
		- Request
			- http://localhost:8080/qna/Check?type=login&user=test@test.com
		- Result
			- {"code":"true","user":"teset@test.com"}
- ###Resource : /LoadQnA (GET)
	- Request
		- `speaker` (String) : speaker's name, if query is `all`, result display all questions.
	- Result(JSON Array)
		- speaker : speaker's name
		- user : user's email
		- msg : question context
	- Example
		- Request
			- http://localhost:8080/qna/LoadQnA?speaker=all
		- Result
			- [{"speaker":"speaker-1","user":"test@test.com","msg":"something text"},{"speaker":"speaker-2","user":"test@test.com","msg":"something text 2"}]
- ###Resource : /SaveQnA (GET)
	- Request
		- `speaker` : speaker's name
		- `msg` : question context
	- Result
		- code : if data saved at db, return true, else return false.
	- Example
		- Request
			- http://localhost:8080/qna/LoadQnA?speaker=someone&user=test@test.com&msg=sample text
		- Result
			- {"code","true"}
			
## Settings
- ###in Java Source [src/com.proinlab.util/QnASetting.java]
	- HOST : Database Location, default `localhost`.
	- PORT : Database Port, default `3306`.
	- DB_USER : Database user id, default `root`.
	- DB_PASSWORD : Database password, default `null`.
	- DB_NAME : Database name, default `qna_example`, this is not table name.
- ###in JavaScript [WebContent/global.js]
	- host : Tomcat Hosts, default `115.88.201.42`, is my host.
	- port : Tomcat Port, default `8080`.
	- appName : Tomcat webapps name, default `qna`.
	- refreshTime : ajax reload data delay time, default `1000`.
	- textLimit : minimum question text length, default `5`.
