package Codebase.FunctionLibrary;//BrowserStack
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


public class HTML_Report {
	public WebDriver webdriver ;
	public static String mstrAppendString;
	public int mintSummaryCount=0;
	public int mintDetailCount=0,mintSubStepNo=0;
	public int mintPassed=0;
	public  int mintFailed=0;
	public static int mPass=0;
	public static int mFail=0; 
	public int mintWarnings=0;
	public  int mintStepNo=0;
	static FileWriter mObjFW0;
	public static int k=0;
	public static int mTestcase=0; 
	public static int mPassTC=0;
	public static int mFailTC=0;
	public static String mStatus;
	public static ArrayList<String> mlistTC_Seq=new ArrayList<String>();
	public static ArrayList<BarValuesDTO> arrList=new ArrayList<BarValuesDTO>();
	public static ArrayList<BarValuesDTO> formatedData=new ArrayList<BarValuesDTO>();
	public int ic=0;
	static DateFormat mObjDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	static DateFormat mObjDateFormatFile = new SimpleDateFormat("dd_MM_yyyy HH_mm_ss");
	Calendar mstrCalender_strt = Calendar.getInstance();
	String mstrStartTime=mObjDateFormat.format(mstrCalender_strt.getTime());
	static Date mDateToday=new Date();
	static String gstrAssignedTo="Shruti Mekhe";
	static String gstrCountryName="India";
	public static String strt_tym;
	public static String RunType;
	public String mstrTC_Name;
	public String mstrTC_Desc;
	public String mstrModuleName;
	int mintMasterStepNo=0;
	public String detail_strt_tym;
	public static Calendar cal_strt;
	public static Calendar cal_end;
	public static int mintstarttimecounter=0;
	public static int mPassed=0;
	public static int mFailed=0;
	public static int mStepNo=0;
	public String ImageComparedPath=""; 
	public String Respos;

	public HTML_Report(WebDriver webdriver) {
		this.webdriver=webdriver;
	}

	public HTML_Report() {
	}

	public void init_detail_TC_report(String Test_Case,String TC_Desc) throws Exception {

		int i,flag=0;

		for(i=0;i<mlistTC_Seq.size();i++)

		{
			if(mlistTC_Seq.get(i).equals(this.mstrTC_Name+this.mstrModuleName))
			{
				flag=1;
			}

		}
		if(flag==0)
			mlistTC_Seq.add(this.mstrTC_Name+this.mstrModuleName);

		Calendar cal_strt = Calendar.getInstance();
		detail_strt_tym=mObjDateFormat.format(cal_strt.getTime());
		CSS_StyleSheet.write_StyleSheet(Global.gstrResultPath+Global.gstrStart_time.replaceAll("\\W", "")+"\\"+this.mstrModuleName+"/"+this.mstrTC_Name+this.mstrModuleName+".html");
		FileWriter f1;

		f1=new FileWriter(Global.gstrResultPath+Global.gstrStart_time.replaceAll("\\W", "")+"\\"+this.mstrModuleName+"/"+this.mstrTC_Name+this.mstrModuleName+".html",true);

		String mstrstyleStrt1="<link rel=\"stylesheet\" type=\"text/css\" href=\"../styles/atos_indexstyle.css\">\n";	
		f1.append(mstrstyleStrt1);

		String mstrLeft_img="\n</head>\n<body>\n<div id=\"divContainer\">\n<table  class=\"formatHeader\">\n<tr><td colspan=\"4\" id=\"left1\">\n<figure><img src=\"../Images/SFDC_Dashboard.png\"></figure></td>\n";
		f1.append(mstrLeft_img);

		String mstrRight_img="\n<td colspan=\"4\" id=\"right\">\n<figure><img src=\"../Images/Logo_RGB_Small.jpg\"></figure></td></tr>";
		f1.append(mstrRight_img);

		String detail_hd_table="\n<script>var txt='<tr><td colspan=\"4\" id=\"center\"><h1>Test Case: "+Test_Case+"</h1></td></tr><tr> <td colspan=\"4\" id=\"center\"><h1>Test Description: "+TC_Desc+"</h1></td></tr></table>'; document.write(txt);</script>";
		f1.append(detail_hd_table);


		mstrAppendString="\n<script>var txt1='<table class=\"formatHTML5\"><thead><tr><td colspan=\"5\">As of:"+mDateToday+"</td><td align=\"left\"><a href=\"../Automation_summary.html\"><h2>Automation Summary<h2></a></td></tr>'; document.write(txt1);</script>";
		f1.append(mstrAppendString);


		mstrAppendString="<script>var txt='<br> <tr><th>Step#</th><th>Step Description</th><th>Expected Result</th><th>Actual Result</th><th>Test Status</th><th>Screen Shot</th></tr></thead><tbody>'; document.write(txt);</script>";
		f1.append(mstrAppendString);

		f1.close();
	}	
	public String createTimeStampStr() throws Exception {
		Calendar mycalendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_hhmmss");
		String timeStamp = formatter.format(mycalendar.getTime());

		return timeStamp;

	}
	public void details_append(String step_desc,String Exp_Res,String Act_Res,String Status) throws Exception
	{
		///*************************************************
		//Captures screenshot for calling step 
		//*************************************************
		Thread.sleep(2000);
		////////WebDriver augmentedDriver = new Augmenter().augment(webdriver);
		////////File scrFile = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy somewhere

		////////String imgPath = "ScreeenShots/"+this.mstrModuleName+"/"+this.mstrTC_Name+"/"+ createTimeStampStr() + ".PNG";
		////////String screenShotPath=Global.gstrResultPath+imgPath;

		////////FileUtils.copyFile(scrFile, new File(screenShotPath));
		////////scrFile.delete();
		///***************************************************
		//=======================
		String imgPath = "ScreeenShots/"+this.mstrModuleName+"/"+this.mstrTC_Name+"_"+ createTimeStampStr() + ".jpg";
		String screenShotPath=Global.gstrResultPath+Global.gstrStart_time.replaceAll("\\W", "")+"\\"+imgPath;
		File srcFile;
		srcFile=((RemoteWebDriver) webdriver).getScreenshotAs(OutputType.FILE);
		if(Status.equalsIgnoreCase("Pass")||Status.equalsIgnoreCase("Fail")||Status.equals("")){
			File targetFile=new File(screenShotPath);
			FileUtils.copyFile(srcFile,targetFile);
		}else{	
			if(TestCaseReader.getConfig("CompareImage").equalsIgnoreCase("YES")){
				try{
					Status=Status.replaceAll(" ", "");
					ImageTesting objImageTesting=new ImageTesting(this.mstrModuleName,this.Respos);
					ImageComparedPath=objImageTesting.CompareImages(srcFile.getAbsolutePath(),Status);
					Status=ImageComparedPath.split(",")[0];
					String path=ImageComparedPath.split(",")[1];
					String SourceFilePath=ImageComparedPath.split(",")[2];
					SourceFilePath= (new File(SourceFilePath)).getCanonicalPath();
					SourceFilePath=SourceFilePath.replaceAll("\\\\", "//");
					Act_Res=Act_Res+ " <a href=file:///" + SourceFilePath+">Click to View Source Image</a>";
					if (Status.equalsIgnoreCase("Pass")){
						File targetFile=new File(screenShotPath);
						FileUtils.copyFile(srcFile,targetFile);
						Exp_Res=Exp_Res+" Images Compared Successfully No Difference Found!";

						Status="Pass";
					}else if(Status.equalsIgnoreCase("Error"))
					{
						File targetFile=new File(screenShotPath);
						FileUtils.copyFile(srcFile,targetFile);				
						Exp_Res=Exp_Res+" Images could Not Be compared  Please verify Manual from Screen Shot";

						Status="Fail";
					}
					else
					{
						File targetFile=new File(screenShotPath);
						FileUtils.copyFile(new File(path),targetFile);
						Exp_Res=Exp_Res+" Images Compared Successfully Difference Found";

						Status="Fail";
					}		
				}catch(Exception e){
					e.printStackTrace();
					System.out.println();
				}
			}else{
				Status=Status.replaceAll(" ", "");
				imgPath = "ScreeenShots/"+this.mstrModuleName+"/"+"Source"+"/"+ Status + ".jpg";
			/*	Date date =new Date();
				screenShotPath=Global.gstrResultPath+"Source/"+this.mstrModuleName+"/"+Respos+"_"+mObjDateFormatFile.format(date)+"/"+ Status + ".jpg";//+imgPath;*/
				screenShotPath=Global.gstrResultPath+"Source/"+this.mstrModuleName+"/"+Respos+"/"+ Status + ".jpg";//+imgPath;
				File targetFile=new File(screenShotPath);
				FileUtils.copyFile(srcFile,targetFile);
			}
			
		}

		//System.out.println();
		//==================================================
		FileWriter f1;
		mintDetailCount++; //Counts the call to this function frmo individual test case

		f1=new FileWriter(Global.gstrResultPath+Global.gstrStart_time.replaceAll("\\W", "")+"\\"+this.mstrModuleName+"/"+this.mstrTC_Name+this.mstrModuleName+".html",true);

		if(mintDetailCount==1){

			init_detail_TC_report(this.mstrTC_Name,mstrTC_Desc);
		}

		if(Act_Res.equals("") && Status.equals(""))
		{
			mintStepNo++;
			mintMasterStepNo=0;
			mintSubStepNo=0;
			mstrAppendString="<script>var txt='<tbody><tr><td>"+mintStepNo+"</td><td><b>"+step_desc+"</td><td>"+Exp_Res+"</td><td></td><td width=8%><b><font size = 2 color = green></td><td></td></tr>'; document.write(txt);</script>";

			f1.append("\n"+mstrAppendString);
		}

		else
		{
			mintSubStepNo++;

			if(Status.equalsIgnoreCase("PASS"))
			{
				mstrAppendString="<script>var txt='<tr><td>"+mintStepNo+"."+mintSubStepNo+"</td><td>"+step_desc+"</td><td>"+Exp_Res+"</td><td>"+Act_Res+"</td><td><b><font size = 2 color = green>"+Status+"</td><td><a target=_blank class=anibutton href=\"../"+imgPath+"\"><img class=screen src=\"../Images/pass.png\"></a></td></tr>'; document.write(txt);</script>";
			}
			else if(Status.equalsIgnoreCase("FAIL"))
			{
				if(mintMasterStepNo==0){
					mintFailed++;
					mintMasterStepNo++;
				}
				mstrAppendString="<script>var txt='<tr><td width=70px class=tsindlevel2>"+mintStepNo+"."+mintSubStepNo+"</td><td width=300px class=tsgen>"+step_desc+"</td><td width=70px class=tsgen>"+Exp_Res+"</td><td width=70px class=tsgen>"+Act_Res+"</td><td width=70px align=center class=tsgen><b><font size = 2 color = red>"+Status+"</td><td class=tsind width=50px><a target=_blank class=anibutton href=\"../"+imgPath+"\"><img class=screen src=\"../Images/fail.png\"></a></td></tr>'; document.write(txt);</script>";

			}
			else if(Status.equalsIgnoreCase("Skipped"))
			{
				if(mintMasterStepNo==0){
					mintFailed++;
					mintMasterStepNo++;
				}
				mstrAppendString="<script>var txt='<tr><td></td><td width=600px><b><font size = 2 color = Brown>"+step_desc+"</td><td></td><td></td><td><b><font size = 2 color = Brown>"+Status+"</td><td align=center width=50px><a target=_blank class=anibutton href=\"file:///"+screenShotPath+"\"><img class=screen src=\"file:///"+Global.gstrResultPath+"Images/img.png\"></a></td></tr>'; document.write(txt);</script>";
			}
			f1.append("\n"+mstrAppendString);

		}

		f1.close();
		if(mintFailed==0)
		{
			mintPassed=mintStepNo;
			this.Summary(this.mstrTC_Name,mstrModuleName, mstrTC_Desc, String.valueOf(mintStepNo), String.valueOf(mintPassed), String.valueOf(mintFailed), String.valueOf(mintWarnings), "PASS");
		}
		else
		{
			mintPassed=mintStepNo-mintFailed;
			this.Summary(this.mstrTC_Name,mstrModuleName, mstrTC_Desc, String.valueOf(mintStepNo), String.valueOf(mintPassed), String.valueOf(mintFailed), String.valueOf(mintWarnings), "FAIL");
		}

	}
	public void Summary(String Test_Case,String Module,String Description,String Steps,String Passed,String Failed,String Warnings,String Status) throws Exception{

		//*********************************************************************************************
		String str=Global.gstrResultPath+Global.gstrStart_time.replaceAll("\\W", "")+"\\"+"Dump/"+Test_Case+Module+".txt";

		try{
			mObjFW0=new FileWriter(str);
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		//**********************************************************************************************
		if(Status.equalsIgnoreCase("PASS")){
			String summary="\n<script>var txt='<tr><td><a href=\"./"+this.mstrModuleName+"/"+this.mstrTC_Name+this.mstrModuleName+".html\">"+Test_Case+"</a></td><td>"+Module+"</td><td>"+Description+"</td><td>"+Steps+"</td><td>"+Passed+"</td><td>"+Failed+"</td><td>"+Warnings+"</td><td><b><font size = 2 color = green>"+Status+"</td></tr> '; document.write(txt);</script>";
			mObjFW0.write(summary);
		}
		else if(Status.equalsIgnoreCase("FAIL")){
			String summary="\n<script>var txt='<tr><td><a href=\"./"+this.mstrModuleName+"/"+this.mstrTC_Name+this.mstrModuleName+".html\">"+Test_Case+"</a></td><td>"+Module+"</td><td>"+Description+"</td><td>"+Steps+"</td><td>"+Passed+"</td><td>"+Failed+"</td><td>"+Warnings+"</td><td><b><font size = 2 color = red>"+Status+"</td></tr> '; document.write(txt);</script>";
			mObjFW0.write(summary);
		}

		mObjFW0.close();
		/**format data*/

		int m,flag=0;

		for(m=0;m<formatedData.size();m++)

		{
			if(formatedData.get(m).getModuleTC().equals(this.mstrTC_Name+this.mstrModuleName))
			{
				flag=1;
				formatedData.get(m).setModuleTC(this.mstrTC_Name+this.mstrModuleName);
				formatedData.get(m).setMstrModule(this.mstrModuleName);
				formatedData.get(m).setMtotalCS(Integer.valueOf(Steps));
				formatedData.get(m).setmPass(Integer.valueOf(Passed));
				formatedData.get(m).setmFail(Integer.valueOf(Failed));
				formatedData.get(m).setmStatus(Status);
			}

		}
		if(flag==0){
			BarValuesDTO dto=new BarValuesDTO();
			dto.setModuleTC(this.mstrTC_Name+this.mstrModuleName);
			dto.setMtotalCS(Integer.valueOf(Steps));
			dto.setmPass(Integer.valueOf(Passed));
			dto.setmFail(Integer.valueOf(Failed));
			dto.setMstrModule(this.mstrModuleName);
			dto.setmStatus(Status);
			formatedData.add(dto);

		}	
	}//end of summary


	public void end_Report() throws Exception
	{
		mObjFW0=new FileWriter(Global.gstrResultPath+Global.gstrStart_time.replaceAll("\\W", "")+"\\"+this.mstrModuleName+"/"+this.mstrTC_Name+this.mstrModuleName+".html",true);
		Calendar cal_end = Calendar.getInstance();
		String end_tym=mObjDateFormat.format(cal_end.getTime());
		Date date1 = mObjDateFormat.parse(detail_strt_tym);
		Date date2 = mObjDateFormat.parse(end_tym);
		long difference = date2.getTime() - date1.getTime();
		long var1=difference%1000;
		difference=(difference/1000);
		String Duration1=difference+"."+var1;
		String summary1="\n<script>var txt='</table><br><br><table class=\"formatHTML4\" width=900px align = center><tr><td width=650px></td><td colspan=2 class=tsheader>Execution Time</td></tr><tr><td width=650px></td><td class=pfhead width=120px>Start Time</td><td class=pfind width=130px>"+detail_strt_tym+"</td></tr><tr><td width=650px></td><td class=pfhead width=120px>End Time</td><td class=pfind width=130px>"+end_tym+"</td></tr><tr><td width=650px></td><td class=pfhead width=120px>Duration</td><td class=pfind width=130px>"+Duration1+" secs</td></tr></table>'; document.write(txt);</script>";
		mObjFW0.append(summary1);
		String summary3="\n<script>var txt='<tfoot><tr><td colspan=\"8\">For internal use. &copy; Atos S.E. 2017 all rights reserved.</td></tr></tfoot> </table></div></body></html>'; document.write(txt);</script>";
		mObjFW0.append(summary3);
		mObjFW0.close();
		HTML_Report.build_Automation_Summary();

	}


	public static void build_Automation_Summary() throws Exception
	{
		//*******************************************
		// Initially writes a schema for automation summary report
		//*******************************************

		CSS_StyleSheet.write_StyleSheet(Global.gstrResultPath+Global.gstrStart_time.replaceAll("\\W", "")+"\\"+"Automation_summary.html");

		mObjFW0=new FileWriter(Global.gstrResultPath+Global.gstrStart_time.replaceAll("\\W", "")+"\\"+"Automation_summary.html",true);

		String strTemp="[['Modules', 'Total TC', 'Pass TC','Fail TC'],['";
		for(int m=0;m<formatedData.size();m++)
		{
		}
		for(;k<formatedData.size();k++){

			if(formatedData.get(k).getmStatus().equalsIgnoreCase("Pass"))
			{
				mPassTC++;
			}else{
				mFailTC++;
			}	

			boolean flag=false;
			BarValuesDTO dto = new BarValuesDTO();

			// Code for Bar Chart to calculate Values
			for(int j=0; j<arrList.size(); j++){
				if(arrList.get(j).getMstrModule().equalsIgnoreCase(formatedData.get(k).getMstrModule())){
					flag=true;
					if(formatedData.get(k).getmStatus().equalsIgnoreCase("pass")){
						arrList.get(j).setmPass((arrList.get(j).getmPass()+1));
					}else{
						arrList.get(j).setmFail((arrList.get(j).getmFail()+1));
					}
					break;
				}
			}

			if(arrList.size()==0 || !flag){
				if(formatedData.get(k).getmStatus().equalsIgnoreCase("pass")){
					dto.setMstrModule(formatedData.get(k).getMstrModule());
					dto.setmPass(1);
					dto.setmFail(0);
				}else{
					dto.setMstrModule(formatedData.get(k).getMstrModule());
					dto.setmPass(0);
					dto.setmFail(1);
				}
				arrList.add(dto);
			}

		}
		for(int i=0;i<arrList.size();i++){
			int TotalTC=arrList.get(i).getmPass()+arrList.get(i).getmFail();
			strTemp=strTemp+arrList.get(i).getMstrModule()+"',"+TotalTC+","+arrList.get(i).getmPass()+","+arrList.get(i).getmFail()+"]";
			if(i<(arrList.size()-1)){
				strTemp=strTemp+",['";
			}	
		}
		strTemp=strTemp+"]";
		String PieChartStr0="\n<script type=\"text/javascript\">google.load(\"visualization\", \"1\", {packages:[\"corechart\"]});google.setOnLoadCallback(drawChart);function drawChart() {var data = google.visualization.arrayToDataTable([['Status','Scripts'],['Pass',"+mPassTC+"],['Fail',"+mFailTC+"],['Warning',0]]);var options = {title:'Overall Automation Execution Status %',slices: {0: {color: 'green'}, 1:{color: 'red'}}};var chart = new google.visualization.PieChart(document.getElementById('chart_div'));chart.draw(data, options);";	
		mObjFW0.append(PieChartStr0);
		String PieChartStr1="google.visualization.events.addListener(chart, 'select', FetchSpecificRows);function FetchSpecificRows(){var selection = chart.getSelection();var item = selection[0];var test = document.getElementsByTagName(\"INPUT\")[0];";
		mObjFW0.append(PieChartStr1);
		String PieChartStr2="test.setAttribute(\"name\", \"filt\");if(item.row==0){test.value =\"Pass\";}if(item.row==1){test.value = \"Fail\";}if(item.row==2){test.value =\"Warning\";}";
		mObjFW0.append(PieChartStr2);
		String PieChartStr3="chart.setSelection([]);test.focus();var keyupevent=document.createEvent(\"MouseEvents\");keyupevent.initEvent(\"keyup\", true, true);test.dispatchEvent(keyupevent);}}$(document).ready( function () { $('#table1').dataTable();} );</script>";
		mObjFW0.append(PieChartStr3);
		String BarchartStr="<script type=\"text/javascript\">google.load(\"visualization\", \"1\", {packages:[\"corechart\"]});google.setOnLoadCallback(drawChart);function drawChart() {var data = google.visualization.arrayToDataTable("+strTemp+");var options = {title: 'Performance',series: [{color: '#3366CC', visibleInLegend: true}, {color: 'green', visibleInLegend:true}, {color: 'red', visibleInLegend:true}]};var chart = new google.visualization.ColumnChart(document.getElementById('barchart_div'));chart.draw(data, options);};document.write(txt);</script>";
		mObjFW0.append(BarchartStr);
		String mstrLeft_img="\n</head>\n<body>\n<div id=\"divContainer\">\n<table  class=\"formatHeader\">\n<tr><td colspan=\"4\" id=\"left1\">\n<figure><img src=\"./Images/SFDC_Dashboard.png\"></figure></td>\n";
		mObjFW0.append(mstrLeft_img);
		String mstrRight_img="\n<td colspan=\"4\" id=\"right\">\n<figure><img src=\"./Images/Logo_RGB_Small.jpg\"></figure></td></tr>";
		mObjFW0.append(mstrRight_img);
		mstrAppendString="\n<script>var txt='<tr><td colspan=\"4\" id=\"center\"><h1>Test Automation Summary Report</h1></td></tr><tr><td colspan=\"4\" id=\"center2\">As on: "+mDateToday+"</td></tr></table>'; document.write(txt);</script>";
		mObjFW0.append(mstrAppendString);
		String summary="\n<script>var txt='<br><table><tr><td colspan=\"4\" id=\"left1\"><div id=\"chart_div\" style=\"width: 500px; height: 250px ; position:center \"></div></td><br><td colspan=\"4\" id=\"right\"><div id=\"barchart_div\" style=\"width: 500px; height: 250px ; position:center \"></div></td></tr></table><br><table id=\"table1\" class=\"formatHTML5\"><thead><tr><th>Test_Case</th></th><th>Module</th><th>Description</th><th>Steps</th><th>Passed</th><th>Failed</th><th>Warnings</th><th>Status</th></tr></thead><tbody> '; document.write(txt);</script>";
		mObjFW0.append(summary);


		//*************************************************************************************************//

		BufferedReader br;
		for(int i=0;i<mlistTC_Seq.size();i++)
		{
			String str=Global.gstrResultPath+Global.gstrStart_time.replaceAll("\\W", "")+"\\"+"Dump/"+mlistTC_Seq.get(i)+".txt";

			File f=new File(str);
			FileReader fr=new FileReader(f);
			br = new BufferedReader(fr);


			String line = br.readLine();

			while (line != null) {
				mObjFW0.append(line);
				mObjFW0.append("\n");
				line = br.readLine();

			}

			br.close();
			fr.close();

			f.deleteOnExit();
		}


		Calendar cal_end1 = Calendar.getInstance();
		String end_tym1=mObjDateFormat.format(cal_end1.getTime());
		Date date3 = mObjDateFormat.parse(Global.gstrStart_time);
		Date date4 = mObjDateFormat.parse(end_tym1);
		long difference1 = date4.getTime() - date3.getTime();
		long var=difference1%1000;
		difference1=(difference1/1000);
		String Duration=difference1+"."+var;

		String summary2="\n<script>var txt='</table><br><br><table class=\"formatHTML4\" width=900px align=center><tr><td width=650px></td><td colspan=2 class=tsheader>Execution Time</td></tr><tr><td width=650px></td><td class=pfhead width=120px>Start Time</td><td class=pfind width=130px>"+Global.gstrStart_time+"</td></tr><tr><td width=650px></td><td class=pfhead width=120px>End Time</td><td class=pfind width=130px>"+end_tym1+"</td></tr><tr><td width=650px></td><td class=pfhead width=120px>Duration</td><td class=pfind width=130px>"+Duration+" secs</td></tr></table>'; document.write(txt);</script>";
		mObjFW0.append(summary2);
		String summary3="\n<script>var txt='<tfoot><tr><td colspan=\"8\">For internal use. &copy; Atos S.E. 2017 all rights reserved.</td></tr></tfoot> </table></div></body></html>'; document.write(txt);</script>";
		mObjFW0.append(summary3);

		mObjFW0.close();

	}
}
