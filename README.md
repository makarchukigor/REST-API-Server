# REST-API-Server
Simple example REST API Server with  Spring Boot,  JWT, Gradle 

REST API Server for creation of messages, Authorization by the JWT (only for create messages). 
Ability edit, delete, close and open access to messages. 
After message creation, it become public. 
Create message can only user received token;
Gradle launch Server on port 9099.


REST API
        
        Launch by Gradle port = 9099
        Launch by Eclipce port = 8080
	
        GET    http://{Server name}:{Port}/login - for get tocken
        POST   http://{Server name}:{Port}/api/messages/new?text={message text} - for post new message (token must be in authorization section)
        GET    http://{Server name}:{Port}/api/messages/ - for get all public messages
        DELETE http://{Server name}:{Port}/api/messages/del/{message number} - for delete message
        PUT    http://{Server name}:{Port}/api/messages/close/{message number} - for close message
        PUT    http://{Server name}:{Port}/api/messages/open/{message number} - for open message 
        PUT    http://{Server name}:{Port}/api/messages/edit/{message number}?text={new message text}  for edid message     