package com.jmpark.simpleBoard.domain;

import java.util.Date;
import java.util.Objects;

public class CommentDto {

    private Integer commentNo;
    private Integer boardNo;
    private Integer pCommentNo;     // 부모 댓글 번호
    private String comment;
    private String writer;
    private char status;
    private Date regDate;
    private Date modDate;
    private Date deleteDate;


    public CommentDto() { };

    public CommentDto(Integer boardNo, Integer pCommentNo, String comment, String writer) {
        this.boardNo = boardNo;
        this.pCommentNo = pCommentNo;
        this.comment = comment;
        this.writer = writer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDto that = (CommentDto) o;
        return Objects.equals(commentNo, that.commentNo) && Objects.equals(boardNo, that.boardNo) && Objects.equals(pCommentNo, that.pCommentNo) && Objects.equals(comment, that.comment) && Objects.equals(writer, that.writer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentNo, boardNo, pCommentNo, comment, writer);
    }

    public Integer getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(Integer commentNo) {
        this.commentNo = commentNo;
    }

    public Integer getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(Integer boardNo) {
        this.boardNo = boardNo;
    }

    public Integer getpCommentNo() {
        return pCommentNo;
    }

    public void setpCommentNo(Integer pCommentNo) {
        this.pCommentNo = pCommentNo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "commentNo=" + commentNo +
                ", boardNo=" + boardNo +
                ", pCommentNo=" + pCommentNo +
                ", comment='" + comment + '\'' +
                ", writer='" + writer + '\'' +
                ", status=" + status +
                ", regDate=" + regDate +
                ", modDate=" + modDate +
                ", deleteDate=" + deleteDate +
                '}';
    }
}