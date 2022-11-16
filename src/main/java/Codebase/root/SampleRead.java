package Codebase.root;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SampleRead {

	public static void main(String[] args) throws Throwable {
		String strFilePath = "C:/Users/A631061/Documents/My Received Files/AutomationFramework/AutomationFramework/Codebase/Modules/AutomationPractice/TestData/TestData.xlsx";
		FileInputStream objFileStream = new FileInputStream(strFilePath);
		XSSFWorkbook objWorkBook = new XSSFWorkbook(objFileStream);
		XSSFSheet objSheet = objWorkBook.getSheet("Sheet1");
		System.out.println("Last Num : "+objSheet.getLastRowNum()+" First Num : "+objSheet.getFirstRowNum());
		int intRow = objSheet.getPhysicalNumberOfRows();
		System.out.println(intRow);
		
		
		Iterator<Row> iterator = objSheet.iterator();

        while (iterator.hasNext()) {

            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();

            while (cellIterator.hasNext()) {

                Cell currentCell = cellIterator.next();
                    System.out.print(currentCell.getStringCellValue() + "--");

            }
            System.out.println();

        }	
		
	}
		
		

}
