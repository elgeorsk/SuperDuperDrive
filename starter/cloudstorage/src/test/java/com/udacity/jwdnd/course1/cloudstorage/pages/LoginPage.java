package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "error-msg")
    private WebElement errorMessage;

    @FindBy(id = "logout-msg")
    private WebElement logoutMessage;

    @FindBy(id = "inputUsername")
    private WebElement usernameTextField;

    @FindBy(id = "inputPassword")
    private WebElement passwordTextField;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "signup-link")
    private WebElement signUpLink;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void login(String username, String password){
        this.usernameTextField.sendKeys(username);
        this.passwordTextField.sendKeys(password);
        this.submitButton.click();
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(WebElement errorMessage) {
        this.errorMessage = errorMessage;
    }

    public WebElement getLogoutMessage() {
        return logoutMessage;
    }

    public void setLogoutMessage(WebElement logoutMessage) {
        this.logoutMessage = logoutMessage;
    }

    public WebElement getUsernameTextField() {
        return usernameTextField;
    }

    public void setUsernameTextField(WebElement usernameTextField) {
        this.usernameTextField = usernameTextField;
    }

    public WebElement getPasswordTextField() {
        return passwordTextField;
    }

    public void setPasswordTextField(WebElement passwordTextField) {
        this.passwordTextField = passwordTextField;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(WebElement submitButton) {
        this.submitButton = submitButton;
    }

    public WebElement getSignUpLink() {
        return signUpLink;
    }

    public void setSignUpLink(WebElement signUpLink) {
        this.signUpLink = signUpLink;
    }
}

