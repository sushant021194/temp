package Codebase.FunctionLibrary;

public class AgentDetails {
	String ModuleName,AgentDetails,Browser;
	String EnvURL,ExcOn,ExecType;
	int intFlag;
	
	 public AgentDetails(String strModuleName,String strAgD,String strBrowser,String strURL,String strExcOn,String strExcType){
		 ModuleName=strModuleName;
		 AgentDetails=strAgD;
		 Browser=strBrowser;
		 EnvURL=strURL;
		 ExcOn=strExcOn;
		 ExecType=strExcType;
		 intFlag=-1;
		 
	 }
	 public void setFlag1(int value){
		 intFlag=value;
	 }
	 
	
	 
	 public String getModuleName(){
		 return ModuleName;
	 }
	 public String getAgentDetails(){
		 return AgentDetails;
	 }
	 public String getBrowser(){
		 return Browser;
	 }
	 public String getEnvURL(){
		 return EnvURL;
	 }
	 public String getExcOn(){
		 return ExcOn;
	 }
	 public String getExecType(){
		 return ExecType;
	 }
	 
	 
}
