# H2-HealthHelp

the H2 project born as a project for the course of Pervasive Computing for Unibo's master degree in Ingegneria e Scienze informatiche @ Cesena.

## Course reference

This prototype was presented as our final project for the *Pervasive Computing* course:  
https://www.unibo.it/it/didattica/insegnamenti/insegnamento/2017/412647

## Report and documentation

- [A complete project report in Italian language](report.pdf)  

## To Execute

To start our application, is necessary to:
- run a single instance of the data centre (datacentre/src/main/java/-
core/Main.java)
- run a single instance of both association RESTful API, application
RESTful API and H2 client. In order to do that, it is necessary to run
the following instructions:
  - npm install
  - node bin/www
- run the patient’s control unit:
  - remotly connect to the control unit
  - enter in the H2 folder
  - run the server with command node bin/www

Started in Academy Year 2017/2018.
