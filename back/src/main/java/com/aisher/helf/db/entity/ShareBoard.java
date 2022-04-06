package com.aisher.helf.db.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Builder
public class ShareBoard {
    @Id
    @Column(name = "board_no")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long boardNo;

    @Column(length = 2000, name = "description")
    String description;  // 식단 공유시 쓴 내용

    @Column(name = "hit", nullable = false)
    @ColumnDefault("0")
    int hit; // 조회수

    @Column(name = "created_at",columnDefinition = "TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
    @CreationTimestamp
    LocalDateTime createdAt; // 등록일

    @JsonIgnore
    @OneToMany(mappedBy = "shareboard", fetch = FetchType.EAGER)
    List<Comment> replies = new ArrayList<>();

    //식단 일지 번호
    @OneToOne
    @JoinColumn(name = "diary_no")
    @OnDelete(action = OnDeleteAction.CASCADE)
    DietDiary diaryNo;

    public void updateDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "ShareBoard{" +
                "boardNo=" + boardNo +
                ", description='" + description + '\'' +
                ", hit=" + hit +
                ", createdAt=" + createdAt +
                ", replies=" + replies +
                ", diaryNo=" + diaryNo.getDiaryNo() +
                '}';
    }

}
