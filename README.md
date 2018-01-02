# JChat

[![Build Status](https://travis-ci.org/GreedyAlchemist/Xiangqi.svg?branch=master)](https://travis-ci.org/GreedyAlchemist/Xiangqi)

JChat is a project for originally developed for WPI CS3516 computer networks course. JChat is a client and server chat application, with the support of multiple clients. Currently you are able to login to any JChat servers if running online using their IP address. With the use of java threads JChat supports multiple clients. 

## Getting Started

Download the whole Eclipse project from github or just 4 included source files in src folder. 

### Files
Client.java
Server.java
CThread.java
SThread.java

### Prerequisites

* [Eclipse](https://www.eclipse.org/downloads/) - IDE for java.
* [JRE 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html) - Java Developement Environment or Developent Kit.

### Build

To start the server run the server.java file from Eclipse. 
After the server is running you need to make sure 5566 port is open on your machine and is not blocked by firewall.
After starting the server each client should run the Client.java file. 
After running the file client can choose their name and login to the server with specified IP address by clicking the connect button.


## Authors

* **Vakhtang Margvelashvili** 

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.