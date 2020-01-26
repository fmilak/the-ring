import Post from "../model/Post";
import User from "../model/User";
import LoginStore from "../login/LoginStore";
import {action, observable} from "mobx";
import RestService from "../service/RestService";
import RestInit from "../model/api/RestInit";
import ApiResponse from "../model/api/ApiResponse";

class HomeStore {

    loginStore!: LoginStore;
    @observable allPosts: Array<Post> = new Array<Post>();

    init = (loginStore: LoginStore): void => {
        this.loginStore = loginStore;
        this.initPosts();
    };
    
    @action
    initPosts = (): void => {
        const restInit: RestInit = new RestInit();
        restInit.url = `/api/post`;
        restInit.method = 'GET';
        restInit.header = {
            'Authorization': `bearer ${localStorage.getItem('token')}`,
            'Content-Type': 'application/json'
        };
        RestService.fetch(restInit, this.handleInitPosts).catch(err => console.log(err));
    };

    insertPost = (): void => {
        const restInit: RestInit = new RestInit();
        restInit.url = `/api/post/insert/${this.loginStore.user.username}`;
        restInit.method = 'POST';
        restInit.header = {
            'Authorization': `bearer ${localStorage.getItem('token')}`,
            'Content-Type': 'application/json',
        };
        // todo -> this is test, remove after
        let post: Post = new Post();
        post.text = 'nesto';
        post.user = this.loginStore.user;
        post.user.name = 'admin';
        post.user.surname = 'admin';
        post.user.role = 'ADMIN';
        restInit.body = JSON.stringify(post);

        RestService.fetch(restInit, this.handleInsertPost).catch(err => console.log(err));
    };

    uploadPicture = (input: any, postId: number): void => {
        if (this.loginStore.user.currentLimit >= this.loginStore.user.maxLimit) {
            return;
        }
        const file: any = input.target.files[0];
        const formData = new FormData();
        formData.append("image", file);

        const restInit: RestInit = new RestInit();
        restInit.url = `/api/post/upload/${postId}/${this.loginStore.user.username}`;
        restInit.method = 'POST';
        restInit.header = {
            'Authorization': `bearer ${localStorage.getItem('token')}`,
        };
        restInit.body = formData;

        RestService.fetch(restInit, this.handleInsertPost).catch(err => console.log(err));
    };
    
    handleInsertPost = (apiResponse: ApiResponse): void => {
        if (apiResponse.success) {
            console.log(apiResponse);
            this.initPosts();
        } else {
            console.log(apiResponse.message);
        }
    };

    @action
    handleInitPosts = (apiResponse: ApiResponse): void => {
        this.allPosts = new Array<Post>();
        if (apiResponse.success) {
            apiResponse.data.forEach((post: Post) => {
                this.allPosts.push(post);
            });
        } else {
            console.log(apiResponse.message);
        }
    };

    deletePost = (id: number): void => {
        const restInit: RestInit = new RestInit();
        restInit.url = `/api/post/delete/${id}`;
        restInit.method = 'delete';
        restInit.header = {
            'Authorization': `bearer ${localStorage.getItem('token')}`,
            'Content-Type': 'application/json',
        };

        RestService.fetch(restInit, this.handleDeletePost).catch(err => console.log(err));
    };

    handleDeletePost = (apiResponse: ApiResponse): void => {
        if (apiResponse.success) {
            this.initPosts()
        } else {
            console.log(apiResponse.message);
        }
    }
    
}

export default HomeStore;