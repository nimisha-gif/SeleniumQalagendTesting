package pageElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Utilities.JavascriptExecutors;

import Utilities.CommonAction;
import Utilities.WaitConditions;
import Utilities.ExcelReader;

public class CategoriesPageElements extends CommonAction {
	WebDriver driver;
	UnitPageElements unitObj;
	
	WaitConditions wait=new WaitConditions();
	JavascriptExecutors script=new JavascriptExecutors();
	ExcelReader exObj = new ExcelReader();
	public CategoriesPageElements(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver, this);
		unitObj= new UnitPageElements(driver);
	}
	
	@FindBy(xpath="//*[@id=\"tour_step5\"]/ul/li[9]/a")
	public WebElement categories;
	
	@FindBy(xpath="//button[contains(@class,'btn btn-block')]")
	public WebElement add;
	
	@FindBy(xpath="//*[@id=\"category_table_filter\"]/label/input")
	public WebElement search;
	
	@FindBy(id="name")
	public WebElement categoryName;
	
	@FindBy(id="short_code")
	public WebElement categoryCode;
	
	@FindBy(xpath="//*[@id=\"category_add_form\"]/div[3]/button[1]")
	public WebElement save;
	
	@FindBy(xpath="//*[@id=\"category_table\"]/tbody/tr[1]/td[1]")
	public WebElement categoryTableRow;
	
	@FindBy(xpath="//*[@id=\"category_table\"]/tbody/tr[1]/td[3]/button[1]")
	public WebElement edit;
	
	@FindBy(xpath="//*[@id=\"category_edit_form\"]/div[3]/button[1]")
	
	public WebElement update;
	
	@FindBy(xpath="//*[@id=\"category_table\"]/tbody/tr[1]/td[3]/button[2]")
	public WebElement delete;
	
	@FindBy(xpath="//button[contains(@class,'swal-button swal-button--confirm')]")
	public WebElement ok;
	
	@FindBy(xpath="//*[@id=\"category_table\"]/tbody/tr/td")
	public WebElement noRecords;
	
	public void categories_add()
	{
		click(unitObj.products);
		click(categories);
		click(add);
		sendKeys(categoryCode,exObj.excelread(4, 0));
		sendKeys(categoryName,exObj.excelread(4, 1));
		click(save);
		wait.implicitWait(driver, 30);
	}
	
	public String categories_search(String name)
	{
		click(categories);
		sendKeys(search,name);
		return getText(categoryTableRow);
		
	}
	
	public void categories_edit(String cateEditName)
	{
		click(edit);
		categoryName.clear();
		sendKeys(categoryName,cateEditName);
		wait.explicitWait_elementvisibility(driver,update , 2);
		click(update);
		
	}
	
	public void categories_delete()
	{
		wait.explicitWait_elementvisibility(driver,delete, 5);
		click(delete);
		click(ok);
		
	}
	
	public String noRecords() 
	{
		String text=noRecords.getText();
		return text;
	}

}