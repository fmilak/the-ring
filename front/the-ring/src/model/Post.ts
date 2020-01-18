import User from "./User";

class Post {

    id = 0;
    text = '';
    picture: any; // todo -> type for saving picture
    user: User = new User();
    likes: Array<User> = new Array<User>();
    dislikes: Array<User> = new Array<User>();
    
}

export default Post;