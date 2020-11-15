package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.models.Files;
import com.udacity.jwdnd.course1.cloudstorage.models.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public class HomeController {

    private UserService userService;
    private NoteService noteService;
    private CredentialsService credentialService;
    private FileService fileService;
    private String activeTab = "files";

    public HomeController(UserService userService, NoteService notesService, CredentialsService credentialService, FileService fileService) {
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
    @PostMapping("/files/upload")
    public String fileUpload(@RequestParam("fileUpload") MultipartFile fileUpload, Authentication auth, Model model) {
        if (!fileService.isFilenameAvailable(auth.getName()) || fileUpload.isEmpty()){
            activeTab = "files";
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "Your file cannot be uploaded. An error occurred.");
            return "result";
        }

        fileService.createNewFile(auth.getName(), fileUpload);
        activeTab = "files";
        model.addAttribute("file", fileUpload);
        model.addAttribute("success", true);
        model.addAttribute("successMsg", "Your file was successfully uploaded.");
        return "result";
    }

    @GetMapping("/files/download")
    public ResponseEntity<Resource> download(@RequestParam("id") Long fileId, Authentication auth, Model model) throws URISyntaxException {
        Files file = fileService.getFileByUserId(auth.getName());
        if (file == null) {
            activeTab = "files";
            URI redirectUrl = new URI("/result/?error");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(redirectUrl);
            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        } else if (file.getFileId().equals(fileId) && !file.equals(null)) {
            activeTab = "files";
            HttpHeaders header = new HttpHeaders();
            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = " + file.getFilename());
            header.add("Cache-control", "no-cache, no-store, must-revalidate");
            header.add("Pragma", "no-cache");
            header.add("Expires", "0");
            ByteArrayResource resource = new ByteArrayResource((file.getFileData()));
            return ResponseEntity.ok()
                    .headers(header)
                    .body(resource);
        }

        URI redirectUrl = new URI("/result?error");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUrl);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    @GetMapping("/files/delete")
    public String deleteFile(@RequestParam("id") Long fileId, Authentication auth, Model model) {
        Files file = fileService.getFileByUserId(auth.getName());

        if (file == null){
            activeTab = "files";
            return "redirect:/result?error";
        }else if (fileService.getFileByUserId(auth.getName()).getFileId().equals(fileId)) {
            fileService.deleteFile(fileId);
            activeTab = "files";
            model.addAttribute("success", true);
            model.addAttribute("successMsg", "Your file was successfully deleted.");
            return "result";
        }

        activeTab = "files";
        return "redirect:/result?error";
    }
    // End Files tab

    // Notes tab
    @PostMapping("/notes")
    public String createOrUpdateNote(Authentication auth, Notes note, Model model) {
        if (noteService.getNoteByIds(note.getNoteId(),auth.getName()) != null) {
            noteService.updateNote(note);
        } else {
            noteService.createNewNote(auth.getName(), note);
        }
        activeTab = "notes";
        model.addAttribute("success", true);
        model.addAttribute("successMsg", "Your note changes were successfully saved.");
        return "result";
    }

    @GetMapping("/notes/delete")
    public String deleteNote(@RequestParam("id") Long noteId, Authentication auth, Model model) {
        if (noteService.getNoteByIds(noteId,auth.getName()) != null) {
            noteService.deleteNote(noteId);
            activeTab = "notes";
            model.addAttribute("success", true);
            model.addAttribute("successMsg", "Your note was successfully deleted.");
            return "result";
        }
        activeTab = "notes";
        model.addAttribute("error", true);
        model.addAttribute("errorMsg", "Your note wasn't delete. An error occurred.");
        return "result";
    }
    // End Notes tab

    // Credentials tab
    @PostMapping("/credentials")
    public String createOrUpdateCredential(Authentication auth, Credentials credential, Model model) {
        if (credentialService.getCredentialsById(credential.getCredentialId(),auth.getName()) != null) {
            credentialService.updateCredential(credential);
        } else {
            credentialService.createNewCredential(auth.getName(), credential);
        }
        model.addAttribute("success", true);
        model.addAttribute("successMsg", "Your credential was successfully saved.");
        return "result";
    }

    @GetMapping("/credentials/delete")
    public String deleteCredential(@RequestParam("id") Long credentialId, Authentication auth, Model model) {
        if (credentialService.getCredentialsById(credentialId,auth.getName()) != null) {
            credentialService.deleteCredential(credentialId);
            activeTab = "credentials";
            model.addAttribute("success", true);
            model.addAttribute("successMsg", "Your credential was successfully deleted.");
            return "result";
        }
        activeTab = "credentials";
        return "redirect:/result?error";
    }
    // End Credentials tab
}
