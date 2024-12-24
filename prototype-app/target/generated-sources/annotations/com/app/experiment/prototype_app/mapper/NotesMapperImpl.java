package com.app.experiment.prototype_app.mapper;

import com.app.experiment.prototype_app.DTO.NotesDTO;
import com.app.experiment.prototype_app.domain.Notes;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-23T15:06:14-0600",
    comments = "version: 1.5.0.Beta2, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class NotesMapperImpl implements NotesMapper {

    @Override
    public NotesDTO notesToNotesDTO(Notes notes) {
        if ( notes == null ) {
            return null;
        }

        NotesDTO notesDTO = new NotesDTO();

        notesDTO.setId( notes.getId() );
        notesDTO.setRecipeNotes( notes.getRecipeNotes() );

        return notesDTO;
    }

    @Override
    public Notes notesDTOToNotes(NotesDTO notesDTO) {
        if ( notesDTO == null ) {
            return null;
        }

        Notes notes = new Notes();

        notes.setId( notesDTO.getId() );
        notes.setRecipeNotes( notesDTO.getRecipeNotes() );

        return notes;
    }
}
