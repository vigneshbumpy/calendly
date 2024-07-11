Calendly Project - Initial Draft

Let's break this down into steps:

MVP Features:
    - Set user availability
    - Show user availability
    - Find schedule overlap between 2 users

API Design

/api/v1/users/<user_id>/availability
GET: Get user's availability
POST: Set user's availability

/api/v1/schedule/overlap
GET: Find overlap between two users' schedules

Data Model:
We'll use a sql lite database for the MVP. 
In a production environment, this would be replaced with a prod database.

Simple API implementation
setUserAvailability(userId: String, slots: List<String)
getUserAvailability(userId: String): List<String>
getOverlap(userId1: String, userId2: String): List<String>

Final Implemented 
[calendly-service](calendly-service)
This has the calendly springboot server starts - [CalendlyApplication.kt](calendly-service%2Fsrc%2Fmain%2Fkotlin%2Fcom%2Fcalendly%2FCalendlyApplication.kt)
This has two resource 
[AvailabilityResource.kt](calendly-service%2Fsrc%2Fmain%2Fkotlin%2Fcom%2Fcalendly%2Fresource%2FAvailabilityResource.kt)
All user availability related rest api endpoints - Get Availability, Set Availability and findOverlap
[UserResource.kt](calendly-service%2Fsrc%2Fmain%2Fkotlin%2Fcom%2Fcalendly%2Fresource%2FUserResource.kt)
All User create and get resource exist here 

[calendly-core](calendly-core)
This has all the core logics like dal dao services and mangers for the appilication
[config](calendly-core%2Fsrc%2Fmain%2Fkotlin%2Fcom%2Fcalendly%2Fconfig)
[dal](calendly-core%2Fsrc%2Fmain%2Fkotlin%2Fcom%2Fcalendly%2Fdal)
[dao](calendly-core%2Fsrc%2Fmain%2Fkotlin%2Fcom%2Fcalendly%2Fdao)
[db](calendly-core%2Fsrc%2Fmain%2Fkotlin%2Fcom%2Fcalendly%2Fdb)
[manager](calendly-core%2Fsrc%2Fmain%2Fkotlin%2Fcom%2Fcalendly%2Fmanager)
[model](calendly-core%2Fsrc%2Fmain%2Fkotlin%2Fcom%2Fcalendly%2Fmodel)
[service](calendly-core%2Fsrc%2Fmain%2Fkotlin%2Fcom%2Fcalendly%2Fservice)

[calendly-client](calendly-client)
Can be used to expose client to external service (Not Implemented)

[calendly-fe](calendly-fe)
Can be used to build the UI for the application (Not Implemented)

This application uses sql lite for DB

Question specific answer

- Have you thought through what a good MVP looks like? Does your API support that? 
  - Yes all the API support has been implemented for the MVP that was designed
- What trade-offs are you making in your design?
  - Not very major, just one trade off, for a quick portablity I have used sql lite,
    - Though it does not provide concurrency
    - Single point of failure
  Took this trade off for quick development.
  - Client Impl is not complete, wanted to expose client for other service. Took restapi route than a client 
  
- Working code - we should be able to pull and hit the code locally. Bonus points if deployed somewhere.
  - Yes working and deployed - https://bdfc-152-58-232-250.ngrok-free.app/swagger-ui/
- Any good engineer will make hacks when necessary - what are your hacks and why?
  - Had to make @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) rather than GenerationType.IDENTITY as the Identity is not working as expected
  - User table is just with email and id, need to add elobrate entity
