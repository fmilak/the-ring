import Post from "../model/Post";
import User from "../model/User";
import LoginStore from "../login/LoginStore";
import {action, observable} from "mobx";

class HomeStore {

    loginStore!: LoginStore;
    @observable allPosts: Array<Post> = new Array<Post>();

    @action
    initPosts = (): void => {
        // todo -> ask back for posts
        let post: Post = new Post();
        post.text = 'nesto';
        post.creator = this.loginStore.user;
        post.creator.name = 'admin';
        post.creator.surname = 'admin';
        this.allPosts.push(post);
        this.allPosts.push(post);
        this.allPosts.push(post);
        this.allPosts.push(post);
        this.allPosts.push(post);
        this.allPosts.push(post);
        this.allPosts.push(post);
    }
    
}

export default HomeStore;