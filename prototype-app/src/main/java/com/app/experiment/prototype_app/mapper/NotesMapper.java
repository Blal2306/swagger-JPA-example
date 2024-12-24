package com.app.experiment.prototype_app.mapper;

import com.app.experiment.prototype_app.DTO.NotesDTO;
import com.app.experiment.prototype_app.domain.Notes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NotesMapper {

    NotesMapper INSTANCE = Mappers.getMapper(NotesMapper.class);

    NotesDTO notesToNotesDTO(Notes notes);
    Notes notesDTOToNotes(NotesDTO notesDTO);
}
