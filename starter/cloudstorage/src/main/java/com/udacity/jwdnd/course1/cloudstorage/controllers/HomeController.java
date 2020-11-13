package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.models.Files;
import com.udacity.jwdnd.course1.cloudstorage.models.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class HomeController {

    private NoteService noteService;
    private UserService userService;
    private CredentialsService credentialService;
    private FileService fileService;
    private String activeTab = "files";

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

        model.addAttribute("activeTab", activeTab);
        return "home";
    }

    // Files tab
    @PostMapping("/upload")
    public String fileUpload(@RequestParam("fileUpload") MultipartFile fileUpload, Authentication auth, Files files, Model model) {

        if (fileUpload.isEmpty()) {
            activeTab = "files";
            return "redirect:/result?error";
        }

        fileService.createNewFile(auth.getName(), fileUpload);

        activeTab = "files";
        return "redirect:/result?success";
    }

    @GetMapping("/files/delete")
    public String deleteFile(@RequestParam("id") Long fileId) {
        if (fileId > 0) {
            fileService.deleteFile(fileId);
            activeTab = "files";
            return "redirect:/result?success";
        }
        activeTab = "files";
        return "redirect:/result?error";
    }
    // End Files tab

    // Notes tab
    @PostMapping("/notes")
    public String createOrUpdateNote(Authentication auth, Notes note, Model model) {
        if (note.getNoteId() > 0) {
            noteService.updateNote(note);
        } else {
            noteService.createNewNote(auth.getName(), note);
        }
        activeTab = "notes";
        return "redirect:/result?success";
    }

    @GetMapping("/notes/delete")
    public String deleteNote(@RequestParam("id") Long noteId, Model model) {
        if (noteId > 0) {
            noteService.deleteNote(noteId);
            activeTab = "notes";
            return "redirect:/result?success";
        }
        activeTab = "notes";
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
        activeTab = "credentials";
        return "redirect:/result?success";
    }

    @GetMapping("/credentials/delete")
    public String deleteCredential(@RequestParam("id") Long credentialId) {
        if (credentialId > 0) {
            credentialService.deleteCredential(credentialId);
            activeTab = "credentials";
            return "redirect:/result?success";
        }
        activeTab = "credentials";
        return "redirect:/result?error";
    }

    // End Credentials tab
}
