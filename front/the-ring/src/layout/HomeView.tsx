import {observer} from "mobx-react";
import React, {ReactElement, useContext, useEffect} from "react";
import {RootStoreContext} from "../App";
import {Redirect, Route} from "react-router";
import {Col, Icon, List, Row} from "antd";
import Post from "../model/Post";
import InfiniteScroll from "react-infinite-scroller";

const IconText = ({ type, text }: {type: string, text: string}) => {
    return (
        <span>
            <Icon type={type} style={{marginRight: 8}}/>
            {text}
        </span>
    )
};

const PostItem = observer(({post}: {post: Post}): ReactElement => {
    return (
        <List.Item key={post.text} actions={[
            <IconText type="star-o" text="156" key="list-vertical-star-o" />,
            <IconText type="like-o" text="156" key="list-vertical-like-o" />,
            <IconText type="message" text="2" key="list-vertical-message" />,
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
                    <span>{post.creator.name} {post.creator.surname}</span>
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

const HomeView: React.FC = observer((): ReactElement => {
    const rootStore = useContext(RootStoreContext);
    const {loginStore, homeStore} = rootStore;
    homeStore.loginStore = loginStore;
    homeStore.initPosts();

    useEffect(() => {
        homeStore.initPosts();
    }, []); // todo -> cant use it here because of navigation

    if (!loginStore.isAuthenticated) {
        return (
            <Redirect to="/login" />
        )
    }
    return (
        <div style={{overflow: 'auto', overflowWrap: "normal", height: 830}}>
            <InfiniteScroll initialLoad={false} useWindow={false} pageStart={0} loadMore={() => {}} hasMore>
                <List dataSource={homeStore.allPosts} itemLayout="vertical" renderItem={(post) => <PostItem post={post} />} />
            </InfiniteScroll>
        </div>
    )
});

export default HomeView;