package Codebase.root;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Codebase.FunctionLibrary.AgentDetails;
import Codebase.FunctionLibrary.ExcelDataPoolManager;
import Codebase.FunctionLibrary.Global;
import Codebase.FunctionLibrary.HTML_Report;
import Codebase.FunctionLibrary.TestCaseReader;


class MachineThread extends Thread 
{
	AgentDetails objAgent;
	MachineThread(AgentDetails Agent)
	{
		objAgent=Agent;
		//		mstrTestCaseName=TestCasename;
		//		mstrMachineAddress=MachineIp;
		//		mstrBrowser=Browser;
		//		mstrCompatibility=Compatibility;
	}


	public void run() 
	{
		try
		{
			//--------------------Assigning Driver to respective TestCase and Machine  
			Driver.DriverObject[Driver.mintAssigned]=new TestNGMain();

			Driver.DriverObject[Driver.mintAssigned].cfnDriver(objAgent);

		}
		catch(Exception e)
		{
			//System.out.println("Error Occured");
			e.printStackTrace();
		}
	}
}


@SuppressWarnings("rawtypes")
public class Driver {

	public static List<Map> ModulesList,ConfigList; 
	static ExcelDataPoolManager objEDPM=new ExcelDataPoolManager();
	static MachineThread[] gThrmachine;
	static DateFormat mObjDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	static String mstrTestCase;
	static int mintAssigned;
	static TestNGMain[] DriverObject;
	Global objGlobal=new Global();
	public List<AgentDetails> AgentList=new ArrayList<AgentDetails>();	


	public void CreateAgentProfile(int i){
		try {
			//----------  Reading Excel Module Suit File On the Basis of Run Flag -------------

			AgentList=new ArrayList<AgentDetails>();	

			String mname=objEDPM.getValueByKey(ModulesList.get(i).entrySet(),"moduleName");
			//System.out.println(mname);
			String AgentName=objEDPM.getValueByKey(ModulesList.get(i).entrySet(),"AgentDetails");
			//System.out.println(AgentName);
			String Browser=objEDPM.getValueByKey(ModulesList.get(i).entrySet(),"Browser");
			//System.out.println(Browser);
			String EnvURL=objEDPM.getValueByKey(ModulesList.get(i).entrySet(),"Enviorment");
			//System.out.println(EnvURL);
			EnvURL=TestCaseReader.getConfig(EnvURL);

			String ExcOn=objEDPM.getValueByKey(ModulesList.get(i).entrySet(),"ExecutionEnv");
			System.out.println(ExcOn);
			//				if(!ExcOn.equalsIgnoreCase("Remote")){
			//					ExcOn=TestCaseReader.getConfig(ExcOn);
			//				}else{
			//					ExcOn=TestCaseReader.getConfig(AgentName);
			//				}				
			String ExecType=objEDPM.getValueByKey(ModulesList.get(i).entrySet(),"ExecutionType");								

			//				int index1=-1;
			//				if(Browser.split(",").length>1){
			//					if(Browser.split(",")[0].equalsIgnoreCase(Browser.split(",")[1])){
			//						index1=1;						
			//					}
			//					if(Browser.split(",").length>2){
			//						if(Browser.split(",")[0].equalsIgnoreCase(Browser.split(",")[2])){
			//							index1=2;						
			//						}
			//						if(Browser.split(",")[1].equalsIgnoreCase(Browser.split(",")[2])){
			//							index1=3;						
			//						}
			//						if((Browser.split(",")[0].equalsIgnoreCase(Browser.split(",")[1]))&&(Browser.split(",")[0].equalsIgnoreCase(Browser.split(",")[2]))){
			//							index1=4;						
			//						}
			//					}
			//				}				
			//				int countPart=0;
			int AgentCount=0;
			String TempAgent=ExcOn;
			do{		
				//System.out.println(AgentName.split(",")[AgentCount].toString());
				if(!TempAgent.equalsIgnoreCase("Remote")){

					ExcOn=TestCaseReader.getConfig(ExcOn);
				}else{
					ExcOn=TestCaseReader.getConfig(AgentName.split(",")[AgentCount].toString());
				}	
				AgentDetails objAgent=new AgentDetails(mname, AgentName.split(",")[AgentCount].toString(),
						Browser.split(",")[AgentCount].toString(),EnvURL,ExcOn,ExecType);					
				AgentCount++;
				//					if(index1==4){
				//						objAgent.setFlag1(3);	
				//					}
				//					if((index1!=-1)&&(index1==AgentCount))
				//					{
				//						objAgent.setFlag1(countPart++);
				//					}
				AgentList.add(objAgent);
			}while(AgentCount < AgentName.split(",").length);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Driver objDriver=new Driver();
			Global.cfnRootPath();
			ModulesList= objEDPM.readExcel(Global.gstrSuitFilePath+"SuitFile"+".xls","Config File");
			for (int mCount=0;mCount<ModulesList.size();mCount++){
				objDriver.CreateAgentProfile(mCount);

				Global.gintMachineCount=objDriver.AgentList.size();
		System.out.println("Count ="+Global.gintMachineCount);
				// Initiating The Batching Of Test scripts		
				Driver ObjTestNG=new Driver();
				ObjTestNG.cfnCallModule(objDriver.AgentList);		

				int stop=0;
				while(stop!=Global.gintMachineCount)
					for(int i=0;i<Global.gintMachineCount;i++)
					{
						if (gThrmachine[i].isAlive()==false)//all thread killed then
						{
							stop+=1;
							if(stop==Global.gintMachineCount)
							{
								System.out.println("Execution Completed");
								//////////////////////////////CODE TO OPEN HTML FILE AT THE END OF EXECUTION
								HTML_Report.build_Automation_Summary();
								String mstrAutoSummPath=Global.gstrResultPath+"/Automation_summary.html";
								String cmds[] = new String[] {"cmd", "/c",mstrAutoSummPath};  
								Runtime.getRuntime().exec(cmds); 	break;
							}

						}
						else
							stop=0;
					}
				
			}
				//HTML_Report.Clean_Record();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public  void cfnCallModule(List<AgentDetails> AgentList) throws Exception 
		{
			//------------- Declaration Of Different Machine/ System For Parallel Execution-----------
			gThrmachine=	new MachineThread[Global.gintMachineCount];
			//------------- Creation of Individual Driver For Each Machine------------
			DriverObject=	new TestNGMain[Global.gintMachineCount];


			for (int i = 0; i < Global.gintMachineCount; i++)
			{
				gThrmachine[i]=new MachineThread(AgentList.get(i)); // Intilaize Machine Thread With Default Value.  
				gThrmachine[i].start();
			}		

			//Global.gintRowCount++;
			try
			{	
				mintAssigned=0;				
				int stop=0;
				while(stop!=Global.gintMachineCount)
					for(int i=0;i<Global.gintMachineCount;i++)
					{
						if (gThrmachine[i].isAlive()==false)//all thread killed then
						{
							stop+=1;
							if(stop==Global.gintMachineCount)
							{
								//System.out.println("Wait for module to end");
								//wait till all thread of module complete there task
								break;
							}
						}
						else
							stop=0;
					}		
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
