# WSIT_Social_Media_Task

Database Design

![image](https://user-images.githubusercontent.com/65022657/190992140-f0822c30-96fa-4a33-8c38-e2f2c100974f.png)

User_reactions table:

    - user_id (user id of the user who reacted)
    - post_id 
    - reaction (stores Like dislike of null)

User_comments tableP:
    
    - user_id (user id of the user who commented)
    - post_id 
    - reaction (stores the comment)
