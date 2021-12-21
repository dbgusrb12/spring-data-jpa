package com.example.demospringdata;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    // cascade 로 entity의 상태를 전파 시킬 수 있다. (어떤 상태를 전파시킬건지 정의 할 수 있음)
    // 해당 Post 객체가 persistent 상태로 변경 될 때 참조되는 Comment 리스트 객체들도 persistent 상태로 전파된다.
    // 해당 Post 객체가 removed 상태로 변경 될 때 참조되는 Comment 리스트 객체들도 removed 상태로 전파된다.
//    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})

    // 모든 상태를 전파 시키는 옵션 (CascadeType.ALL)
//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    // FETCH 전략을 설정하는 것 (OneToMany 는 default 가 LAZY, ManyToOne 은 default 가 EAGER)
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();

    public void addComment(Comment comment) {
        this.getComments().add(comment);
        comment.setPost(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
