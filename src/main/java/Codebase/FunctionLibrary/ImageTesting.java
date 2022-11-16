package Codebase.FunctionLibrary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageTesting {
	
	public String moduleName,Respo;
	Global objGlobal=new Global();
	//private static String SourceReportPath= "..//AutomationFramework2//Codebase//Result//Source//ScreeenShots//GMS//";
	private  String SourceReportPath = Global.gstrResultPath+"SourceIE2/" + objGlobal.gstrModuleName + "//";
	//private static String SourceReportPath= "..//AutomationFramework2//Codebase//Result//SourceIE2//ScreeenShots//GMS//";
	private static String TargetReportPath;

	public ImageTesting(String str,String res){
		this.moduleName=str;
		this.Respo=res;
		SourceReportPath = Global.gstrResultPath+"Source/"+moduleName+"/"+Respo+"/";
		
		//SourceReportPath = Global.gstrResultPath+"SourceIE2/ScreeenShots/" + moduleName + "/"+Respo+"/";
		TargetReportPath= Global.gstrResultPath+Global.gstrStart_time.replaceAll("\\W", "")+"\\ScreeenShots\\"+moduleName+"\\";
		
	}
	
	public static ImageComparison compare = new ImageComparison();
	

	public  String Compare(String SourceImagePath,String strTargetFileName) throws IOException {
		System.out.println("Source path"+SourceImagePath);
		System.out.println("targetPath"+SourceReportPath+strTargetFileName+".jpg");		
		
		String strSourcePAth=SourceReportPath+strTargetFileName+".jpg";
		String Result= compare.compareImage(strSourcePAth,SourceImagePath, "../AutomationFramework2/Codebase/Result/ImageCompareResult");
		
		return Result+","+strSourcePAth;
		

	}

	public  String CompareImages(String SourceReportPath,String TargetReportPath) throws IOException {
		ImageTesting objImageTesting=new ImageTesting(moduleName,Respo);
		String res=objImageTesting.Compare(SourceReportPath,TargetReportPath);
		if(TargetReportPath.equalsIgnoreCase("Login") && res.split(",")[0].equalsIgnoreCase("Fail")){
			TargetReportPath="Login2";
			System.out.println("Image returned");
			res=objImageTesting.Compare(SourceReportPath,TargetReportPath);
			
		}
		return res;
		
	}
}
