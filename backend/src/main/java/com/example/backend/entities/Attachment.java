package com.example.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "attachments")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Size(max = 100)
    @NotNull
    @Column(name = "file_name", nullable = false, length = 100)
    private String fileName;

    @Size(max = 500)
    @NotNull
    @Column(name = "object_key", nullable = false, length = 500)
    private String objectKey;

    @Size(max = 100)
    @NotNull
    @Column(name = "content_type", nullable = false, length = 100)
    private String contentType;

    @NotNull
    @Column(name = "file_size", nullable = false)
    private Long fileSize;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "uploaded_by", nullable = false)
    private User uploadedBy;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;


}