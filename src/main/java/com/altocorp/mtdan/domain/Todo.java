package com.altocorp.mtdan.domain;

import java.util.List;
import java.util.Objects;

public class Todo {

    private long id;
    private String project;
    private String content;
    private List<String> labels;
    private int order;
    private int indent;
    private int priority;
    private int commentCount;
    private String created;
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getIndent() {
        return indent;
    }

    public void setIndent(int indent) {
        this.indent = indent;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id == todo.id &&
                order == todo.order &&
                indent == todo.indent &&
                priority == todo.priority &&
                commentCount == todo.commentCount &&
                Objects.equals(project, todo.project) &&
                Objects.equals(content, todo.content) &&
                Objects.equals(labels, todo.labels) &&
                Objects.equals(created, todo.created) &&
                Objects.equals(url, todo.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, project, content, labels, order, indent, priority, commentCount, created, url);
    }
}
