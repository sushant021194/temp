package Codebase.Modules.AtosDemo.FunctionLibrary;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import Codebase.FunctionLibrary.TestCaseReader;
import Codebase.Modules.AtosDemo.ObjectRepository.Locators;

public class ReusableFunctions {
	private RemoteWebDriver driver;
	Locators OR;
	public String mstrCaptureParameterName;
	public String mstrCaptureCommandName;

	public ReusableFunctions(RemoteWebDriver driver) {
		this.driver = driver;
		OR = new Locators();
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_ApplicationLaunch ### Description of Function� To verify
	 * application launch functionality ### Developed By � Rahul Warkhedkar ###
	 * Developed Date� 11/08/2021 ### List of Input Arguments� Boolean ### List of
	 * Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 * 
	 * @throws InterruptedException
	 ******************************************************************************************************/
	public boolean cfnLogin() {
		try {
			// Click on DAS Option
			WebElement DASbutton = driver.findElement(By.xpath("//button[@form='formDas']"));
			if (DASbutton.isDisplayed()) {
				DASbutton.click();
				// Enter Das Id
				WebElement inputDasId = driver.findElement(
						By.xpath("//input[@name=\"com.siemens.dxa.applications.web.authn.challenging.username\"]"));
				if (inputDasId.isDisplayed()) {
					inputDasId.sendKeys("A885555");
					// Enter Password
					WebElement Pass = driver.findElement(
							By.xpath("//input[@name=\"com.siemens.dxa.applications.web.authn.challenging.response\"]"));
					if (Pass.isDisplayed()) {
						Pass.sendKeys("Regency@703");
						// Click on login button
						WebElement loginButton = driver.findElement(OR.objWebElement_loginButton);
						if (loginButton.isDisplayed()) {
							loginButton.click();
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;

	}

	public boolean cfnCreateLeave() {
		try {
			Thread.sleep(10000);

			driver.switchTo().frame("contentAreaFrame");
			// Click on create leave option
			WebElement a1 = driver.findElement(By.xpath("//*[@id=\"__text1-__xmlview1--atosFavoritesList-1-inner\"]"));
			if (a1.isDisplayed()) {
				a1.click();
				Thread.sleep(10000);
				driver.switchTo().window("Create leave request - MyAtos Portal");
				// Click on next button
				WebElement b1 = driver.findElement(By.xpath("//a[@id=\"CreateNewRequest\"]"));
				if (b1.isDisplayed()) {
					b1.click();
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
			// Click on next button
			/*
			 * driver.findElement(By.xpath("//a[@id=\"CreateNewRequest\"]")).click(); return
			 * false;
			 */
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;

	}

	// Acerta Demo purpose only
	public boolean cfnRegister() {
		// Cookies Accept Bar
		driver.switchTo().frame("gdpr-consent-notice");
		driver.findElement(By.xpath("//*[@id=\"save\"]/span[1]/div/span")).click();
		System.out.println("cookies accepted");
		// Click on Register Link/Menu
		driver.findElement(By.xpath("//a[contains(text(),'REGISTER')]")).click();

		// --------Step 3 : Fill in the Details ------------
		// Fill FirstName
		driver.findElementByName("firstName").sendKeys("Ibrahim");
		// Fill LastName
		driver.findElementByXPath("//input[@name='lastName']").sendKeys("Bousba");
		// Fill Phone and other details
		driver.findElementByName("phone").sendKeys("5555 5555 6548");
		driver.findElementByXPath("//input[@name='userName']").sendKeys("testuser@testmail.com");
		driver.findElementByXPath("//input[@name='address1']").sendKeys("Elm street 101");
		driver.findElementByXPath("//input[@name='city']").sendKeys("Racoon City");
		driver.findElementByXPath("//input[@name='state']").sendKeys("Arklay");
		driver.findElementByXPath("//input[@name='postalCode']").sendKeys("RPD-4456");

		// Drop Down list
		Select drpList = new Select(driver.findElement(By.name("country")));
		drpList.selectByValue("UNITED STATES");
		// Fill in the rest of the details
		driver.findElementByXPath("//input[@id='email']").sendKeys("LeonSKennedy");
		driver.findElementByXPath("//input[@name='password']").sendKeys("JillValentineN3mesis");
		driver.findElementByXPath("//input[@name='confirmPassword']").sendKeys("JillValentineN3mesis");

		// click on the submit button
		driver.findElementByName("submit").click();
		return true;
	}

	public boolean cfnVerify_ApplicationLaunch() throws InterruptedException {

		/*
		 * boolean mblnSecurityCertificate = cfnHandleSecurityCertificate(); if
		 * (mblnSecurityCertificate) {
		 */
//			Thread.sleep(2000);
		// Accept the cookies
		driver.switchTo().frame("gdpr-consent-notice");
		driver.findElement(By.xpath("//*[@id=\"save\"]/span[1]/div/span")).click();
		System.out.println("cookies accepted");
		// Enter Username
		WebElement EnterEmail = driver.findElement(OR.objEmailSignInPage);
		EnterEmail.sendKeys(TestCaseReader.getConfig("Command_UN"));
		// Enter Password
		WebElement Enterpassword = driver.findElement(OR.objPassSignInPage);
		Enterpassword.sendKeys(TestCaseReader.getConfig("Command_PW"));
		// Click on Login Button
		WebElement SignInBtn = driver.findElement(OR.objclickSignInBtnSignInPage);
//				Thread.sleep(1000000);
		SignInBtn.click();
		System.out.println("Button Clicked");
		/*
		 * } else { return false; }
		 */
		String SuccessMsg = driver.findElement(By.xpath("//h3")).getText();
		System.out.println(SuccessMsg);
		if (SuccessMsg.equals("Login Successfully")) {
			return true;
		} else {
			return false;
		}

	}

	public boolean cfnHandleSecurityCertificate() {
		try {
			WebElement objAdv = driver.findElement(OR.objadvancedclick);
			if (objAdv.isDisplayed()) {
				objAdv.click();
				WebElement objlinkunsafe = driver.findElement(OR.objunsafelinkclick);
				if (objlinkunsafe.isDisplayed()) {
					objlinkunsafe.click();
					Thread.sleep(5000);
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnSelect_ItemFrom_LeftHandSideMenu ### Description of Function� To select
	 * menu item from left hand side menu ### Developed By � Rahul Warkhedkar ###
	 * Developed Date� 12/08/2021 ### List of Input Arguments� Item Name ### List of
	 * Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnSelect_ItemFrom_LeftHandSideMenu(String mstrItem) {
		try {
			List<WebElement> allItems = driver.findElements(OR.objWebElementList_LHS_MenuItems);
			for (int i = 0; i < allItems.size(); i++) {
				String mstrItemText = allItems.get(i).getText().trim();
				if (mstrItemText.equalsIgnoreCase(mstrItem)) {
					allItems.get(i).click();
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_LHS_MenuItems_Exist ### Description of Function� To verify menu
	 * item from left hand side menu exist ### Developed By � Rahul Warkhedkar ###
	 * Developed Date� 12/08/2021 ### List of Input Arguments� Item Name ### List of
	 * Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_LHS_MenuItems_Exist(String mstrMenuItem) {
		try {
			List<WebElement> allItems = driver.findElements(OR.objWebElementList_LHS_MenuItems);
			for (int i = 0; i < allItems.size(); i++) {
				String mstrItemText = allItems.get(i).getText().trim();
				if (mstrItemText.equalsIgnoreCase(mstrMenuItem)) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnSelect_ItemFrom_LeftHandSideMenu ### Description of Function� To select
	 * menu item from left hand side menu ### Developed By � Rahul Warkhedkar ###
	 * Developed Date� 12/08/2021 ### List of Input Arguments� Header Text/name ###
	 * List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_Page_Header(String mstrHeader) {
		try {
			String mstrHeaderText = driver.findElement(OR.objWebElement_Page_Header).getText().trim();
			if (mstrHeaderText.equalsIgnoreCase(mstrHeader)) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_Add_CommonButton_Exist ### Description of Function� To verify ADD
	 * button exist or not ### Developed By � Rahul Warkhedkar ### Developed Date�
	 * 12/08/2021 ### List of Input Arguments� Button Text/name ### List of Output
	 * Arguments� Boolean ### Modified By� ### Modified Date� ### Modification
	 * Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_Add_CommonButton_Exist(String mstrButtonName) {
		try {
			String mstrButtonText = driver.findElement(OR.objWebButton_Add_CommonButton).getText().trim();
			if (mstrButtonText.equalsIgnoreCase(mstrButtonName)) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnClick_Add_CommonButton ### Description of Function� To click ADD button
	 * ### Developed By � Rahul Warkhedkar ### Developed Date� 12/08/2021 ### List
	 * of Input Arguments� Button Text/name ### List of Output Arguments� Boolean
	 * ### Modified By� ### Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnClick_Add_CommonButton(String mstrButtonName) {
		try {
			WebElement objAddButton = driver.findElement(OR.objWebButton_Add_CommonButton);
			if (objAddButton.isDisplayed()) {
				if (!(mstrButtonName.equalsIgnoreCase("NA"))) {
					objAddButton.click();
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_Popup_Header ### Description of Function� To verify popup header
	 * ### Developed By � Rahul Warkhedkar ### Developed Date� 12/08/2021 ### List
	 * of Input Arguments� header Text/name ### List of Output Arguments� Boolean
	 * ### Modified By� ### Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_Popup_Header(String mstrHeader) {
		try {
			WebElement objHeader = driver.findElement(OR.objWebElement_Popup_Header);
			if (objHeader.isDisplayed()) {
				if (!(mstrHeader.equalsIgnoreCase("NA"))) {
					String mstrHeaderText = objHeader.getText().trim();
					if (mstrHeaderText.equalsIgnoreCase(mstrHeader)) {
						return true;
					}
					driver.findElement(OR.objWebElement_Popup_Close).click();
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_Popup_Header ### Description of Function� To verify popup header
	 * ### Developed By � Rahul Warkhedkar ### Developed Date� 12/08/2021 ### List
	 * of Input Arguments� header Text/name ### List of Output Arguments� Boolean
	 * ### Modified By� ### Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerifyAddParameter_Popup_Header(String mstrHeader) {
		try {
			System.out.println("i am inheader verification function");
			WebElement objHeader = driver.findElement(OR.objWebElementAddParameter_Popup_Header);
			// System.out.println(objHeader);
			if (objHeader.isDisplayed()) {
				if (!(mstrHeader.equalsIgnoreCase("NA"))) {
					String mstrHeaderText = objHeader.getText().trim();
					System.out.println(mstrHeaderText);
					System.out.println(mstrHeader);
					if (mstrHeaderText.equalsIgnoreCase(mstrHeader.trim())) {
						return true;
					}
					// driver.findElement(OR.objWebElement_Popup_Close).click();
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_Popup_Header_WithoutClose ### Description of Function� To verify
	 * popup header ### Developed By � Rahul Warkhedkar ### Developed Date�
	 * 12/08/2021 ### List of Input Arguments� header Text/name ### List of Output
	 * Arguments� Boolean ### Modified By� ### Modified Date� ### Modification
	 * Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_AddCommandPopup_Header_WithoutClose(String mstrHeader) {
		try {
			WebElement objHeader = driver.findElement(OR.objWebElement_AddCommand_Popup_Header);
			if (objHeader.isDisplayed()) {
				System.out.println("Header is displayed");
				String mstrHeaderText = objHeader.getText().trim();
				System.out.println(mstrHeaderText);
				System.out.println(mstrHeader);
				if (mstrHeaderText.trim().equalsIgnoreCase(mstrHeader.trim())) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_Popup_Header_WithoutClose ### Description of Function� To verify
	 * popup header ### Developed By � Rahul Warkhedkar ### Developed Date�
	 * 12/08/2021 ### List of Input Arguments� header Text/name ### List of Output
	 * Arguments� Boolean ### Modified By� ### Modified Date� ### Modification
	 * Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_SendCommandPopup_Header_WithoutClose(String mstrHeader) {
		try {
			WebElement objHeader = driver.findElement(OR.objWebElement_SendCommand_Popup_Header);
			if (objHeader.isDisplayed()) {
				System.out.println("Header is displayed");
				String mstrHeaderText = objHeader.getText().trim();
				System.out.println(mstrHeaderText);
				System.out.println(mstrHeader);
				if (mstrHeaderText.trim().equalsIgnoreCase(mstrHeader.trim())) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * /****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_Popup_Header_WithoutClose ### Description of Function� To verify
	 * popup header ### Developed By � Rahul Warkhedkar ### Developed Date�
	 * 12/08/2021 ### List of Input Arguments� header Text/name ### List of Output
	 * Arguments� Boolean ### Modified By� ### Modified Date� ### Modification
	 * Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_Popup_Header_WithoutClose(String mstrHeader) {
		try {
			WebElement objHeader = driver.findElement(OR.objWebElement_Popup_Header);
			if (objHeader.isDisplayed()) {
				System.out.println("Header is displayed");
				String mstrHeaderText = objHeader.getText().trim();
				System.out.println(mstrHeaderText);
				System.out.println(mstrHeader);
				if (mstrHeaderText.trim().equalsIgnoreCase(mstrHeader.trim())) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_Left_Hand_Side_Menu_Working ### Description of Function� To verify
	 * left hand side menu is working properly or not ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� Menu
	 * Item Name, Page Header ### List of Output Arguments� Boolean ### Modified By�
	 * ### Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_Left_Hand_Side_Menu_Working(String mstrMenuItem, String mstrPageHeader) {
		try {
			boolean mblnMenuItem = cfnSelect_ItemFrom_LeftHandSideMenu(mstrMenuItem);
			if (mblnMenuItem) {
				boolean mblnPageHeader = cfnVerify_Page_Header(mstrPageHeader);
				if (mblnPageHeader) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_Add_CommonButton_Working ### Description of Function� To verify
	 * common ADD button working properly or not h ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� Button
	 * Name, Popup Header ### List of Output Arguments� Boolean ### Modified By� ###
	 * Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_Add_CommonButton_Working(String mstrButtonName, String mstrPopupHeader) {
		try {
			boolean mblnAddButton = cfnClick_Add_CommonButton(mstrButtonName);
			if (mblnAddButton) {
				boolean mblnPopupHeader = cfnVerify_Popup_Header(mstrPopupHeader);
				if (mblnPopupHeader) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnClick_Add_CommonButton_Working ### Description of Function� To click
	 * common ADD button working properly or not ### Developed By � Rahul Warkhedkar
	 * ### Developed Date� 12/08/2021 ### List of Input Arguments� Button Name,
	 * Popup Header ### List of Output Arguments� Boolean ### Modified By� ###
	 * Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnClick_Add_CommonButton_Working(String mstrButtonName, String mstrPopupHeader) {
		try {
			boolean mblnAddButton = cfnClick_Add_CommonButton(mstrButtonName);
			Thread.sleep(7000);
			if (mblnAddButton) {
				System.out.println("mstrPopupHeader");
				boolean mblnPopupHeader = cfnVerifyAddParameter_Popup_Header(mstrPopupHeader);
				// System.out.println(mblnPopupHeader);
				if (mblnPopupHeader) {
					// System.out.println("Header Displayed");
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnClick_Add_CommonButton_Working ### Description of Function� To click
	 * common ADD button working properly or not ### Developed By � Rahul Warkhedkar
	 * ### Developed Date� 12/08/2021 ### List of Input Arguments� Button Name,
	 * Popup Header ### List of Output Arguments� Boolean ### Modified By� ###
	 * Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnClick_AddCommandButton_Working(String mstrButtonName, String mstrPopupHeader) {
		try {
			boolean mblnAddButton = cfnClick_Add_CommonButton(mstrButtonName);
			Thread.sleep(2000);
			if (mblnAddButton) {
				// System.out.println("Button Clined");
				boolean mblnPopupHeader = cfnVerify_AddCommandPopup_Header_WithoutClose(mstrPopupHeader);
				// System.out.println(mblnPopupHeader);
				if (mblnPopupHeader) {
					// System.out.println("Header Displayed");
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnClick_Add_CommonButton_Working ### Description of Function� To click
	 * common ADD button working properly or not ### Developed By � Rahul Warkhedkar
	 * ### Developed Date� 12/08/2021 ### List of Input Arguments� Button Name,
	 * Popup Header ### List of Output Arguments� Boolean ### Modified By� ###
	 * Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnClick_Add_SendCommandButton_Working(String mstrButtonName, String mstrPopupHeader) {
		try {
			boolean mblnAddButton = cfnClick_Add_CommonButton(mstrButtonName);
			Thread.sleep(2000);
			if (mblnAddButton) {
				// System.out.println("Button Clined");
				boolean mblnPopupHeader = cfnVerify_SendCommandPopup_Header_WithoutClose(mstrPopupHeader);
				// System.out.println(mblnPopupHeader);
				if (mblnPopupHeader) {
					// System.out.println("Header Displayed");
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_AllFeilds_SearchAssets_Related_SendCommandPage ### Description of
	 * Function� To verify all feild related to search asset functioality ###
	 * Developed By � Akshada ### Developed Date� 20/10/2021 ### List of Input
	 * Arguments� ### List of Output Arguments� Boolean ### Modified By� ###
	 * Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_AllFeilds_SearchAssets_Related_SendCommandPage(String mstrSearch_BUTTON) {
		try {
			if (!(mstrSearch_BUTTON.equalsIgnoreCase("NA"))) {
				WebElement element = driver.findElement(OR.objWebEdit_SearchAsset);
				element.sendKeys(mstrSearch_BUTTON);
				element.click();
				// WebElement SearchAssetButton =
				// driver.findElement(OR.objWebButton_SearchAsset);

				// SearchAssetButton.click();
				Thread.sleep(2000);
				WebElement chkbx = driver.findElement(OR.objWebButton_Checkbox);
				// System.out.println("hiiii");
				chkbx.click();
				// System.out.println("hiiii");

				chkbx.isSelected();
				return true;

				// WebElement SearchAssetLable =
				// driver.findElement(OR.objWebElement_SearchAssetLable);
				/*
				 * WebElement SearchAssetLableEditFeild =
				 * driver.findElement(OR.objWebEdit_SearchAsset);
				 * SearchAssetLableEditFeild.sendKeys("TRUCK"); // Select sl=new Select();
				 * WebElement SearchAssetButton =
				 * driver.findElement(OR.objWebButton_SearchAsset); SearchAssetButton.click();
				 * Thread.sleep(2000); WebElement chkbx =
				 * driver.findElement(OR.objWebButton_Checkbox); // System.out.println("hiiii");
				 * chkbx.click(); // System.out.println("hiiii");
				 * 
				 * chkbx.isSelected(); //System.out.println("hiiii"); if
				 * (!(mstrParameter.equalsIgnoreCase("NA"))) { WebElement element =
				 * driver.findElement(OR.objWebEdit_ParameterName_AddParameterPopup); if
				 * (cfnVerify_FeildDisplayed(element)) { element.clear();
				 * element.sendKeys(mstrParameter); return true; } else { return false; } } }
				 * 
				 * /* for (int i=0; i<2; i++) { chkFBPersist.click ();
				 * System.out.println(" Checkbox Status is -  "+chkFBPersist.isSelected()); }
				 * /*if (SearchAssetLable.isDisplayed() &&
				 * SearchAssetLableEditFeild.isDisplayed() && SearchAssetButton.isDisplayed()) {
				 * return true; }
				 */
				// return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_AssetORCommand_Table_Exist ### Description of Function� To verify
	 * assets or command table ### Developed By � Rahul Warkhedkar ### Developed
	 * Date� 12/08/2021 ### List of Input Arguments� ### List of Output Arguments�
	 * Boolean ### Modified By� ### Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_AssetORCommand_Table_Exist() {
		try {
			String mstrTableCol = "Id-Asset Name-Tag Name-Protocol-Serial Number";
			String[] marrTableCol = mstrTableCol.split("-");
			int mintCount = 0;
			for (int i = 0; i < marrTableCol.length; i++) {
				List<WebElement> TableCols = driver.findElements(OR.objWebTable_ColoumName_List);
				for (int j = 0; j < TableCols.size(); j++) {
					String mstrColName = TableCols.get(j).getText().trim();
					if (mstrColName.equalsIgnoreCase(marrTableCol[i].trim())) {
						mintCount = mintCount + 1;
					}
				}
			}
			if (mintCount == marrTableCol.length) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_CommandConfiguration_Table_Exist ### Description of Function� To
	 * verify command configuration table ### Developed By � Rahul Warkhedkar ###
	 * Developed Date� 12/08/2021 ### List of Input Arguments� ### List of Output
	 * Arguments� Boolean ### Modified By� ### Modified Date� ### Modification
	 * Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_CommandConfiguration_Table_Exist() {
		try {
			String mstrTableCol = "Command Name-Command Description-Command Parameter-Updated Date-Action";
			String[] marrTableCol = mstrTableCol.split("-");
			int mintCount = 0;
			for (int i = 0; i < marrTableCol.length; i++) {
				List<WebElement> TableCols = driver.findElements(OR.objWebTable_ColoumName_List_CommandConfiguration);
				for (int j = 0; j < TableCols.size(); j++) {
					String mstrColName = TableCols.get(j).getText().trim();
					if (mstrColName.equalsIgnoreCase(marrTableCol[i].trim())) {
						mintCount = mintCount + 1;
					}
				}
			}
			if (mintCount == marrTableCol.length) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_CommandParameter_Table_Exist ### Description of Function� To verify
	 * command parameter table ### Developed By � Rahul Warkhedkar ### Developed
	 * Date� 12/08/2021 ### List of Input Arguments� ### List of Output Arguments�
	 * Boolean ### Modified By� ### Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_CommandParameter_Table_Exist() {
		try {
			String mstrTableCol = "Parameter Name-Parameter Description-Data Type-Updated Date-Action";
			String[] marrTableCol = mstrTableCol.split("-");
			int mintCount = 0;
			for (int i = 0; i < marrTableCol.length; i++) {
				List<WebElement> TableCols = driver.findElements(OR.objWebTable_Coloum_List_CommandParameter);
				for (int j = 0; j < TableCols.size(); j++) {
					String mstrColName = TableCols.get(j).getText().trim();
					if (mstrColName.equalsIgnoreCase(marrTableCol[i].trim())) {
						mintCount = mintCount + 1;
					}
				}
			}
			if (mintCount == marrTableCol.length) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_CommandHistory_Table_Exist ### Description of Function� To verify
	 * command history table ### Developed By � Rahul Warkhedkar ### Developed Date�
	 * 12/08/2021 ### List of Input Arguments� ### List of Output Arguments� Boolean
	 * ### Modified By� ### Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_CommandHistory_Table_Exist() {
		try {
			String mstrTableCol = "Asset Number-Command With Parameter-Requested Date-Status-Scheduled On-Updated On-Protocol-Expiration date";
			String[] marrTableCol = mstrTableCol.split("-");
			int mintCount = 0;
			for (int i = 0; i < marrTableCol.length; i++) {
				List<WebElement> TableCols = driver.findElements(OR.objWebTable_Coloum_List_CommandHistory);
				for (int j = 0; j < TableCols.size(); j++) {
					String mstrColName = TableCols.get(j).getText().trim();
					if (mstrColName.equalsIgnoreCase(marrTableCol[i].trim())) {
						mintCount = mintCount + 1;
					}
				}
			}
			if (mintCount == marrTableCol.length) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_FeildDisplayed ### Description of Function� To verify feild is
	 * displayed ### Developed By � Rahul Warkhedkar ### Developed Date� 12/08/2021
	 * ### List of Input Arguments� ### List of Output Arguments� Boolean ###
	 * Modified By� ### Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_FeildDisplayed(WebElement element) {
		try {
			if (element.isDisplayed()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_FeildEnabled ### Description of Function� To verify feild is
	 * enabled ### Developed By � Rahul Warkhedkar ### Developed Date� 12/08/2021
	 * ### List of Input Arguments� ### List of Output Arguments� Boolean ###
	 * Modified By� ### Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_FeildEnabled(WebElement element) {
		try {
			if (element.isEnabled()) {
				// element.click();
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_CommandName_Feild_Disaplyed_AddCommandPopup ### Description of
	 * Function� To verify Command Name feild displayed ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� ###
	 * List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_CommandName_Feild_Disaplyed_AddCommandPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebEdit_CommandName_AddCommandPopup);
			return cfnVerify_FeildDisplayed(element);
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnSet_CommandName_Feild_Disaplyed_AddCommandPopup ### Description of
	 * Function� To set Command Name feild ### Developed By � Rahul Warkhedkar ###
	 * Developed Date� 12/08/2021 ### List of Input Arguments� ### List of Output
	 * Arguments� Boolean ### Modified By� ### Modified Date� ### Modification
	 * Comments �
	 * 
	 ******************************************************************************************************/
	public boolean cfnSet_CommandName_Feild_Disaplyed_AddCommandPopup(String mstrCommandName) {
		try {
			WebElement element = driver.findElement(OR.objWebEdit_CommandName_AddCommandPopup);
			if (element.isDisplayed()) {
				if (!(mstrCommandName.equalsIgnoreCase("NA"))) {
					element.clear();
					element.sendKeys(mstrCommandName);
					return true;
				} else {
					element.clear();
					return true;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_CommandDescription_Feild_Disaplyed_AddCommandPopup ### Description
	 * of Function� To verify Command Description feild displayed ### Developed By �
	 * Rahul Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments�
	 * ### List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_CommandDescription_Feild_Disaplyed_AddCommandPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebEdit_CommandDescription_AddCommandPopup);
			return cfnVerify_FeildDisplayed(element);
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnSet_CommandDescription_Feild_Disaplyed_AddCommandPopup ### Description of
	 * Function� To set Command Description feild displayed ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� ###
	 * List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnSet_CommandDescription_Feild_Disaplyed_AddCommandPopup(String mstrCommandDescription) {
		try {
			WebElement element = driver.findElement(OR.objWebEdit_CommandDescription_AddCommandPopup);
			if (element.isDisplayed()) {
				if (!(mstrCommandDescription.equalsIgnoreCase("NA"))) {
					element.clear();
					element.sendKeys(mstrCommandDescription);
					return true;
				} else {
					element.clear();
					return true;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_CommandParameter_Feild_Disaplyed_AddCommandPopup ### Description of
	 * Function� To verify Command Parameter feild displayed ### Developed By �
	 * Rahul Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments�
	 * ### List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_CommandParameter_Feild_Disaplyed_AddCommandPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebDropDown_CommandParameter_AddCommandPopup);
			return cfnVerify_FeildDisplayed(element);
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnSelect_CommandParameter_Feild_Disaplyed_AddCommandPopup ### Description of
	 * Function� To Select Command Parameter feild ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� ###
	 * List of Output Arguments� Boolean ### Modified By�
	 * 
	 * ### Modified Date� ### Modification Comments �
	 * 
	 ******************************************************************************************************/
	public boolean cfnSelect_CommandParameter_Feild_Disaplyed_AddCommandPopup(String mstrParameter) {
		try {
			WebElement element = driver.findElement(OR.objWebDropDown_CommandParameter_AddCommandPopup);
			if (element.isDisplayed()) {
				if (!(mstrParameter.equalsIgnoreCase("NA"))) {
					Select ParameterName = new Select(element);
					for (int i = 0; i < ParameterName.getOptions().size(); i++) {
						String Text = ParameterName.getOptions().get(i).getText().trim();
						if (Text.equalsIgnoreCase(mstrParameter)) {
							ParameterName.selectByIndex(i);
							return true;
						}
					}
				} else {
					// element.clear();
					return true;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_CommandOptional_Feild_Disaplyed_AddCommandPopup ### Description of
	 * Function� To verify Command Optional feild displayed ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� ###
	 * List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_CommandOptional_Feild_Disaplyed_AddCommandPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebEdit_CommandOptional_AddCommandPopup);
			return cfnVerify_FeildDisplayed(element);
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_CommandOptional_Feild_Disaplyed_AddCommandPopup ### Description of
	 * Function� To verify Command Optional feild displayed ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� ###
	 * List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 * 
	 ******************************************************************************************************/
	public boolean cfnSelect_CommandOptional_Feild_Disaplyed_AddCommandPopup(String mstrCommandOptional) {
		try {
			WebElement element = driver.findElement(OR.objWebEdit_CommandOptional_AddCommandPopup);
			if (element.isDisplayed()) {
				if (!(mstrCommandOptional.equalsIgnoreCase("NA"))) {
					Select ParameterName = new Select(element);
					for (int i = 0; i < ParameterName.getOptions().size(); i++) {
						String Text = ParameterName.getOptions().get(i).getText().trim();
						if (Text.equalsIgnoreCase(mstrCommandOptional)) {
							ParameterName.selectByIndex(i);
							return true;
						}
					}
				} else {
					// element.clear();
					return true;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_CreateButton_Feild_Disabled_AddCommandPopup ### Description of
	 * Function� To verify create button feild enabled ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� ###
	 * List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_CreateButton_Feild_Disabled_AddCommandPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_Create_AddCommandPopup);
			return cfnVerify_FeildEnabled(element);
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnClick_CreateButton_Feild_Disabled_AddCommandPopup ### Description of
	 * Function� To click create button feild enabled ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� ###
	 * List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnClick_AddButton_Feild_Disabled_AddCommandPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_Add_AddCommandPopup);
			if (cfnVerify_FeildEnabled(element)) {
				element.click();
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnClick_CreateButton_Feild_Disabled_AddCommandPopup ### Description of
	 * Function� To click create button feild enabled ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� ###
	 * List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnClick_CreateButton_Feild_Disabled_AddCommandPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_Create_AddCommandPopup);
			if (cfnVerify_FeildEnabled(element)) {
				element.click();
				Thread.sleep(2000);
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_CloseButton_Feild_Disabled_AddCommandPopup ### Description of
	 * Function� To verify close button feild enabled ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� ###
	 * List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_CloseButton_Feild_Disabled_AddCommandPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_Close_AddCommandPopup);
			return cfnVerify_FeildEnabled(element);
		} catch (Exception e) {
			System.out.println(e);
		}

		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfn_Click_CloseButton_Feild_Disabled_AddCommandPopup ### Description of
	 * Function� To click close button feild enabled ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� ###
	 * List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfn_Click_CloseButton_Feild_Disabled_AddCommandPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_Close_AddCommandPopup);
			if (cfnVerify_FeildEnabled(element)) {
				element.click();
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnClick_CloseButton_Feild_Disabled_AddCommandPopup ### Description of
	 * Function� To click close button feild enabled ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� ###
	 * List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnClick_CloseButton_Feild_Disabled_AddCommandPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_Close_AddCommandPopup);
			if (cfnVerify_FeildEnabled(element)) {
				element.click();
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_ResetButton_Feild_Disabled_AddCommandPopup ### Description of
	 * Function� To verify Reset button feild enabled ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� ###
	 * List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_ResetButton_Feild_Disabled_AddCommandPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_Reset_AddCommandPopup);
			return cfnVerify_FeildEnabled(element);
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfn_Click_ResetButton_Feild_Disabled_AddCommandPopup ### Description of
	 * Function� To verify Reset button feild at add command ### Developed By �
	 * Rahul Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments�
	 * ### List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfn_Click_ResetButton_Feild_Disabled_AddCommandPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_Reset_AddCommandPopup);
			if (cfnVerify_FeildEnabled(element)) {
				element.click();
				Thread.sleep(3000);
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_ResetButton_Feild_Disabled_AddCommandPopup ### Description of
	 * Function� To verify Reset button feild enabled ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� ###
	 * List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnClick_ResetButton_Feild_Disabled_AddCommandPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_Reset_AddCommandPopup);
			if (cfnVerify_FeildEnabled(element)) {
				element.click();
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_ParameterName_Feild_Disaplyed_AddParameterPopup ### Description of
	 * Function� To verify Parameter Name feild at Add Parameter pop up ###
	 * Developed By � Rahul Warkhedkar ### Developed Date� 12/08/2021 ### List of
	 * Input Arguments� ### List of Output Arguments� Boolean ### Modified By� ###
	 * Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_ParameterName_Feild_Disaplyed_AddParameterPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebEdit_ParameterName_AddParameterPopup);
			return cfnVerify_FeildDisplayed(element);
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_ParameterName_Feild_Disaplyed_AddParameterPopup ### Description of
	 * Function� To verify Parameter Name feild at Add Parameter pop up ###
	 * Developed By � Rahul Warkhedkar ### Developed Date� 12/08/2021
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * ### List of Input Arguments� parameter name ### List of Output Arguments�
	 * Boolean ### Modified By� ### Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnSet_ParameterName_Feild_Disaplyed_AddParameterPopup(String mstrParameter) {
		try {
			WebElement element = driver.findElement(OR.objWebEdit_ParameterName_AddParameterPopup);
			if (element.isDisplayed()) {
				if (!(mstrParameter.equalsIgnoreCase("NA"))) {
					element.clear();
					element.sendKeys(mstrParameter);
					return true;
				} else {
					element.clear();
					return true;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_ParameterDescription_Feild_Disaplyed_AddParameterPopup ###
	 * Description of Function� To verify Parameter Description feild at Add
	 * Parameter pop up ### Developed By � Rahul Warkhedkar ### Developed Date�
	 * 12/08/2021 ### List of Input Arguments� ### List of Output Arguments� Boolean
	 * ### Modified By� ### Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_ParameterDescription_Feild_Disaplyed_AddParameterPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebEdit_ParameterDescription_AddParameterPopup);
			return cfnVerify_FeildDisplayed(element);
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnSet_ParameterDescription_Feild_Disaplyed_AddParameterPopup ### Description
	 * of Function� To set Parameter Description feild at Add Parameter pop up ###
	 * Developed By � Rahul Warkhedkar ### Developed Date� 12/08/2021 ### List of
	 * Input Arguments� parameter description ### List of Output Arguments� Boolean
	 * ### Modified By� ### Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnSet_ParameterDescription_Feild_Disaplyed_AddParameterPopup(String mstrParameterDescription) {
		try {
			WebElement element = driver.findElement(OR.objWebEdit_ParameterDescription_AddParameterPopup);
			if (element.isDisplayed()) {
				if (!(mstrParameterDescription.equalsIgnoreCase("NA"))) {
					element.clear();
					element.sendKeys(mstrParameterDescription);
					return true;
				} else {
					element.clear();
					return true;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_ParameterDataType_Feild_Disaplyed_AddParameterPopup ### Description
	 * of Function� To verify Parameter Data Type feild at Add Parameter pop up ###
	 * Developed By � Rahul Warkhedkar ### Developed Date� 12/08/2021 ### List of
	 * Input Arguments� ### List of Output Arguments� Boolean ### Modified By� ###
	 * Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_ParameterDataType_Feild_Disaplyed_AddParameterPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebEdit_ParameterDataType_AddParameterPopup);
			return cfnVerify_FeildDisplayed(element);
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnSet_ParameterDataType_Feild_Disaplyed_AddParameterPopup ### Description of
	 * Function� To set Parameter Data Type feild at Add Parameter pop up ###
	 * Developed By � ### Developed Date� 12/08/2021 ### List of Input Arguments�
	 * parameter data type ### List of Output Arguments� Boolean ### Modified By�
	 * ### Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnSet_ParameterDataType_Feild_Disaplyed_AddParameterPopup(String mstrParameterDataType) {
		try {
			WebElement element = driver.findElement(OR.objWebEdit_ParameterDataType_AddParameterPopup);
			if (element.isDisplayed()) {
				if (!(mstrParameterDataType.equalsIgnoreCase("NA"))) {
					Select ParameterName = new Select(element);
					for (int i = 0; i < ParameterName.getOptions().size(); i++) {
						String Text = ParameterName.getOptions().get(i).getText().trim();
						if (Text.equalsIgnoreCase(mstrParameterDataType)) {
							ParameterName.selectByIndex(i);
							return true;
						}
					}
				} else {
					// element.clear();
					return true;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_ParameterOptional_Feild_Disaplyed_AddParameterPopup ### Description
	 * of Function� To verify Parameter Optional feild at Add Parameter pop up ###
	 * Developed By � Rahul Warkhedkar ### Developed Date� 12/08/2021 ### List of
	 * Input Arguments� ### List of Output Arguments� Boolean ### Modified By� ###
	 * Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_ParameterOptional_Feild_Disaplyed_AddParameterPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebDropDown_ParameterOptional_AddParameterPopup);
			return cfnVerify_FeildDisplayed(element);
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnSelect_ParameterOptional_Feild_Disaplyed_AddParameterPopup ### Description
	 * of Function� To select Parameter Optional feild at Add Parameter pop up ###
	 * Developed By � Rahul Warkhedkar ### Developed Date� 12/08/2021 ### List of
	 * Input Arguments� ### List of Output Arguments� Boolean ### Modified By� ###
	 * Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnSelect_ParameterOptional_Feild_Disaplyed_AddParameterPopup(String mstrParameterOptional) {
		try {
			if (!(mstrParameterOptional.equalsIgnoreCase("NA"))) {
				WebElement element = driver.findElement(OR.objWebDropDown_ParameterOptional_AddParameterPopup);
				if (cfnVerify_FeildDisplayed(element)) {
					Select optional = new Select(element);
					for (int i = 0; i < optional.getOptions().size(); i++) {
						String mstrOptional = optional.getOptions().get(i).getText().trim();
						if (mstrOptional.equalsIgnoreCase(mstrParameterOptional)) {
							optional.selectByIndex(i);
							return true;
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_CreateButton_Feild_Disabled_AddParameterPopup ### Description of
	 * Function� To verify create button feild enabled ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� ###
	 * List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_CreateButton_Feild_Disabled_AddParameterPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_Create_AddParameterPopup);
			return cfnVerify_FeildEnabled(element);
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnClick_CreateButton_Feild_Disabled_AddParameterPopup ### Description of
	 * Function� To click create button feild enabled ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� ###
	 * List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnClick_CreateButton_Feild_Disabled_AddParameterPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_Create_AddParameterPopup);
			if (cfnVerify_FeildEnabled(element)) {
				element.click();
				Thread.sleep(2000);
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_CloseButton_Feild_Disabled_AddParameterPopup ### Description of
	 * Function� To verify close button feild enabled ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� ###
	 * List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_CloseButton_Feild_Disabled_AddParameterPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_Close_AddParameterPopup);
			return cfnVerify_FeildEnabled(element);
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnClick_CloseButton_Feild_Disabled_AddParameterPopup ### Description of
	 * Function� To click close button feild enabled ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� ###
	 * List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnClick_CloseButton_Feild_Disabled_AddParameterPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_Close_AddParameterPopup);
			if (cfnVerify_FeildEnabled(element)) {
				element.click();
				// Thread.sleep(2000);
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_ResetButton_Feild_Disabled_AddParameterPopup ### Description of
	 * Function� To verify Reset button feild enabled ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� ###
	 * List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_ResetButton_Feild_Disabled_AddParameterPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_Reset_AddParameterPopup);
			return cfnVerify_FeildEnabled(element);
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_ResetButton_Feild_Disabled_AddParameterPopup ### Description of
	 * Function� To verify Reset button feild enabled ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� ###
	 * List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnClick_ResetButton_Feild_Disabled_AddParameterPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_Reset_AddParameterPopup);
			if (cfnVerify_FeildEnabled(element)) {
				element.click();
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnSelect_ParameterOptional_Feild_Disaplyed_AddParameterPopup ### Description
	 * of Function� To select Parameter Optional feild at Add Parameter pop up ###
	 * Developed By � Rahul Warkhedkar ### Developed Date� 12/08/2021 ### List of
	 * Input Arguments� ### List of Output Arguments� Boolean ### Modified By� ###
	 * Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_ParameterCreationMessage_AddParameterPopup(String mstrMessage) {
		try {
			if (!(mstrMessage.equalsIgnoreCase("NA"))) {
				WebElement element = driver.findElement(OR.objWebElement_ParametrAddedd_Successfully_AddParameterPopup);
				if (cfnVerify_FeildDisplayed(element)) {
					String mstrMessageText = element.getText().trim();
					if (mstrMessageText.equalsIgnoreCase(mstrMessage)) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_ParameterDetails_ParameterConfigurationTable ### Description of
	 * Function� To verify parameter details at paramater configuration table ###
	 * Developed By � Rahul Warkhedkar ### Developed Date� 12/08/2021 ### List of
	 * Input Arguments� ### List of Output Arguments� Boolean ### Modified By� ###
	 * Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_ParameterDetails_ParameterConfigurationTable(String mstrParameterName,
			String mstrParameterDescription, String mstrParameterDataType) {
		try {
			if (!(mstrParameterName.equalsIgnoreCase("NA")) && !(mstrParameterDescription.equalsIgnoreCase("NA"))
					&& !(mstrParameterDataType.equalsIgnoreCase("NA"))) {
				List<WebElement> ParameterNameList = driver
						.findElements(OR.objWebTable_ParameterName_ColumnList_CommandParameter);
				List<WebElement> ParameterDescriptionList = driver
						.findElements(OR.objWebTable_ParameterDescription_ColumnList_CommandParameter);
				List<WebElement> ParameterDataTypeList = driver
						.findElements(OR.objWebTable_ParameterDataType_ColumnList_CommandParameter);
				// List<WebElement> ParameterOptionalList =
				// driver.findElements(OR.objWebTable_ParameterOptional_ColumnList_CommandParameter);
				for (int i = 0; i < ParameterNameList.size(); i++) {
					String mstrParameterNameText = ParameterNameList.get(i).getText().trim();
					System.out.println(mstrParameterNameText);
					if (mstrParameterNameText.equalsIgnoreCase(mstrParameterName.trim())) {
						String mstrParameterDescriptionText = ParameterDescriptionList.get(i).getText().trim();
						System.out.println(mstrParameterDescriptionText);
						System.out.println(mstrParameterDescription);
						String mstrParameterDataTypeText = ParameterDataTypeList.get(i).getText().trim();
						System.out.println(mstrParameterDataTypeText);
						System.out.println(mstrParameterDataType);
						// String mstrParameterOptionalText =
						// ParameterOptionalList.get(i).getText().trim();
						if (mstrParameterDescriptionText.equalsIgnoreCase(mstrParameterDescription.trim())
								&& mstrParameterDataTypeText.equalsIgnoreCase(mstrParameterDataType.trim())) {
							return true;
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnClick_Respective_EditButton_ParameterConfigurationTable ### Description of
	 * Function� To click repective edit button of parameter at parameter
	 * configuration table ### Developed By � Rahul Warkhedkar ### Developed Date�
	 * 12/08/2021 ### List of Input Arguments� ### List of Output Arguments� Boolean
	 * ### Modified By� ### Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnClick_Respective_EditButton_ParameterConfigurationTable(String mstrParameterName) {
		try {
			if (!(mstrParameterName.equalsIgnoreCase("NA"))) {
				List<WebElement> ParameterNameList = driver
						.findElements(OR.objWebTable_ParameterName_ColumnList_CommandParameter);
				List<WebElement> ParameterEditButton = driver
						.findElements(OR.objWebButton_EditParameter_ColoumnList_CommandParameter);
				for (int i = 0; i < ParameterNameList.size(); i++) {
					String mstrParameterNameText = ParameterNameList.get(i).getText().trim();
					if (mstrParameterNameText.equalsIgnoreCase(mstrParameterName)) {
						ParameterEditButton.get(i).click();
						return true;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnClick_UpdateButton_Feild_Disabled_EditParameterPopup ### Description of
	 * Function� To click update button feild enabled ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� ###
	 * List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnClick_UpdateButton_Feild_Disabled_EditParameterPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_Update_EditParameterPopup);
			if (cfnVerify_FeildEnabled(element)) {
				element.click();
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnClick_UpdateButton_Feild_Disabled_EditCommandPopup ### Description of
	 * Function� To click update button feild ### Developed By � Rahul Warkhedkar
	 * ### Developed Date� 12/08/2021 ### List of Input Arguments� ### List of
	 * Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnClick_UpdateButton_Feild_Disabled_EditCommandPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_Update_AddCommandPopup);
			if (cfnVerify_FeildEnabled(element)) {
				element.click();
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnClick_CloseButton_Feild_Disabled_EditParameterPopup ### Description of
	 * Function� To click close button feild enabled ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments� ###
	 * List of Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnClick_CloseButton_Feild_Disabled_EditParameterPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_Close_EditParameterPopup);
			if (cfnVerify_FeildEnabled(element)) {
				element.click();
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_AllFeilds_ResetToDefault_AtAddParameter_Popup ### Description of
	 * Function� To verify all feilds reset to default after clicking reset button
	 * at Add Parameter popup ### Developed By � Rahul Warkhedkar ### Developed
	 * Date� 12/08/2021 ### List of Input Arguments� ### List of Output Arguments�
	 * Boolean ### Modified By� ### Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_AllFeilds_ResetToDefault_AtAddParameter_Popup() {
		try {
			List<WebElement> objInputFeilds = driver.findElements(By.tagName("input"));
			int mintCount = 0;
			for (int i = 0; i < objInputFeilds.size(); i++) {
				String Text = objInputFeilds.get(i).getAttribute("value");
				if (Text.isEmpty()) {
					mintCount = mintCount + 1;
				}
			}
			boolean mblnselect = false;
			WebElement element = driver.findElement(OR.objWebEdit_ParameterDataType_AddParameterPopup);
			Select optional = new Select(element);
			String SelectedText = optional.getOptions().get(0).getText().trim();
			if (SelectedText.equalsIgnoreCase("Select Required")) {
				mblnselect = true;
			}
			if (mintCount == objInputFeilds.size() && mblnselect) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_Parameter_Activate_Or_Deactivate_ParameterConfiguration_FirstRow
	 * ### Description of Function� To activate or deactivate 1st row parameter at
	 * parameter configuration ### Developed By � Rahul Warkhedkar ### Developed
	 * Date� 12/08/2021 ### List of Input Arguments� ### List of Output Arguments�
	 * Boolean ### Modified By� ### Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_Parameter_Activate_Or_Deactivate_ParameterConfiguration_FirstRow(String mstrStatus) {
		try {
			String mstrADStatus = null;
			List<WebElement> objParameterName = driver
					.findElements(OR.objWebTable_ParameterName_ColumnList_CommandParameter);
			List<WebElement> objActivateDeactivate = driver
					.findElements(OR.objWebButton_ActivatedeactivateButton_ColoumnList_CommandParameter);
			mstrCaptureParameterName = objParameterName.get(0).getText().trim();
			String mstrClass = objActivateDeactivate.get(0).getAttribute("class");
			if (mstrClass.contains("active")) {
				mstrADStatus = "Activated";
			} else {
				mstrADStatus = "Deactivated";
			}
			if (mstrStatus.equalsIgnoreCase("Activate")) {
				if (mstrADStatus.equalsIgnoreCase("Deactivated")) {
					objActivateDeactivate.get(0).click();
					return true;
				}
				if (mstrADStatus.equalsIgnoreCase("Activated")) {
					return true;
				}
			}
			if (mstrStatus.equalsIgnoreCase("Deactivate")) {
				if (mstrADStatus.equalsIgnoreCase("Activated")) {
					objActivateDeactivate.get(0).click();
					return true;
				}
				if (mstrADStatus.equalsIgnoreCase("Deactivated")) {
					return true;
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_ParameterDropdown_ActivateDeactivateParameter_AddCommandPopup ###
	 * Description of Function� To verify activate or deactivate present or not at
	 * parameter dropdown at add command ### Developed By � Rahul Warkhedkar ###
	 * Developed Date� 12/08/2021 ### List of Input Arguments� ### List of Output
	 * Arguments� Boolean ### Modified By� ### Modified Date� ### Modification
	 * Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_ParameterDropdown_ActivateDeactivateParameter_AddCommandPopup(String mstrStatus) {
		try {
			WebElement element = driver.findElement(OR.objWebDropDown_CommandParameter_AddCommandPopup);
			Select ParameterName = new Select(element);
			int mintCount = 0;
			for (int i = 0; i < ParameterName.getOptions().size(); i++) {
				String Text = ParameterName.getOptions().get(i).getText().trim();
				if (Text.equalsIgnoreCase(mstrCaptureParameterName)) {
					mintCount = mintCount + 1;
				}
			}
			if (mstrStatus.equalsIgnoreCase("Activate") && mintCount == 1) {
				return true;
			}
			if (mstrStatus.equalsIgnoreCase("Deactivate") && mintCount == 0) {
				return true;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_SuccessMessage_AddCommandPopup ### Description of Function� To
	 * verify message at add command popup ### Developed By � Rahul Warkhedkar ###
	 * Developed Date� 12/08/2021 ### List of Input Arguments� ### List of Output
	 * Arguments� Boolean ### Modified By� ### Modified Date� ### Modification
	 * Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_SuccessMessage_AddCommandPopup(String mstrMessage) {
		try {
			WebElement element = driver.findElement(OR.objWebElement_CommandAddedd_Successfully_AddCommandPopup);
			if (cfnVerify_FeildDisplayed(element)) {
				String Text = element.getText().trim();
				if (Text.equalsIgnoreCase(mstrMessage)) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_ParameterDetails_ParameterConfigurationTable ### Description of
	 * Function� To verify parameter details at paramater configuration table ###
	 * Developed By � Rahul Warkhedkar ### Developed Date� 12/08/2021 ### List of
	 * Input Arguments� ### List of Output Arguments� Boolean ### Modified By� ###
	 * Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_CommandDetails_CommandConfigurationTable(String mstrCommandName,
			String mstrCommandDescription) {
		try {
			if (!(mstrCommandName.equalsIgnoreCase("NA")) && !(mstrCommandDescription.equalsIgnoreCase("NA"))) {
				List<WebElement> CommandNameList = driver
						.findElements(OR.objWebElement_CommandName_List_CommandConfigurationTable);
				List<WebElement> CommandDescriptionList = driver
						.findElements(OR.objWebElement_CommandDescription_List_CommandConfigurationTable);
				// List<WebElement> CommandParameterList =
				// driver.findElements(OR.objWebElement_CommandParameter_List_CommandConfigurationTable);
				// List<WebElement> CommandOptionalList =
				// driver.findElements(OR.objWebElement_CommandOptional_List_CommandConfigurationTable);
				for (int i = 0; i < CommandNameList.size(); i++) {
					String mstrCommandNameText = CommandNameList.get(i).getText().trim();
					if (mstrCommandNameText.equalsIgnoreCase(mstrCommandName)) {
						String mstrCommandDescriptionText = CommandDescriptionList.get(i).getText().trim();
						// String mstrCommandParameterText =
						// CommandParameterList.get(i).getText().trim();
						// String mstrCommandOptionalText = CommandOptionalList.get(i).getText().trim();
						if (mstrCommandDescriptionText.equalsIgnoreCase(mstrCommandDescription)) {
							return true;
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_AllFeilds_ResetToDefault_AtAddCommand_Popup ### Description of
	 * Function� To verify all feilds reset to default after clicking reset button
	 * at Add Command popup ### Developed By � Rahul Warkhedkar ### Developed Date�
	 * 12/08/2021 ### List of Input Arguments� ### List of Output Arguments� Boolean
	 * ### Modified By� ### Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_AllFeilds_ResetToDefault_AtAddCommand_Popup() {
		try {
			List<WebElement> objInputFeilds = driver.findElements(By.tagName("input"));
			int mintCount = 0;
			for (int i = 0; i < objInputFeilds.size(); i++) {
				String Text = objInputFeilds.get(i).getAttribute("value");
				if (Text.isEmpty()) {
					mintCount = mintCount + 1;
				}
			}
			// ======== Command Parameter DropDown ===========
			boolean mblnselect = false;
			WebElement element = driver.findElement(OR.objWebDropDown_CommandParameter_AddCommandPopup);
			Select optional = new Select(element);
			String SelectedText = optional.getOptions().get(0).getText().trim();
			if (SelectedText.equalsIgnoreCase("Select Parameter")) {
				mblnselect = true;
			}
			// =====Command Optional dropdown
			boolean mblnselect1 = false;
			WebElement element1 = driver.findElement(OR.objWebDropDown_ParameterOptional_AddParameterPopup);
			Select optional1 = new Select(element1);
			String SelectedText1 = optional1.getOptions().get(0).getText().trim();
			if (SelectedText1.equalsIgnoreCase("Select Required")) {
				mblnselect1 = true;
			}
			if (mintCount == objInputFeilds.size() && mblnselect && mblnselect1) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnClick_Respective_EditButton_CommandConfigurationTable ### Description of
	 * Function� To click repective edit button of command at command configuration
	 * table ### Developed By � Rahul Warkhedkar ### Developed Date� 12/08/2021 ###
	 * List of Input Arguments� ### List of Output Arguments� Boolean ### Modified
	 * By� ### Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnClick_Respective_EditButton_CommandConfigurationTable(String mstrCommandName) {
		try {
			if (!(mstrCommandName.equalsIgnoreCase("NA"))) {
				List<WebElement> CommandNameList = driver
						.findElements(OR.objWebElement_CommandName_List_CommandConfigurationTable);
				List<WebElement> CommandEditButton = driver
						.findElements(OR.objWebButton_EditCommand_ColoumnList_CommandConfiguration);
				for (int i = 0; i < CommandNameList.size(); i++) {
					String mstrParameterNameText = CommandNameList.get(i).getText().trim();
					if (mstrParameterNameText.equalsIgnoreCase(mstrCommandName)) {
						CommandEditButton.get(i).click();
						return true;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_Command_Activate_Or_Deactivate_CommandConfiguration_FirstRow ###
	 * Description of Function� To activate or deactivate 1st row command at command
	 * configuration ### Developed By � Rahul Warkhedkar ### Developed Date�
	 * 12/08/2021 ### List of Input Arguments� ### List of Output Arguments� Boolean
	 * ### Modified By� ### Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_Command_Activate_Or_Deactivate_CommandConfiguration_FirstRow(String mstrStatus) {
		try {
			System.out.println("I am in Activate function");
			String mstrADStatus = null;
			List<WebElement> objCommandName = driver
					.findElements(OR.objWebElement_CommandName_List_CommandConfigurationTable);
			System.out.println(objCommandName.size());
			List<WebElement> objActivateDeactivate = driver
					.findElements(OR.objWebButton_ActivatedeactivateButton_ColoumnList_CommandConfiguration);
			System.out.println(objActivateDeactivate.size());
			mstrCaptureCommandName = objCommandName.get(0).getText().trim();
			System.out.println(mstrCaptureCommandName);
			String mstrClass = objActivateDeactivate.get(0).getAttribute("class");
			if (mstrClass.contains("active")) {
				mstrADStatus = "Activated";
			} else {
				mstrADStatus = "Deactivated";
			}
			System.out.println(mstrADStatus);
			if (mstrStatus.equalsIgnoreCase("Activate")) {
				if (mstrADStatus.equalsIgnoreCase("Deactivated")) {
					objActivateDeactivate.get(0).click();
					return true;
				}
				if (mstrADStatus.equalsIgnoreCase("Activated")) {
					return true;
				}
			}
			if (mstrStatus.equalsIgnoreCase("Deactivate")) {
				if (mstrADStatus.equalsIgnoreCase("Activated")) {
					objActivateDeactivate.get(0).click();
					return true;
				}
				if (mstrADStatus.equalsIgnoreCase("Deactivated")) {
					return true;
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_CommandNameDropdown_ActivateDeactivateParameter_SendCommandPopup
	 * ### Description of Function� To verify activate or deactivate present or not
	 * at command name dropdown at send command ### Developed By � Rahul Warkhedkar
	 * ### Developed Date� 12/08/2021 ### List of Input Arguments� ### List of
	 * Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_CommandNameDropdown_ActivateDeactivateParameter_SendCommandPopup(String mstrStatus) {
		try {
			WebElement element = driver.findElement(OR.objWebDropDown_CommandName_SendCommand_Popup);
			Select ParameterName = new Select(element);
			int mintCount = 0;
			for (int i = 0; i < ParameterName.getOptions().size(); i++) {
				String Text = ParameterName.getOptions().get(i).getText().trim();
				if (Text.equalsIgnoreCase(mstrCaptureCommandName)) {
					mintCount = mintCount + 1;
				}
			}
			if (mstrStatus.equalsIgnoreCase("Activate") && mintCount == 1) {
				return true;
			}
			if (mstrStatus.equalsIgnoreCase("Deactivate") && mintCount == 0) {
				return true;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnClick_CloseButton_Feild_Disabled_SendCommandPopup ### Description of
	 * Function� To click close button feild ### Developed By � Rahul Warkhedkar ###
	 * Developed Date� 12/08/2021 ### List of Input Arguments� ### List of Output
	 * Arguments� Boolean ### Modified By� ### Modified Date� ### Modification
	 * Comments �
	 ******************************************************************************************************/
	public boolean cfnClick_CloseButton_Feild_Disabled_SendCommandPopup() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_Close_Button_SendCommand_Popup);
			if (cfnVerify_FeildEnabled(element)) {
				element.click();
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnSelect_Command_CommandNameDropDown_SendCommandPopup ### Description of
	 * Function� To select command from command name dropdown at send command pop up
	 * ### Developed By � Rahul Warkhedkar ### Developed Date� 12/08/2021 ### List
	 * of Input Arguments� Command Name ### List of Output Arguments� Boolean ###
	 * Modified By� ### Modified Date�
	 * 
	 * ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnSelect_Command_CommandNameDropDown_SendCommandPopup(String mstrCommandName) {
		try {
			WebElement element = driver.findElement(OR.objWebDropDown_CommandName_SendCommand_Popup);
			if (element.isDisplayed()) {
				if (!(mstrCommandName.equalsIgnoreCase("NA"))) {
					Select ParameterName = new Select(element);
					for (int i = 0; i < ParameterName.getOptions().size(); i++) {
						String Text = ParameterName.getOptions().get(i).getText().trim();
						if (Text.equalsIgnoreCase(mstrCommandName)) {
							ParameterName.selectByIndex(i);
							return true;
						}
					}
				} else {
					// element.clear();
					return true;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnSelect_Command_CommandNameDropDown_SendCommandPopup ### Description of
	 * Function� To select command from command name dropdown at send command pop up
	 * ### Developed By � Rahul Warkhedkar ### Developed Date� 12/08/2021 ### List
	 * of Input Arguments� Command Name ### List of Output Arguments� Boolean ###
	 * Modified By�Akshada Pawar ### Modified Date� 22/10/2021 ### Modification
	 * Comments �
	 ******************************************************************************************************/
	public boolean cfnSelect_Command_ProtocolDropDown_SendCommandPopup(String mstrCommandName) {
		try {
			WebElement element = driver.findElement(OR.objWebDropDown_Protocol_SendCommand_Popup);
			if (element.isDisplayed()) {
				if (!(mstrCommandName.equalsIgnoreCase("NA"))) {
					Select ParameterName = new Select(element);
					for (int i = 0; i < ParameterName.getOptions().size(); i++) {
						String Text = ParameterName.getOptions().get(i).getText().trim();
						if (Text.equalsIgnoreCase(mstrCommandName)) {
							ParameterName.selectByIndex(i);
							return true;
						}
					}
				} else {
					// element.clear();
					return true;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_Respective_ParameterFeild_SendCommand ### Description of Function�
	 * To verify respective parameter lable display ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments�
	 * Command Name ### List of Output Arguments� Boolean ### Modified By� ###
	 * Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_Respective_ParameterFeild_SendCommand(String mstrCommandName) {
		try {
			WebElement element = driver.findElement(OR.objWebElement_ParameterLable);
			if (element.isDisplayed()) {
				String Text = element.getText().trim();
				// System.out.println(Text);
				if (Text.contains(mstrCommandName)) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnSet_ParameterValue_SendCommandPopup ### Description of Function� To set
	 * respective parameter value at parameter value ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments�
	 * Parameter Value ### List of Output Arguments� Boolean ### Modified By� ###
	 * Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnSet_ParameterValue_SendCommandPopup(String mstrParametrValue) {
		try {
			if (!(mstrParametrValue.equalsIgnoreCase("NA"))) {
				WebElement element = driver.findElement(OR.objWebEdit_ParameterValue_SendCommand_Popup);
				if (element.isDisplayed()) {
					element.clear();
					element.sendKeys(mstrParametrValue);
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnSet_ParameterValue_SendCommandPopup ### Description of Function� To set
	 * respective parameter value at parameter value ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 18/10/2021 ### List of Input Arguments�
	 * Parameter Value ### List of Output Arguments� Boolean ### Modified By� ###
	 * Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnSet_OptionalValue_SendCommandPopup(String mstrParametrValue) {
		try {
			if (!(mstrParametrValue.equalsIgnoreCase("NA"))) {
				WebElement element = driver.findElement(OR.objWebEdit_ParameterValue_SendCommand_Popup);
				if (element.isDisplayed()) {
					element.clear();
					element.sendKeys(mstrParametrValue);
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnCheckEnabled_Click_SendCommandButton ### Description of Function� To
	 * verify enabled and click send command button ### Developed By � Rahul
	 * Warkhedkar ### Developed Date� 12/08/2021 ### List of Input Arguments�
	 * Parameter Value ### List of Output Arguments� Boolean ### Modified By� ###
	 * Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnCheckEnabled_Click_SendCommandButton() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_SendCommand_Button_SendCommand_Popup);
			if (element.isEnabled()) {
				element.click();
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_SendCommand_Message_SendCommandPopup ### Description of Function�
	 * To verify send command success message at send command popup ### Developed By
	 * � Rahul Warkhedkar ### Developed Date� 12/08/2021 ### List of Input
	 * Arguments� message ### List of Output Arguments� Boolean ### Modified By� ###
	 * Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_SendCommand_Message_SendCommandPopup(String mstrMessage) {
		try {
			WebElement element = driver.findElement(OR.objWebElement_Message_SendCommandPopup);
			if (element.isDisplayed()) {
				String Text = element.getText().trim();
				if (Text.equalsIgnoreCase(mstrMessage)) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnClick_CloseButton_SendCommand_Popup ### Description of Function� To close
	 * send command pop up ### Developed By � Rahul Warkhedkar ### Developed Date�
	 * 12/08/2021 ### List of Input Arguments� ### List of Output Arguments� Boolean
	 * ### Modified By� ### Modified Date� ### Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnClick_CloseButton_SendCommand_Popup() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_Close_Button_SendCommand_Popup);
			if (element.isDisplayed()) {
				element.click();
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnClick_ResetButton_SendCommand ### Description of Function� To click reset
	 * button at send command portal ### Developed By � Rahul Warkhedkar ###
	 * Developed Date� 12/08/2021 ### List of Input Arguments� ### List of Output
	 * Arguments� Boolean ### Modified By� ### Modified Date� ### Modification
	 * Comments �
	 ******************************************************************************************************/
	public boolean cfnClick_ResetButton_SendCommand() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_Reset_Button_SendCommand_Popup);
			if (element.isDisplayed()) {
				element.click();
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_CommandName_DropDown_AfterReset ### Description of Function� To
	 * verify command name dropdown after reset ### Developed By � Rahul Warkhedkar
	 * ### Developed Date� 12/08/2021 ### List of Input Arguments� ### List of
	 * Output Arguments� Boolean ### Modified By� ### Modified Date� ###
	 * Modification Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_CommandName_DropDown_AfterReset() {
		try {
			WebElement element = driver.findElement(OR.objWebDropDown_CommandName_SendCommand_Popup);
			Select CommandName = new Select(element);
			String Text = CommandName.getFirstSelectedOption().getText().trim();
			if (Text.equalsIgnoreCase("Select Parameter")) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	/****************************************************************************************************
	 * ### Name of Project � DAF_M2M_COMMAND_PORTAL ### Name of Function �
	 * cfnVerify_SendCommand_ButtonDisabled ### Description of Function� To verify
	 * send command button disabled ### Developed By � Rahul Warkhedkar ###
	 * Developed Date� 12/08/2021 ### List of Input Arguments� ### List of Output
	 * Arguments� Boolean ### Modified By� ### Modified Date� ### Modification
	 * Comments �
	 ******************************************************************************************************/
	public boolean cfnVerify_SendCommand_ButtonDisabled() {
		try {
			WebElement element = driver.findElement(OR.objWebButton_SendCommand_Button_SendCommand_Popup);
			if (element.isEnabled()) {
				element.click();
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

}
