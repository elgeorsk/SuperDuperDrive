package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UsersMapper;
import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private String baseURL;

	private WebDriver driver;

	private LoginPage loginPage;
	private SignupPage signupPage;
	private HomePage homePage;

	// User data
	private String firstname = "Selenium";
	private String lastname = "TEST";
	private static String username = "selenium";
	private String password = "1234";
	private String temp = "";

	// File data
	private File file = new File("src/main/resources/udacityLogo.png");
	private String filePath = file.getAbsolutePath();

	// Note data
	private String noteTitle = "Selenium Note";
	private String noteDescription = "Selenoium Description";

	// Credential data
	private String url = "https://www.udacity.com";

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		// set values to check the download action
		ChromeOptions options = new ChromeOptions();
		String downloadFilepath = "C:\\data";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		options.setExperimentalOption("prefs", chromePrefs);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.setCapability(ChromeOptions.CAPABILITY, options);
		this.driver = new ChromeDriver(options);

		temp = "";
		baseURL = "http://localhost:" + port;
	}

	@AfterEach
	public void afterEach() throws InterruptedException {
		if (this.driver != null) {
			Thread.sleep(2000);
			driver.quit();
		}
	}

	@Test
	@Order(1)
	public void signUpUser() throws InterruptedException {
		driver.get(baseURL + "/signup");

		signupPage = new SignupPage(driver);
		signupPage.signUp(firstname, lastname, username, password);

		Thread.sleep(1000);
		driver.get(baseURL + "/login");

		loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		homePage = new HomePage(driver);

		homePage.getLogoutButton().click();
		Assertions.assertEquals("Login - Super Duper Drive", driver.getTitle());
	}

	public boolean checkFileExists(File file){
		return file.exists();
	}

	public boolean deleteFile(File file){
		return file.delete();
	}

	@Test
	@Order(2)
	public void crudFiles() throws InterruptedException {
		driver.get(baseURL + "/login");

		loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		homePage = new HomePage(driver);
		homePage.getNavFilesTab().click();
		Thread.sleep(2000);

		//Add new file
		homePage.getFileUploadTextField().sendKeys(this.filePath);
		homePage.getUploadFileButton().click();
		Thread.sleep(5000);

		//Download file
		homePage.getDownloadFilesBtns().get(homePage.getDownloadFilesBtns().size()-1).click();
		Thread.sleep(5000);

		// Check if file exists
		String filename = homePage.getFilenameList().get(homePage.getFilenameList().size()-1).getText();
		Assertions.assertTrue(this.checkFileExists(new File("C:\\data\\"+filename)));
		Thread.sleep(5000);

		//Delete file
		homePage.getDeleteFilesBtns().get(homePage.getDeleteFilesBtns().size()-1).click();
		try {
			homePage.getDeleteFilesBtns().get(homePage.getDeleteFilesBtns().size()).equals(0);
		} catch (IndexOutOfBoundsException e) {
			temp = e.getMessage();
		}
		Assertions.assertEquals("Index 0 out of bounds for length 0", temp);
		Assertions.assertTrue(this.deleteFile(new File("C:\\data\\"+filename)));
		Thread.sleep(5000);

		homePage.getLogoutButton().click();
		Assertions.assertEquals("Login - Super Duper Drive", driver.getTitle());
	}

	@Test
	@Order(3)
	public void crudNote() throws InterruptedException {
		driver.get(baseURL + "/login");

		loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		homePage = new HomePage(driver);
		homePage.getNavNotesTab().click();
		Thread.sleep(2000);

		// Add new note
		homePage.getAddNotesButton().click();

		// wait for the modal to be displayed
		Thread.sleep(2000);

		// set values
		homePage.getNoteTitleModalTextField().sendKeys(this.noteTitle);
		homePage.getNoteDescriptionModalTextField().sendKeys(this.noteDescription);
		homePage.getSaveNoteModalButton().click();
		Thread.sleep(5000);

		// Get the latest added note
		homePage.getEditNotesBtns().get(homePage.getEditNotesBtns().size()-1).click();
		Thread.sleep(4000);

		// Check that note is appeared
		temp = homePage.getNotesTitle().get(homePage.getNotesTitle().size()-1).getText();
		Assertions.assertEquals(temp, this.noteTitle);

		// Edit note
		homePage.getNoteTitleModalTextField().sendKeys(" Edit");
		homePage.getNoteDescriptionModalTextField().sendKeys(" " + this.noteDescription.toUpperCase());
		homePage.getSaveNoteModalButton().click();
		Thread.sleep(5000);

		// Check that note is appeared
		temp = homePage.getNotesTitle().get(homePage.getNotesTitle().size()-1).getText();
		Assertions.assertEquals(temp, this.noteTitle + " Edit");

		//Delete note
		homePage.getDeleteNotesBtns().get(homePage.getDeleteNotesBtns().size()-1).click();
		try {
			homePage.getDeleteNotesBtns().get(homePage.getDeleteNotesBtns().size()).equals(0);
		} catch (IndexOutOfBoundsException e) {
			temp = e.getMessage();
		}
		Assertions.assertEquals("Index 0 out of bounds for length 0", temp);
		Thread.sleep(5000);
		Assertions.assertEquals("Home - Super Duper Drive", driver.getTitle());

		homePage.getLogoutButton().click();
		Assertions.assertEquals("Login - Super Duper Drive", driver.getTitle());
	}

	@Test
	@Order(4)
	public void crudCredential() throws InterruptedException {
		driver.get(baseURL + "/login");

		loginPage = new LoginPage(driver);
		loginPage.login(username, password);

		homePage = new HomePage(driver);
		homePage.getNavCredentialsTab().click();
		Thread.sleep(2000);

		// Add new credential
		homePage.getAddCredentialButton().click();

		// wait for the modal to be displayed
		Thread.sleep(2000);

		// set values
		homePage.getCredentialUrlModalTextField().sendKeys(this.url);
		homePage.getCredentialUsernameModalTextField().sendKeys(this.username);
		homePage.getCredentialPasswordModalTextField().sendKeys(this.password);
		homePage.getSaveCredentialModalButton().click();
		Thread.sleep(5000);

		// Check that credential is appeared
		temp = homePage.getCredentialsUrls().get(homePage.getCredentialsUrls().size()-1).getText();
		Assertions.assertEquals(temp, this.url);

		// Get the latest added credential
		homePage.getEditCredentialsBtns().get(homePage.getEditCredentialsBtns().size()-1).click();
		Thread.sleep(4000);

		// Edit credential
		homePage.getCredentialUrlModalTextField().sendKeys("/selenium");
		homePage.getSaveCredentialModalButton().click();
		Thread.sleep(5000);

		// Check that credential is appeared
		temp = homePage.getCredentialsUrls().get(homePage.getCredentialsUrls().size()-1).getText();
		Assertions.assertEquals(temp, this.url+"/selenium");

		// Delete credential
		homePage.getDelteCredentialsBtns().get(homePage.getDelteCredentialsBtns().size()-1).click();
		try {
			homePage.getDelteCredentialsBtns().get(homePage.getDelteCredentialsBtns().size()).equals(0);
		} catch (IndexOutOfBoundsException e) {
			temp = e.getMessage();
		}
		Assertions.assertEquals("Index 0 out of bounds for length 0", temp);
		Thread.sleep(5000);
		Assertions.assertEquals("Home - Super Duper Drive", driver.getTitle());

		homePage.getLogoutButton().click();
		Assertions.assertEquals("Login - Super Duper Drive", driver.getTitle());
	}

}