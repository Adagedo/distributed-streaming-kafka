package code.adagedo.dto;

import code.adagedo.models.Meta;

public record StreamsDTO(
        String $schema,
        Meta meta,
         Integer id,
         String type,
         Integer namespace,
         String title,
         String titleUrl,
         String comment,
         Integer timestamp,
         String user,
         Boolean bot,
         String notifyUrl,
         String serverUrl,
         String serverName,
         String serverScriptPath,
         String wiki,
         String parsedcomment
) {
}
