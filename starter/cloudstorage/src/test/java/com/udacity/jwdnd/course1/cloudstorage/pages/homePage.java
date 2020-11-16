package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class homePage {

    @FindBy(id = "logout")
    private WebElement logoutButton;

    @FindBy(id = "nav-files-tab")
    private WebElement navFilesTab;

    @FindBy(id = "fileUpload")
    private WebElement fileUploadTextField;

    @FindBy(id = "upload-file-button")
    private WebElement uploadFileButton;

    @FindBy(id = "view-file")
    private List<WebElement> downloadFilesBtns;

    @FindBy(id = "delete-file")
    private List<WebElement> deleteFilesBtns;

    @FindBy(id = "name-file")
    private List<WebElement> filenameList;

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id = "add-notes")
    private WebElement addNotesButton;

    @FindBy(id = "note-title")
    private WebElement noteTitleModalTextField;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionModalTextField;

    @FindBy(id = "save-note-changes")
    private WebElement saveNoteModalButton;

    @FindBy(id = "edit-notes")
    private List<WebElement> editNotesBtns;

    @FindBy(id = "delete-notes")
    private List<WebElement> deleteNotesBtns;

    @FindBy(id = "notes-noteTitle")
    private List<WebElement> notesTitle;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;

    @FindBy(id = "add-credential")
    private WebElement addCredentialButton;

    @FindBy(id = "credentials-allUrls")
    private List<WebElement> credentialsUrls;

    @FindBy(id = "credential-url")
    private WebElement credentialUrlModalTextField;

    @FindBy(id = "credential-username")
    private WebElement credentialUsernameModalTextField;

    @FindBy(id = "credential-password")
    private WebElement credentialPasswordModalTextField;

    @FindBy(id = "edit-credentials")
    private List<WebElement> editCredentialsBtns;

    @FindBy(id = "delete-credentials")
    private List<WebElement> delteCredentialsBtns;

    @FindBy(id = "save-credential")
    private WebElement saveCredentialModalButton;

    public homePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public WebElement getLogoutButton() {
        return logoutButton;
    }

    public void setLogoutButton(WebElement logoutButton) {
        this.logoutButton = logoutButton;
    }

    public WebElement getNavFilesTab() {
        return navFilesTab;
    }

    public void setNavFilesTab(WebElement navFilesTab) {
        this.navFilesTab = navFilesTab;
    }

    public WebElement getFileUploadTextField() {
        return fileUploadTextField;
    }

    public void setFileUploadTextField(WebElement fileUploadTextField) {
        this.fileUploadTextField = fileUploadTextField;
    }

    public WebElement getUploadFileButton() {
        return uploadFileButton;
    }

    public void setUploadFileButton(WebElement uploadFileButton) {
        this.uploadFileButton = uploadFileButton;
    }

    public List<WebElement> getDownloadFilesBtns() {
        return downloadFilesBtns;
    }

    public void setDownloadFilesBtns(List<WebElement> downloadFilesBtns) {
        this.downloadFilesBtns = downloadFilesBtns;
    }

    public List<WebElement> getDeleteFilesBtns() {
        return deleteFilesBtns;
    }

    public void setDeleteFilesBtns(List<WebElement> deleteFilesBtns) {
        this.deleteFilesBtns = deleteFilesBtns;
    }

    public List<WebElement> getFilenameList() {
        return filenameList;
    }

    public void setFilenameList(List<WebElement> filenameList) {
        this.filenameList = filenameList;
    }

    public WebElement getNavNotesTab() {
        return navNotesTab;
    }

    public void setNavNotesTab(WebElement navNotesTab) {
        this.navNotesTab = navNotesTab;
    }

    public WebElement getAddNotesButton() {
        return addNotesButton;
    }

    public void setAddNotesButton(WebElement addNotesButton) {
        this.addNotesButton = addNotesButton;
    }

    public WebElement getNoteTitleModalTextField() {
        return noteTitleModalTextField;
    }

    public void setNoteTitleModalTextField(WebElement noteTitleModalTextField) {
        this.noteTitleModalTextField = noteTitleModalTextField;
    }

    public WebElement getNoteDescriptionModalTextField() {
        return noteDescriptionModalTextField;
    }

    public void setNoteDescriptionModalTextField(WebElement noteDescriptionModalTextField) {
        this.noteDescriptionModalTextField = noteDescriptionModalTextField;
    }

    public WebElement getSaveNoteModalButton() {
        return saveNoteModalButton;
    }

    public void setSaveNoteModalButton(WebElement saveNoteModalButton) {
        this.saveNoteModalButton = saveNoteModalButton;
    }

    public List<WebElement> getEditNotesBtns() {
        return editNotesBtns;
    }

    public void setEditNotesBtns(List<WebElement> editNotesBtns) {
        this.editNotesBtns = editNotesBtns;
    }

    public List<WebElement> getDeleteNotesBtns() {
        return deleteNotesBtns;
    }

    public void setDeleteNotesBtns(List<WebElement> deleteNotesBtns) {
        this.deleteNotesBtns = deleteNotesBtns;
    }

    public List<WebElement> getNotesTitle() {
        return notesTitle;
    }

    public void setNotesTitle(List<WebElement> notesTitle) {
        this.notesTitle = notesTitle;
    }

    public WebElement getNavCredentialsTab() {
        return navCredentialsTab;
    }

    public void setNavCredentialsTab(WebElement navCredentialsTab) {
        this.navCredentialsTab = navCredentialsTab;
    }

    public WebElement getAddCredentialButton() {
        return addCredentialButton;
    }

    public void setAddCredentialButton(WebElement addCredentialButton) {
        this.addCredentialButton = addCredentialButton;
    }

    public List<WebElement> getCredentialsUrls() {
        return credentialsUrls;
    }

    public void setCredentialsUrls(List<WebElement> credentialsUrls) {
        this.credentialsUrls = credentialsUrls;
    }

    public WebElement getCredentialUrlModalTextField() {
        return credentialUrlModalTextField;
    }

    public void setCredentialUrlModalTextField(WebElement credentialUrlModalTextField) {
        this.credentialUrlModalTextField = credentialUrlModalTextField;
    }

    public WebElement getCredentialUsernameModalTextField() {
        return credentialUsernameModalTextField;
    }

    public void setCredentialUsernameModalTextField(WebElement credentialUsernameModalTextField) {
        this.credentialUsernameModalTextField = credentialUsernameModalTextField;
    }

    public WebElement getCredentialPasswordModalTextField() {
        return credentialPasswordModalTextField;
    }

    public void setCredentialPasswordModalTextField(WebElement credentialPasswordModalTextField) {
        this.credentialPasswordModalTextField = credentialPasswordModalTextField;
    }

    public List<WebElement> getEditCredentialsBtns() {
        return editCredentialsBtns;
    }

    public void setEditCredentialsBtns(List<WebElement> editCredentialsBtns) {
        this.editCredentialsBtns = editCredentialsBtns;
    }

    public List<WebElement> getDelteCredentialsBtns() {
        return delteCredentialsBtns;
    }

    public void setDelteCredentialsBtns(List<WebElement> delteCredentialsBtns) {
        this.delteCredentialsBtns = delteCredentialsBtns;
    }

    public WebElement getSaveCredentialModalButton() {
        return saveCredentialModalButton;
    }

    public void setSaveCredentialModalButton(WebElement saveCredentialModalButton) {
        this.saveCredentialModalButton = saveCredentialModalButton;
    }
}
