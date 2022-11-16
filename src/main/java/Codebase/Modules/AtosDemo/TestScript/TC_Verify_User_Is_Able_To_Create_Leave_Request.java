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
package Codebase.Modules.AtosDemo.TestScript;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Codebase.FunctionLibrary.CfnLib;
import Codebase.FunctionLibrary.Global;
import Codebase.FunctionLibrary.HTML_Report;
import Codebase.Modules.AtosDemo.FunctionLibrary.ReusableFunctions;

public class TC_Verify_User_Is_Able_To_Create_Leave_Request {
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

			// -------- Application Launch and Login-------------
			reporter.details_append("To verify application Login functionality", "", "", "");
			mblnCurrentStep = appSpecific.cfnLogin();
			System.out.println(mblnCurrentStep);
			if (mblnCurrentStep) {
				reporter.details_append("To Verify Application launch", "User should able to login Application",
						"User is able to login Application", "Pass");
				mblnNextStep = true;
			} else {
				reporter.details_append("To Verify Application launch", "User should able to login Application",
						"User is unable to login Application", "Fail");
				mblnNextStep = false;
			}
			// ---------Create Leave request------------
			System.out.println(mblnNextStep);
			if (mblnNextStep) {
				reporter.details_append("To verify Create Leave Request functionality", "", "", "");
				mblnCurrentStep = appSpecific.cfnCreateLeave();
				System.out.println(mblnCurrentStep);
				if (mblnCurrentStep) {
					reporter.details_append("To Create Leave request", "User should able to Create Leave Request",
							"User is able to Create Leave Request", "Pass");
					 mblnNextStep = true;
				} else {
					reporter.details_append("To Create Leave request", "User should able to Create Leave Request",
							"User is unable to Create Leave Request", "Fail");
					 mblnNextStep = false;
				}
				// System.out.println(mblnNextStep);

			}

			else {
				System.out.println("inside the else block");
				reporter.details_append("To Create Leave request", "User should able to Create Leave Request",
						"User is unable to Create Leave Request", "Skipped");
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
//			driver.close();
//			reporter.end_Report();
		}
	}
}
