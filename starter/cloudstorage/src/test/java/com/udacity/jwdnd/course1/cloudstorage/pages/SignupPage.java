package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "success-msg")
    private WebElement successMessage;

    @FindBy(id = "error-msg")
    private WebElement errorMessage;

    @FindBy(id = "inputFirstName")
    private WebElement firstNameTextField;

    @FindBy(id = "inputLastName")
    private WebElement lastNameTextField;

    @FindBy(id = "inputUsername")
    private WebElement usernameTextField;

    @FindBy(id = "inputPassword")
    private WebElement passwordTextField;

    @FindBy(id = "login-link")
    private WebElement loginLink;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    public SignupPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void signUp(String firstName, String lastName, String username, String password){
        this.firstNameTextField.sendKeys(firstName);
        this.lastNameTextField.sendKeys(lastName);
        this.usernameTextField.sendKeys(username);
        this.passwordTextField.sendKeys(password);
        this.submitButton.click();
    }

    public WebElement getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(WebElement successMessage) {
        this.successMessage = successMessage;
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(WebElement errorMessage) {
        this.errorMessage = errorMessage;
    }

    public WebElement getFirstNameTextField() {
        return firstNameTextField;
    }

    public void setFirstNameTextField(WebElement firstNameTextField) {
        this.firstNameTextField = firstNameTextField;
    }

    public WebElement getLastNameTextField() {
        return lastNameTextField;
    }

    public void setLastNameTextField(WebElement lastNameTextField) {
        this.lastNameTextField = lastNameTextField;
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

    public WebElement getLoginLink() {
        return loginLink;
    }

    public void setLoginLink(WebElement loginLink) {
        this.loginLink = loginLink;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(WebElement submitButton) {
        this.submitButton = submitButton;
    }
}
