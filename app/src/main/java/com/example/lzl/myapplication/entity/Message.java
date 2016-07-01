package com.example.lzl.myapplication.entity;
import java.util.ArrayList;

public class Message {
    public String id;
    public String authorId;
    public String title;
    public String summary;
    public String content;
    public String image;
    public String imageType;
    public String url;
    public String source;
    public boolean liked;
    public int likeCount;
    public int style;// MessageStyleShort 1 MessageStyleLong 2  MessageStyleUrl 3
    public int type;//1 new //2 hot
    public long createdAt;
    //public String[] subjectIds;
    public ArrayList<Stock> stocks;
    public String shareUrl;

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", authorId='" + authorId + '\'' +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", imageType='" + imageType + '\'' +
                ", url='" + url + '\'' +
                ", source='" + source + '\'' +
                ", liked=" + liked +
                ", likeCount=" + likeCount +
                ", style=" + style +
                ", type=" + type +
                ", createdAt=" + createdAt +
                ", stocks=" + stocks +
                ", shareUrl='" + shareUrl + '\'' +
                '}';
    }
}
