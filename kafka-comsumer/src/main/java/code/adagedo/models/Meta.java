package code.adagedo.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Meta {
    private String id;
    private String uri;
    private String requestId;
    private String dt;
    private String domain;
    private String stream;
    private String topic;
    private Integer partition;
    private Integer offset;
}
