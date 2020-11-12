package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.models.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private NoteService noteService;
    private UserService userService;
    private CredentialsService credentialService;
    private FileService fileService;

    public HomeController(NoteService notesService, UserService userService, CredentialsService credentialService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = notesService;
        this.credentialService = credentialService;
    }

    @GetMapping("/home")
    public String getHomePage(Authentication auth, Model model) {
        //Get logged-in username
        model.addAttribute("username", auth.getName());
        //Get all files
        model.addAttribute("allFiles", fileService.getAllFiles(auth.getName()));
        //Get all Notes
        model.addAttribute("allNotes", noteService.getAllNotes(auth.getName()));
        // Get all Credentials
        model.addAttribute("allCredentials", credentialService.getAllCredentials(auth.getName()));
        model.addAttribute("credentialService", credentialService);
        return "home";
    }

    // Notes tab
    @PostMapping("/notes")
    public String createOrUpdateNote(Authentication auth, Notes note) {
        if (note.getNoteId() > 0) {
            noteService.updateNote(note);
        } else {
            noteService.createNewNote(auth.getName(), note);
        }
        return "redirect:/result?success";
    }

    @GetMapping("/notes/delete")
    public String deleteNote(@RequestParam("id") Long noteId) {
        if (noteId > 0) {
            noteService.deleteNote(noteId);
            return "redirect:/result?success";
        }
        return "redirect:/result?error";
    }

    // End Notes tab

    // Credentials tab
    @PostMapping("/credentials")
    public String createOrUpdateCredential(Authentication auth, Credentials credential) {
        if (credential.getCredentialId() > 0) {
            credentialService.updateCredential(credential);
        } else {
            credentialService.createNewCredential(auth.getName(), credential);
        }
        return "redirect:/result?success";
    }

    @GetMapping("/credentials/delete")
    public String deleteCredential(@RequestParam("id") Long credentialId) {
        if (credentialId > 0) {
            credentialService.deleteCredential(credentialId);
            return "redirect:/result?success";
        }
        return "redirect:/result?error";
    }

    // End Credentials tab
}
