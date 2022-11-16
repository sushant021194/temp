package Codebase.Modules.AcertaDemo.ObjectRepository;

import org.openqa.selenium.By;

public class Locators {
	
	//----------- Local Objects - Login Page -----------------
	
	//----------- Local Object - Landing Page ----------------
	public By objWebElement_LHS_MenuHeader = By.xpath("//*[@id='sidebar']/li/span");
	public By objWebElementList_LHS_MenuItems = By.xpath("//*[@id='sidebar']/li/a/span");
	public By objWebElement_Page_Header = By.xpath("//*[@id='page-heading']/h1");
	public By objWebButton_Add_CommonButton = By.xpath("//*[@id='page-heading']/div/div/button");
	public By objWebElement_Popup_Header = By.xpath("//*[@id='centralModalSm_for_addparameter']/div/div/div[1]/h4");
	public By objWebElementAddParameter_Popup_Header = By.xpath("//*[@id='centralModalSm_for_addparameter']/div/div/div[1]/h4");

	public By objWebElement_AddCommand_Popup_Header = By.xpath("//*[@id=\"centralModalSm_for_addcommand\"]/div/div/div[1]/h4");
	public By objWebElement_SendCommand_Popup_Header = By.xpath("//*[@id=\"centralModalSm_for_addaspect\"]/div/div/div[1]/h4");

	public By objWebElement_Popup_Close = By.xpath("//button[@class='close']");
	public By objWebImage_LogoutDrop = By.xpath("//app-header/header/ul/li[1]/a/span/i");
	public By objWebElement_Logout = By.xpath("//app-header/header/ul/li[1]/ul/li[2]/ul/li/a");
	
	//---------- Send Command Page -----------------------
	public By objWebElement_SearchAssetLable = By.xpath("//label[@class='search-lable']/strong");
	public By objWebEdit_SearchAsset = By.xpath("//input[@id='assetName']");
	public By objWebButton_SearchAsset = By.xpath("//*[@id='chk']");
	public By objWebButton_Checkbox = By.xpath("//*[@id='chk']");

	public By objWebTable_ColoumName_List = By.xpath("//*[@id='wrap']/div[3]/div[2]/div/div/table/thead/tr/th");
	
	public By objWebDropDown_CommandName_SendCommand_Popup = By.xpath("//select[@id='commandName']");
	public By objWebElement_ParameterLable = By.xpath("//table/tbody/tr/td[1]");
	public By objWebDropDown_Protocol_SendCommand_Popup = By.xpath("//select[@id='protocolName']");

	public By objWebEdit_ParameterValue_SendCommand_Popup = By.xpath("//*[@id='param_1']");
	public By objWebButton_SendCommand_Button_SendCommand_Popup = By.xpath("//*[@id='centralModalSm_for_addaspect']/div/div/div[2]/form/div[4]/button[1]");
	public By objWebButton_Close_Button_SendCommand_Popup = By.xpath("//*[@id='centralModalSm_for_addaspect']/div/div/div[2]/form/div/button[2]");
	public By objWebButton_Reset_Button_SendCommand_Popup = By.xpath("//*[@id=\"centralModalSm_for_addaspect\"]/div/div/div[2]/form/div/button[3]");
	public By objWebElement_Message_SendCommandPopup = By.xpath("//*[@class='alert alert-dismissable alert-success']/span");
	
	//-------- Command Configuration Page ---------------
	public By objWebTable_ColoumName_List_CommandConfiguration = By.xpath("//*[@id='wrap']/div[2]/div[3]/div/div/table/thead/tr/th");
	public By objWebEdit_CommandName_AddCommandPopup = By.xpath("//input[@id='commandName']");
	public By objWebEdit_CommandDescription_AddCommandPopup = By.xpath("//input[@id='commandDescription']");
	public By objWebDropDown_CommandParameter_AddCommandPopup = By.xpath("//select[@id='parameterid']");
	public By objWebEdit_CommandOptional_AddCommandPopup = By.xpath("//select[@id='commandRequired']");
	public By objWebButton_Create_AddCommandPopup = By.xpath("//button[text()=' Save ']");
	public By objWebButton_Add_AddCommandPopup= By.xpath("//button[text()=' Add ']");

	public By objWebButton_Close_AddCommandPopup = By.xpath("//button[text()=' Close ']");
	public By objWebButton_Reset_AddCommandPopup = By.xpath("//button[text()=' Reset ']");
	public By objWebElement_CommandAddedd_Successfully_AddCommandPopup = By.xpath("//span[text()='Command Added Successfully']");
	public By objWebButton_Update_AddCommandPopup = By.xpath("//*[@id='centralModalSm_for_addcommand']/div/div/div[2]/form/div[6]/button[1]");
	
	public By objWebElement_CommandName_List_CommandConfigurationTable = By.xpath("//app-command-configuration/div/div/div[2]/div[3]/div/div/table/tbody/tr/td[1]");
	public By objWebElement_CommandDescription_List_CommandConfigurationTable = By.xpath("//app-command-configuration/div/div/div[2]/div[3]/div/div/table/tbody/tr/td[2]");
	public By objWebElement_CommandParameter_List_CommandConfigurationTable = By.xpath("//*[@id='wrap']/div[2]/div[3]/div/div/table/tbody/tr/td[3]");
	public By objWebElement_CommandOptional_List_CommandConfigurationTable = By.xpath("//*[@id='wrap']/div[2]/div[3]/div/div/table/tbody/tr/td[4]");
	public By objWebElement_UpdatedDate_List_CommandConfigurationTable = By.xpath("//*[@id='wrap']/div[2]/div[3]/div/div/table/tbody/tr/td[5]");
	public By objWebElement_CommandAction_List_CommandConfigurationTable = By.xpath("//*[@id='wrap']/div[2]/div[3]/div/div/table/tbody/tr/td[6]");
	public By objWebButton_EditCommand_ColoumnList_CommandConfiguration = By.xpath("//*[@id=\"wrap\"]/div[2]/div[3]/div/div/table/tbody/tr/td[5]/a");
	public By objWebButton_ActivatedeactivateButton_ColoumnList_CommandConfiguration =By.xpath("//app-command-configuration/div/div/div[2]/div[3]/div/div/table/tbody/tr/td[5]/button");
	//---------- Command Histoy Page ----------------
	public By objWebTable_Coloum_List_CommandHistory = By.xpath("//*[@id='wrap']/div[2]/div[2]/div/div/table/thead/tr/th");
	
	
	//--------- Command Parameter---------------------
	public By objWebTable_Coloum_List_CommandParameter = By.xpath("//*[@id='wrap']/div[2]/div[3]/div/div/table/thead/tr/th");
	public By objWebEdit_ParameterName_AddParameterPopup = By.xpath("//input[@id='parametername']");
	public By objWebEdit_ParameterDescription_AddParameterPopup = By.xpath("//input[@id='parameterdesc']");
	public By objWebEdit_ParameterDataType_AddParameterPopup = By.xpath("//select[@id='parameterdatatype']");
	public By objWebDropDown_ParameterOptional_AddParameterPopup = By.xpath("//select[@id='commandRequired']");
	public By objWebButton_Create_AddParameterPopup = By.xpath("//button[text()=' Save ']");
	public By objWebButton_Close_AddParameterPopup = By.xpath("//button[text()=' Close ']");
	public By objWebButton_Reset_AddParameterPopup = By.xpath("//*[@id=\"centralModalSm_for_addparameter\"]/div/div/div[2]/form/div[5]/button[3]");
	public By objWebButton_Update_EditParameterPopup = By.xpath("//*[@id=\"centralModalSm_for_addparameter\"]/div/div/div[2]/form/div[5]/button[1]");
	public By objWebButton_Close_EditParameterPopup = By.xpath("//*[@id=\"centralModalSm_for_addparameter\"]/div/div/div[2]/form/div[5]/button[2]");
	public By objWebElement_ParametrAddedd_Successfully_AddParameterPopup = By.xpath("//*[@id=\"centralModalSm_for_addparameter\"]/div/div/div[2]/div/span");	
	
	public By objWebTable_ParameterName_ColumnList_CommandParameter = By.xpath("//app-command-parameter/div/div/div[2]/div[3]/div/div/table/tbody/tr/td[1]");
	public By objWebTable_ParameterDescription_ColumnList_CommandParameter = By.xpath("//app-command-parameter/div/div/div[2]/div[3]/div/div/table/tbody/tr/td[2]");
	public By objWebTable_ParameterDataType_ColumnList_CommandParameter = By.xpath("//app-command-parameter/div/div/div[2]/div[3]/div/div/table/tbody/tr/td[3]");
	public By objWebTable_ParameterUpdatedDate_ColumnList_CommandParameter = By.xpath("//*[@id='wrap']/div[2]/div[3]/div/div/table/tbody/tr/td[4]");
	public By objWebTable_ParameterOptional_ColumnList_CommandParameter = By.xpath("//*[@id='wrap']/div[2]/div[3]/div/div/table/tbody/tr/td[5]");
	public By objWebTable_ParameterAction_ColumnList_CommandParameter = By.xpath("//*[@id='wrap']/div[2]/div[3]/div/div/table/tbody/tr/td[6]");
	public By objWebButton_EditParameter_ColoumnList_CommandParameter = By.xpath("//*[@id='wrap']/div[2]/div[3]/div/div/table/tbody/tr/td[5]/a");
	public By objWebButton_ActivatedeactivateButton_ColoumnList_CommandParameter =By.xpath("//app-command-parameter/div/div/div[2]/div[3]/div/div/table/tbody/tr[1]/td[5]/button");
	public By objadvancedclick = By.xpath("//*[@id='details-button']");
	public By objunsafelinkclick = By.xpath("//a[text()='Proceed to dev-m2m.westeurope.cloudapp.azure.com (unsafe)']");
	public By objuseanotheract = By.xpath("//div[text()='Use another account']");
	public By objPassSignInPage = By.xpath("//input[@name='password']");
	public By objclickNextBtnSignInPage = By.xpath("//input[@value='Next']");
	//input[@name='passwd']
	
	public By objclickSignInBtnSignInPage = By.xpath("//input[@name='submit']");
	
	public By objclickSkipfornowLinkSignInPage = By.xpath("//a[text()='Skip for now (14 days until this is required)']");
	public By objNoBtnSignInPage = By.xpath("//input[@value='No']");
	public By objEmailSignInPage = By.xpath("//input[@name='userName']");
	public By objWebElement_SignIn_Page = By.xpath("//div[text()='Sign in']");
		
	
	//Acerta Demo Locators
	public By objWebElement_Register = By.xpath("//a[contains(text(),'REGISTER')]");
	//public By objWebElement_Register=//input[@name='firstName']
}