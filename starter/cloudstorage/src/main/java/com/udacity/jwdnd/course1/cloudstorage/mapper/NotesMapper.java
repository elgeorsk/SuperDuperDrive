package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotesMapper {

    @Select("SELECT * FROM NOTES where userId = #{userId}")
    List<Notes> getAllNotes(Long userId);

    @Insert("INSERT INTO NOTES(noteTitle, noteDescription, userId) VALUES (" +
            "#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insertNote(Notes note);

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
    void deleteNoteById(Long noteId);

    @Update("UPDATE NOTES " +
            "SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription} " +
            "WHERE noteId = #{noteId}")
    int updateNote(Notes note);
}
