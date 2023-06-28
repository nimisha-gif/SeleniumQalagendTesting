package pageElements;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	import Utilities.CommonAction;
	import Utilities.JavascriptExecutors;
	import Utilities.WaitConditions;
	import Utilities.ExcelReader;

	public class BrandPageElements extends CommonAction {
		WebDriver driver;
		UnitPageElements unitObj;
		
		ExcelReader exObj = new ExcelReader();
		WaitConditions wait=new WaitConditions();
		JavascriptExecutors script=new JavascriptExecutors();
		public BrandPageElements(WebDriver driver) {
			this.driver =driver;
			PageFactory.initElements(driver, this);
			unitObj= new UnitPageElements(driver);
		}
		
		@FindBy(xpath="//*[@id=\"tour_step5\"]/ul/li[10]/a")
		public WebElement brands;
		
		@FindBy(xpath="//i[@class='fa fa-plus']")
		public WebElement add;
		
		@FindBy(id="name")
		public WebElement brandName;
		
		@FindBy(id="description")
		public WebElement brandDescription;
		
		@FindBy(xpath="//*[@id=\"brand_add_form\"]/div[3]/button[1]")
		public WebElement save;
		
		@FindBy(xpath="//*[@id=\"brands_table_filter\"]/label/input")
		public WebElement search;
		
		@FindBy(xpath="//*[@id=\"brands_table\"]/tbody/tr/td")
		public WebElement brandsTableRow;
		
		@FindBy(xpath="//*[@id=\"brands_table\"]/tbody/tr[1]/td[3]/button[1]")
		public WebElement edit;
		
		@FindBy(xpath="//*[@id=\"brand_edit_form\"]/div[3]/button[1]")
		public WebElement update;
		
		@FindBy(xpath="//*[@id=\"brands_table\"]/tbody/tr[1]/td[3]/button[2]")
		public WebElement delete;
		
		@FindBy(xpath="//button[contains(@class,'swal-button swal-button--confirm')]")
		public WebElement ok;
		
		@FindBy(xpath="//*[@id=\"brands_table\"]/tbody/tr/td")
		public WebElement noRecords;
		
		
		
		
		public String brand_search(String name)
		{
			click(brands);
			sendKeys(search,name);
			return getText(brandsTableRow);
			
		}
		
		public void brands_edit(String editName)
		{
			click(edit);
			brandName.clear();
			sendKeys(brandName,editName);
			click(update);
		}
		
		public void brands_delete()
		{
			wait.explicitWait_elementvisibility(driver,delete,20);
			click(delete);
			click(ok);
		}
		
		public String no_Records() {
			String text = noRecords.getText();
			return text;
		}

	
		public void brands_add() {
			click(unitObj.products);
			click(brands);
			click(add);
			sendKeys(brandName,exObj.excelread(7, 0));
			sendKeys(brandDescription,exObj.excelread(7, 1));
			click(save);
			wait.implicitWait(driver,30);
			
		}
		
	}