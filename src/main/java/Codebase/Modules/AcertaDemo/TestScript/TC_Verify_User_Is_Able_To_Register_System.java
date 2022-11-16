//*******************************************************************
//Name of Project � DAF_M2M_COMMAND_PORTAL 
//Name of Test Script � TC_Verify_Login_Functionality_Command_Portal_Application
//Description of Test Script � To verify login functionality of command portal application
//Developed By � Rahul Warkhedkar
//Developed Date � 11/08/2021
//Modified By �
//Modified Date �
//Modification Comments � 
//*******************************************************************
package Codebase.Modules.AcertaDemo.TestScript;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Codebase.FunctionLibrary.CfnLib;
import Codebase.FunctionLibrary.Global;
import Codebase.FunctionLibrary.HTML_Report;
import Codebase.Modules.AcertaDemo.FunctionLibrary.ReusableFunctions;
import org.openqa.selenium.support.ui.Select;

public class TC_Verify_User_Is_Able_To_Register_System {
	HTML_Report reporter;
	RemoteWebDriver driver;
	boolean mblnCurrentStep = false;
	boolean mblnNextStep = false;

	@Test
	@Parameters({ "ModuleName", "ExecutionURL", "browserName" })
	public void test(String strModuleName, String URL, String browserName) throws Throwable {
		try {
			Global ObjGlobal = new Global();
			driver = ObjGlobal.invokeBrowser();
			String strTestCaseName = this.getClass().getSimpleName();
			CfnLib objCfn = new CfnLib(strModuleName, strTestCaseName, driver);
			reporter = objCfn.objReport;
			reporter.mstrTC_Desc = objCfn.mstrTC_Desc;
			reporter.mstrModuleName = strModuleName;
			reporter.mstrTC_Name = strTestCaseName + "_" + browserName;
			ReusableFunctions appSpecific = new ReusableFunctions(driver);

			// --------Step 1: Application Launch -------------
			/*
			 * reporter.details_append("To Register the user in the System", "", "", "");
			 * mblnCurrentStep = appSpecific.cfnVerify_ApplicationLaunch();
			 * System.out.println(mblnCurrentStep); if (mblnCurrentStep) {
			 * reporter.details_append("To Verify Application launch",
			 * "User should able to login Application", "User is able to login Application",
			 * "Pass"); mblnNextStep = true; } else {
			 * reporter.details_append("To Verify Application launch",
			 * "User should able to login Application",
			 * "User is unable to login Application", "Fail"); mblnNextStep = false; }
			 */

			// ---------Step 2 : Click on Register--------------
			reporter.details_append("To Register the user in the System", "", "", "");
			mblnCurrentStep = appSpecific.cfnRegister();
			System.out.println(mblnCurrentStep);
			if (mblnCurrentStep) {
				reporter.details_append("Register User in the System", "User should able to Register", "User is able to Register", "Pass");
				mblnNextStep = true;
			} else {
				reporter.details_append("Register User in the System", "User should able to Register", "User is unable to Register", "Fail");
				mblnNextStep = false;
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
//			driver.close();
//			reporter.end_Report();
		}
	}
}
