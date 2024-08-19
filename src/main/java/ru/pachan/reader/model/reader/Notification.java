package ru.pachan.reader.model.reader;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Schema(description = "Уведомления")
@Table(name = "notifications")
public class Notification {

    @Column(nullable = false, unique = true)
    private long personId;

    @Column(nullable = false)
    private long count;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
}
