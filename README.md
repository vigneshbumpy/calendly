Calendly Project

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
