# WSIT_Social_Media_Task

Database Design

![image](https://user-images.githubusercontent.com/65022657/190987577-e8c5482f-d21d-47c7-bcf4-85323fb73f4b.png)

User_reactions table:

    - user_id (user id of the user who reacted)
    - post_id 
    - reaction (stores Like dislike of null)

User_comments tableP:
    
    - user_id (user id of the user who commented)
    - post_id 
    - reaction (stores the comment)
