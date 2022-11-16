//------------Package Name----------------//
package Codebase.FunctionLibrary;

//------------Importing Required Packages----------------//
import java.io.FileInputStream; 

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;



//-------------ExcelDataPoolManager class---------------//
/**
 * Excel Data pool manager class reads the suit file in .xls format and decides which module to be run
 * and which scripts to be run.
 * @author 
 */



/****************************************************************************************************
### Class NAME  	: ExcelDataPoolManager
### AUTHOR			: Devesh Mishra
### CREATED ON		: DD/MM/YY
### PURPOSE			: The Class is built to access the functionality of excell in java
### DATE REVIEWED	:
### REVIEWED BY		:
### UPDATED BY		: Devesh Mishra
### LAST UPDATED	: 26/04/2013
******************************************************************************************************/
public class ExcelDataPoolManager {

	InputStream minputStream = null ;
	InputStream minputStreamReadRow = null ;
	HSSFWorkbook mhssfwrkbokWorkbook;
	HSSFRow mhssfrowRow = null;
	HSSFCell mhssfcellCell = null;
	HSSFSheet msheetSheet ; 
	
	@SuppressWarnings("rawtypes")
	Iterator mitrRows,mitrCells;	
	
	@SuppressWarnings("rawtypes")
	Set set = null ;

	@SuppressWarnings("rawtypes")
	List<Map> mlistData;
	int noOfRows=0;
	//------------Constructor ExcelDataPoolManager ----------------//
	public ExcelDataPoolManager() {
		super();
	}
	
	
	/**
	 * This function reads the specified sheet in Excel file provided by the arguments
	 * @param XLS_FILE_PATH Path of the suit file
	 * @param sSheetName Name of the sheet to be read
	 * @return List
	 * @throws Exception 
	 */
	//------------readExcel() function----------------//

	@SuppressWarnings("rawtypes")
	public List<Map> readExcel(String XLS_FILE_PATH , String sSheetName) throws Exception 
	{
		try{
			mlistData = new ArrayList<Map>();
			//------------Try block----------------//		 

			//------------Declare Excel Sheet Variables----------------//
			minputStream = new FileInputStream(XLS_FILE_PATH);
			mhssfwrkbokWorkbook = new HSSFWorkbook(minputStream);
			mhssfrowRow = null;
			mhssfcellCell = null;
			msheetSheet = mhssfwrkbokWorkbook.getSheet(sSheetName); 
			mitrRows = msheetSheet.rowIterator();
			noOfRows = msheetSheet.getPhysicalNumberOfRows();
			set = null ;
			Map<String,String> hm = null;
			int intFlag=0;
			int intRunFlagIndex=0;
			//------------Read no of modules to run----------------//
			for (int i = 1; (i < noOfRows) && (mitrRows.hasNext()); i++)
			{
				hm = new HashMap<String,String>(); 
				mhssfrowRow = (HSSFRow) mitrRows.next();
				mitrCells = mhssfrowRow.cellIterator();
				for(int k=0;mitrCells.hasNext();k++){
					//System.out.println(msheetSheet.getRow(0).getCell(k));
					if(msheetSheet.getRow(0).getCell(k).toString().equalsIgnoreCase("RUNFLAG")){
						intRunFlagIndex=k;
						break;
					}
					
				}
				intFlag=0;
				for( int j=0 ;mitrCells.hasNext() ; j++) 
				{
					mitrCells.next();	
					//System.out.println(j);
					if(msheetSheet.getRow(i).getCell(j)!=null){
						//System.out.println(msheetSheet.getRow(i).getCell(intRunFlagIndex));
						if(msheetSheet.getRow(i).getCell(intRunFlagIndex).toString().equalsIgnoreCase("run")){
							//System.out.println(msheetSheet.getRow(0).getCell(j));
							//System.out.println(msheetSheet.getRow(i).getCell(j));
							hm.put(getValue(msheetSheet.getRow(0).getCell(j)), getValue(msheetSheet.getRow(i).getCell(j)));
							intFlag=1;
						}
					}

				}
				//------------Get a set of the entries----------------// 
				if(intFlag==1){
					mlistData.add(hm); 
					if (minputStream != null) 
					{
						minputStream.close();
						minputStream = null;
					}
					minputStream = null;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//------------Return from function----------------//
		return(mlistData);
	}

	
	/**
	 * This function reads the Excel file By row
	 * @param XLS_FILE_PATH Path of the suit file
	 * @param sSheetName Name of the sheet to be read
	 * @param iRow Total no of rows
	 * @return Map
	 * @throws IOException 
	 */
	//------------readExcelByRow() function----------------//

	@SuppressWarnings("rawtypes")
	public Map<String,String> readExcelByRow(String XLS_FILE_PATH , String sSheetName , int iRow) throws IOException {
		
		@SuppressWarnings("unused")
		List<Map> data = new ArrayList<Map>();
		Map<String,String> hm = null;


		//------------Declare Excel Sheet Variables----------------// 
		minputStreamReadRow = new FileInputStream(XLS_FILE_PATH);
		mhssfwrkbokWorkbook = new HSSFWorkbook(minputStreamReadRow);
		mhssfrowRow = null;
		mhssfcellCell = null;
		msheetSheet = mhssfwrkbokWorkbook.getSheet(sSheetName); 
		mitrRows = msheetSheet.rowIterator();
		noOfRows = msheetSheet.getPhysicalNumberOfRows();
		set = null ;
		int i = 1;

		//------------Count total no of scripts in a Module----------------// 
		for (; (i < noOfRows) && (mitrRows.hasNext()); i++) {
			if( i == iRow ) {
				break;
			}
		}

		hm = new HashMap<String,String>(); 
		mhssfrowRow = (HSSFRow) mitrRows.next();
		mitrCells = mhssfrowRow.cellIterator();
		int j=0 ;
		for( ;mitrCells.hasNext() ; j++) {
			mitrCells.next();
			hm.put( getValue(msheetSheet.getRow(0).getCell(j)) , getValue(msheetSheet.getRow(iRow).getCell(j , Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)));
		}

		if (minputStreamReadRow != null) 
		{
			minputStreamReadRow.close();
			minputStreamReadRow = null;
		}
		minputStreamReadRow = null;


		//------------Return from function----------------//
		return(hm);
	}

	
	/**
	 * This function shows the data of the Specified row 
	 * @param hm 
	 * @return void
	 */
	
	//------------showRowData() function----------------//
	@SuppressWarnings("rawtypes")
	public void showRowData(Map<String,String> hm){
		 set = hm.entrySet();
		Iterator i = set.iterator();
		//------------Display elements----------------//
		while(i.hasNext()) {
			Map.Entry me = (Map.Entry)i.next();
			System.out.print(me.getKey() );
		} 
	}
	
	
	/**
	 * This function shows the data of the excel specified by the List
	 * @param sheetData List of mapping 
	 * @return boolean
	 */
	//------------showExelData() function----------------//
	@SuppressWarnings("rawtypes")
	public boolean showExelData(List<Map> sheetData) {
		Map cell = null;
		for (int i = 0; i < sheetData.size(); i++) {
			cell = (Map) sheetData.get(i);
			System.out.print( cell.get("Username") + " " + cell.get("Password"));
		}
		//------------Return from function----------------//
		return(true);
	}

	
	/**
	 * This is Private function reads the value in cell of excel file
	 * @param cell The cell's value to be read
	 * @return String
	 */
	//------------getValue() private function----------------//
	private String getValue( Cell cell){
		String value = "";

		//------------Get the value of cell into the 'value' string variable----------------//
		switch (cell.getCellType()) {
		case NUMERIC:
			value = BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();
			break;

		case STRING:
			value = cell.getStringCellValue();
			break;

		case BLANK:
			value = "";
			break;

		case FORMULA:
			value = cell.getCellFormula();
			break;

		default:
			break;
		}
		//------------Return from function----------------//
		return value;
	}

	
	/**
	 * This function gives the exact row count in the excel file
	 * @param XLS_FILE_PATH Path of the suit file
	 * @param sSheetName Name of the sheet
	 * @return int
	 * @throws IOException
	 */
	//------------rowCount() function----------------//
	public int rowCount(String XLS_FILE_PATH , String sSheetName) throws IOException 
	{
		int noOfRows = 0;
		minputStream = new FileInputStream(XLS_FILE_PATH);
		mhssfwrkbokWorkbook = new HSSFWorkbook(minputStream);
		msheetSheet = mhssfwrkbokWorkbook.getSheet(sSheetName); 
		noOfRows = msheetSheet.getPhysicalNumberOfRows();
		//------------Return rowCount from function----------------//
		return noOfRows;
	} 	



	/**
	 * This function gives the Value from Map w. r. t Key 
	 * @param Set object
	 * @param KeyName of the value to be extraceted
	 * @return int
	 * @throws IOException
	 */
	//------------rowCount() function----------------//
	@SuppressWarnings("rawtypes")
	public String getValueByKey(Set TempSet,String KeyName) throws Exception 
	{	
		
		Iterator CountRow = TempSet.iterator();
	
		//------------Display elements----------------//
		while(CountRow.hasNext()) {
			Map.Entry me = (Map.Entry)CountRow.next();			
			//System.out.println(me.getKey() );
			//System.out.println(me.getValue() );
			if(me.getKey().toString().equalsIgnoreCase(KeyName)){				
				return me.getValue().toString();
			}
		}
		System.out.println();
		return null; 
	} 

	
//if(KeyName.equalsIgnoreCase("Enviorment")){
//	TestCaseReader.getConfig(me.getValue().toString(),KeyName);
//}




}
