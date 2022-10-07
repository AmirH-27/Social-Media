# WSIT Social Media Task

## Database Design

![image](https://user-images.githubusercontent.com/65022657/193118925-39fd41bd-4826-4670-b0ea-b36aac1ae841.png)

### Task:

- [x] 1. Login and Registration
- [x] 2. Spring Security
- [x] 3. Post
- [x] 4. Like, Dislike, Comment
- [x] 5. 2x Like or Dislike removes reaction
- [x] 6. Friends can be added
- [x] 7. Front-End
- [x] 8. Pagination
- [x] 9. Friends of Friend

## User: 
    
    -> Create
    -> Read
    -> Update
    -> Delete
    -> Login

## Post:

    -> Create
    -> Read
    -> Update
    -> Delete
    -> View All (with reaction and comments)
    -> View All posts by a User (with reaction and comments)

## Comment: 
    
    -> Create
    -> Read
    -> Update
    -> Delete
    -> View comments on a post

## Reaction:

    -> Like
    -> Dislike
    -> Deletes from db when user clicks on the reaction again
    -> Changes form like to dislike and vice versa
    -> View reactions on a post
 
## Friend:

    -> Can add friends from existing users
    -> Can view friend list (returns a list of user objects)
    -> Can view friends of friend (returns a list of user objects)
    -> Not a friend until the reciever accepts the friend request

