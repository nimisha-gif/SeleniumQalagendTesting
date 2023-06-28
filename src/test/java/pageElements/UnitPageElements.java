package pageElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Utilities.CommonAction;
import Utilities.JavascriptExecutors;
import Utilities.WaitConditions;
import Utilities.ExcelReader;
import Utilities.SelectClass;


public class UnitPageElements extends CommonAction {
	
	WebDriver driver;
	WaitConditions wait=new WaitConditions();
	ExcelReader exObj = new ExcelReader();
	SelectClass selectObj = new SelectClass();
	JavascriptExecutors script=new JavascriptExecutors();
	public UnitPageElements(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver, this);
	
	} 
	
	@FindBy(id="username")
	public WebElement username;
	
	@FindBy(id="password")
	public WebElement password;
	
	@FindBy(xpath="//button[@class='btn btn-primary']")
	public WebElement login;
	
	@FindBy(xpath="//section[@class='content-header']//h1[1]")
	public WebElement welcome;
	
	@FindBy(xpath="//*[@id=\"step-0\"]/div[3]/button[3]")
	public WebElement endTour;
	
	@FindBy(xpath="//*[@id=\"tour_step5_menu\"]/span[1]")
	public WebElement products;
	
	@FindBy(xpath="//*[@id=\"tour_step5\"]/ul/li[8]/a/span")
	public WebElement units;
	
	@FindBy(xpath="//*[@id=\"tour_step5\"]/ul/li[10]/a/span")
	public WebElement brands;
	
	@FindBy(xpath="//*[@id=\"unit_table_filter\"]/label/input")
	public WebElement search;
	
	@FindBy(xpath="//table[contains(@id,'unit_table')]/tbody/tr/td[1]")
	public WebElement webTableRowName;
	@FindBy(id="actual_name")
	public WebElement Name;
	
	@FindBy(id="short_name")
	public WebElement Shortname;
	
	
	@FindBy(id="allow_decimal")
	public WebElement Allow_decimal;
	
	@FindBy(xpath="//*[@id=\"unit_add_form\"]/div[3]/button[1]")
	public WebElement Save;
	
	@FindBy(xpath="//*[@id=\"unit_table\"]/tbody/tr[1]/td[4]/button[2]")
	public WebElement Delete;
	
	@FindBy(xpath="//button[contains(@class,'swal-button swal-button--confirm')]")
	public WebElement Ok;
	
	
	@FindBy(xpath="//div[@class='swal-button-container']//button")
	public WebElement Cancel;
	
	
	@FindBy(xpath="//table[contains(@id,'unit_table')]/tbody/tr/td[4]/button[1]")
	public WebElement edit;
	
	
	@FindBy(xpath="//form/div[3]/button[1]")
	public WebElement update;
	
	
	@FindBy(xpath="//*[@id=\"unit_table_length\"]/label/select")
	public WebElement select;
	
	
	@FindBy(xpath="//div[contains(@id,'unit_table_info')]")
	public WebElement show_entries;
	
	@FindBy(xpath="//*[@id=\"unit_table_paginate\"]/ul/li[2]/a")
	public WebElement nexts;
	
	@FindBy(xpath="//button[contains(@class,'btn btn-block')]")
	public WebElement Add;
	
	@FindBy(xpath="//*[@id=\"unit_table\"]/tbody/tr/td")
	public WebElement noRecords;

	
	
	public void login(String id,String key)
	{
		username.clear();
		sendKeys(username,id);
		password.clear();
		sendKeys(password,key);
		wait.explicitWait_elementvisibility(driver, login, 10);
		click(login);
		if (welcome.getText().equals("Welcome admin,")) {
		click(endTour);	
		}
		
	}
	
	public String searchUnit(String name)
	{
		click(units);
		sendKeys(search,name);
		wait.explicitWait_elementvisibility(driver,nexts,20);
		return getText(webTableRowName);
		
	}
	public void deleteUnit()
	{
		wait.explicitWait_elementvisibility(driver,Delete,20);
		click(Delete);
		click(Ok);
	}

	
	public void addUnit() throws InterruptedException
	{
		click(products);
		click(units);
		click(Add);
		sendKeys(Name,exObj.excelread(1, 0));
		sendKeys(Shortname,exObj.excelread(1, 1));
		selectObj.dropdown(Allow_decimal, 1);
		click(Save);
		wait.implicitWait(driver,30);

		
		
	}
	public void editUnit(String editName)
	{
		wait.explicitWait_elementvisibility(driver,Delete,20);
		click(edit);
		wait.explicitWait_elementvisibility(driver,update,20);
		Name.clear();
		sendKeys(Name,editName);
		click(update);
	}

	public String no_Records() {
		String text = noRecords.getText();
		return text;
	}
	
}