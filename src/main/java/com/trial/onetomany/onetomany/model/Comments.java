package com.trial.onetomany.onetomany.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "comments")
public class Comments extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Lob
    private String text;

// one post can have many comments,
//    post-id is foreign key to the comments table(@JoinColumn)
// @onDelete() is same as  @OneToOne(cascade=CascadeType.REMOVE)---->>>>
//    then deleting the comment will also delete the post
//    for a table with more than two fk,,,---->>>>
//    @JoinColumns(value = { @JoinColumn(name = "JOB_ID", referencedColumnName = "TASK_ID_JOB"),
//                       @JoinColumn(name = "TASK_ID", referencedColumnName = "TASK_ID_TASK") })
// cascading types: @OneToMany(cascade = {
//       CascadeType.PERSIST,
//       CascadeType.MERGE, CascadeType.REFRESH},
//    mappedBy = "post")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
//    used to ignore the logical property used in serialization and deserilization.
    private Post post;

    public Comments() {
    }

    public Comments(@NotNull String text, Post post) {
        this.text = text;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public Comments setId(Long id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public Comments setText(String text) {
        this.text = text;
        return this;
    }

    public Post getPost() {
        return post;
    }

    public Comments setPost(Post post) {
        this.post = post;
        return this;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", post=" + post +
                '}';
    }
}
