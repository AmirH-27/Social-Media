# WSIT_Social_Media_Task

Database Design

![image](https://user-images.githubusercontent.com/65022657/191000109-8e9e2010-9419-41a8-928f-0f738040a337.png)

User_reactions table:

    - user_id (user id of the user who reacted)
    - post_id 
    - reaction (stores Like dislike of null)

User_comments tableP:
    
    - user_id (user id of the user who commented)
    - post_id 
    - reaction (stores the comment)
