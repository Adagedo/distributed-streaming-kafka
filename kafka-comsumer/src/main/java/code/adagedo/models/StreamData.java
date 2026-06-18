package code.adagedo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Table
public class StreamData extends BaseModel{
    private Meta meta;
    private String type;
    private Integer namespace;
    private String title;
    private String titleUrl;
    private String comment;
    private Integer timestamp;
    private String user;
    private Boolean bot;
    private String notifyUrl;
    private String serverUrl;
    private String serverName;
    private String serverScriptPath;
    private String wiki;
    private String parsedcomment;

}
