Tutorial: Task-Tracking-App
This Task-Tracking-App is designed to help users organize their work by creating and managing task lists and the individual tasks within them. It allows you to create, view, update, and delete both task lists and tasks, making it easy to keep track of your progress and priorities across the application's user interface.

Visual Overview
flowchart TD
    A0["Domain Entities (Backend)
"]
    A1["REST API Controllers (Backend)
"]
    A2["Business Services (Backend)
"]
    A3["Data Repositories (Backend)
"]
    A4["Data Transfer Objects (DTOs) & Mappers (Backend)
"]
    A5["Frontend Application State Management (AppProvider)
"]
    A6["Frontend React Components/Screens
"]
    A7["Frontend Routing
"]
    A6 -- "Uses state & actions" --> A5
    A5 -- "Calls API" --> A1
    A1 -- "Delegates logic" --> A2
    A1 -- "Exchanges DTOs" --> A4
    A4 -- "Maps entities" --> A0
    A2 -- "Uses repositories" --> A3
    A3 -- "Manages entities" --> A0
    A7 -- "Renders views" --> A6
Chapters
Frontend React Components/Screens
Frontend Routing
Frontend Application State Management (AppProvider)
REST API Controllers (Backend)
Domain Entities (Backend)
Data Transfer Objects (DTOs) & Mappers (Backend)
Business Services (Backend)
Data Repositories (Backend)
