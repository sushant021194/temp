package Codebase.FunctionLibrary;
import java.util.Map;


public class TestCaseReader {
	public static String mstrValue;
	//public String mstrModuleName;

	@SuppressWarnings("rawtypes")
	public static String getTestDescription(String mstrTestCaseName,Global objGlobal) 
	{		
		try{

			String name,SheetName="Config File";
			ExcelDataPoolManager oExcelFile = new ExcelDataPoolManager();
			Map rowData = null;
			int rowcount =oExcelFile.rowCount(objGlobal.gstrModuleSuitFilePath+objGlobal.gstrModuleName+".xls",SheetName);
			for(int i=0;i<rowcount;i++)
			{
				rowData = oExcelFile.readExcelByRow(objGlobal.gstrModuleSuitFilePath+objGlobal.gstrModuleName+".xls",SheetName , i);
				name=rowData.get("TestCaseID").toString();
				if(name.equalsIgnoreCase(mstrTestCaseName))
				{
					mstrValue=rowData.get("TestCaseDescription").toString();					
					return mstrValue;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println();
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static String getConfig(String KeyValue) 
	{
		String SheetName;
		try{		
			SheetName="Config File";			
			ExcelDataPoolManager oExcelFile = new ExcelDataPoolManager();			
			Map rowData = null;
			String strKey;
			int rowcount =oExcelFile.rowCount(Global.gstrConfigFilesPath+"Config"+".xls",SheetName);
			for(int i=1;i<rowcount;i++)
			{
				rowData = oExcelFile.readExcelByRow(Global.gstrConfigFilesPath+"Config"+".xls",SheetName , i);
				strKey=rowData.get("Key").toString();
				if(strKey.equalsIgnoreCase(KeyValue))
				{
					mstrValue=rowData.get("Value").toString();					
					return mstrValue;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
