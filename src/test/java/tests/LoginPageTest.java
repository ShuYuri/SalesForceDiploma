package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginPageTest extends BaseTest {

    @Test(description = "Registration / logging in Sales Force")
    @Description("Enter username / password -> press enter")
    public void loginPageTest() {
        loginPage
                .loginPage()
                .login(
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")));
        loginPage
                .waitForHomePageLoaded();
        Assert.assertEquals(loginPage.getTitle(), "Home | Salesforce");
    }

    @Test
    public void loginPageTestCheckLogoNameTest() {
        loginPage
                .loginPage()
                .login(
                        System.getenv().getOrDefault("username", PropertyReader.getProperty("username")),
                        System.getenv().getOrDefault("password", PropertyReader.getProperty("password")));
        loginPage
                .waitForHomePageLoaded()
                .clickLogoName();
        Assert.assertEquals(loginPage.getLogoName(), System.getenv().getOrDefault("accountName", PropertyReader.getProperty("accountName")));
    }

    @Test(description = "Registration / logging in Sales Force")
    @Description("Enter username / wrong password -> press enter")
    public void loginPageTestWithWrongPasswordTest() {
        loginPage
                .loginPage()
                .login(
                        System.getenv().getOrDefault("username1", PropertyReader.getProperty("username1")),
                        System.getenv().getOrDefault("password1", PropertyReader.getProperty("password1")));
        loginPage
                .waitForErrorTextMessage();
        Assert.assertEquals(loginPage.getErrorText(), "Please check your username and password. If you still can't log in, contact your Salesforce administrator.");
    }

    @Test
    public void getMyIpAddressTest() {

        URL whatismyip = null;
        try {
            whatismyip = new URL("http://checkip.amazonaws.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BufferedReader in = null;
        try {
            assert whatismyip != null;
            in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String ip = null; //you get the IP as a String - 34.228.236.14
        try {
            assert in != null;
            ip = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(ip);
    }
}