import {observer} from "mobx-react";
import React, {ReactElement, useContext, useEffect} from "react";
import {RootStoreContext} from "../App";
import {Redirect, Route} from "react-router";
import {Col, Icon, List, Row} from "antd";
import Post from "../model/Post";
import InfiniteScroll from "react-infinite-scroller";
import {observable} from "mobx";
import HomeStore from "./HomeStore";

const IconText = ({ type, text , homeStore, post}: {type: string, text: string, homeStore: HomeStore, post: Post}) => {
    return (
        <span onClick={() => {
            switch (type) {
                case 'star-o':
                    homeStore.insertPost();
                    break;
                case 'like-o':
                    homeStore.insertPost();
                    break;
                case 'message':
                    homeStore.deletePost(post.id);
                    break;
            }

        }}>
            <Icon type={type} style={{marginRight: 8}}/>
            {text}
        </span>
    )
};

const PostItem = observer(({post, homeStore}: {post: Post, homeStore: HomeStore}): ReactElement => {
    return (
        <List.Item key={post.text} actions={[
            <IconText type="star-o" text="156" key="list-vertical-star-o" homeStore={homeStore} post={post} />,
            <IconText type="like-o" text="156" key="list-vertical-like-o" homeStore={homeStore} post={post} />,
            <IconText type="message" text="2" key="list-vertical-message" homeStore={homeStore} post={post} />,
        ]} extra={
            <img
                width={272}
                alt="logo"
                src="https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png"
            />
        }>
            <div>
                {/* fixme*/}
                <Row>
                    <span>{post.user.name} {post.user.surname}</span>
                </Row>
                <Row>
                    <label>
                        {post.text}
                    </label>
                </Row>
            </div>
        </List.Item>
    )
});

const PostList = observer(({homeStore}: {homeStore: HomeStore}): ReactElement => {
    return (
        <InfiniteScroll initialLoad={false} useWindow={false} pageStart={0} loadMore={() => {}} hasMore>
            <List dataSource={homeStore.allPosts} itemLayout="vertical" renderItem={(post) => <PostItem post={post} homeStore={homeStore} />} />
        </InfiniteScroll>
    )
});

const HomeView: React.FC = observer((): ReactElement => {
    const rootStore = useContext(RootStoreContext);
    const {loginStore, homeStore} = rootStore;
    
    useEffect(() => {
        homeStore.init(loginStore);
    }, []);

    if (!loginStore.isAuthenticated) {
        return (
            <Redirect to="/login" />
        )
    }
    return (
        <div style={{overflow: 'auto', overflowWrap: "normal", height: 830}}>
            <PostList homeStore={homeStore} />
        </div>
    )
});

export default HomeView;