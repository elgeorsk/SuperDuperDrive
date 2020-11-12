package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UsersMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Notes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NotesMapper notesMapper;
    private UsersMapper userMapper;

    public NoteService(NotesMapper notesMapper, UsersMapper userMapper) {
        this.notesMapper = notesMapper;
        this.userMapper = userMapper;
    }

    public int createNewNote(String username, Notes note){
        note.setUserId(userMapper.getUserId(username));
        return notesMapper.insertNote(note);
    }

    public int updateNote(Notes note){
        return notesMapper.updateNote(note);
    }

    public void deleteNote(Long noteId){
        notesMapper.deleteNoteById(noteId);
    }

    public List<Notes> getAllNotes(String username) {
        return notesMapper.getAllNotes(userMapper.getUserId(username));
    }
}
