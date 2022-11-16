package Codebase.root;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

class LocalDriverFactory {
	public static Process pr;

	public static void RunHubFile(String browserName, String System, String URL) {
		String mstrlocal = "127.0.0.1";
		if (System.equalsIgnoreCase(mstrlocal)) {
			Runtime rt = Runtime.getRuntime();
			try {
				// System.out.println();
				File fs = new File("");
				String a = fs.getCanonicalPath();
				String str = a + "\\src\\main\\java\\LABSETUP\\Start_Standalone_File_" + browserName + ".bat";
				// String str="C:\\LABSETUP\\Start_Standalone_File_"+browserName+".bat";

				pr = rt.exec(str);
			} catch (IOException e) {
				// TODO Au;to-generated catch block
				e.printStackTrace();
			}
		} else {

			Runtime rt = Runtime.getRuntime();
			try {
				// System.out.println();
				String str = "\\\\" + System + "\\LABSETUP\\Start_Standalone_File_" + browserName + ".bat";
				pr = rt.exec(str);
			} catch (IOException e) {
				// TODO Au;to-generated catch block
				// e.printStackTrace();
			}

		}
	}

	static RemoteWebDriver createInstance(String browserName, String ExecutionSystem, String URL)
			throws MalformedURLException {
		RemoteWebDriver driver = null;
		try {
			RunHubFile(browserName, ExecutionSystem, URL);

			String msrtMachineAddress = ExecutionSystem;

			// System.out.println();
			String url = "http://" + msrtMachineAddress + ":5547/wd/hub";
			// System.out.println();
			// System.out.println();
			if (browserName.toLowerCase().contains("firefox")) {
				url = "http://" + msrtMachineAddress + ":4445/wd/hub";
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				capabilities.setJavascriptEnabled(true);
				// driver=new RemoteWebDriver(new URL(url), capabilities);
				driver = new FirefoxDriver(capabilities);
				driver.get(URL);
				driver.manage().window().maximize();
				return driver;
			}

			/*
			 * if (browserName.toLowerCase().contains("ie")) {
			 * url="http://"+msrtMachineAddress+":5547/wd/hub";
			 * //System.setProperty("webdriver.ie.driver",
			 * ".//LABSETUP/browserDriver/IEDriverServer.exe"); System.setProperty(
			 * "webdriver.ie.driver","C:\\Users\\a885555\\OneDrive - Atos\\Acerta\\Framework\\src\\main\\java\\LABSETUP\\browserDriver\\IEDriverServer.exe"
			 * ); DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			 * capabilities.setCapability(InternetExplorerDriverService.
			 * IE_DRIVER_EXTRACT_PATH_PROPERTY,
			 * "../AutomationFramework/LABSETUP/IEDriverTemp");
			 * capabilities.setCapability(InternetExplorerDriver.
			 * INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			 * capabilities.setCapability(InternetExplorerDriver.
			 * ENABLE_ELEMENT_CACHE_CLEANUP,true);
			 * capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,
			 * true);
			 * capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,URL);
			 * capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			 * capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS,true);
			 * //driver=new RemoteWebDriver(new URL(url), capabilities); driver=new
			 * InternetExplorerDriver(capabilities); driver.get(URL);
			 * driver.manage().window().maximize(); return driver; }
			 */
			if (browserName.toLowerCase().contains("ie")) {
				url = "http://" + msrtMachineAddress + ":4445/wd/hub";
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setJavascriptEnabled(true);
				File fl = new File("");
				String stCurrentPath = fl.getCanonicalPath();
				System.setProperty("webdriver.ie.driver",
						stCurrentPath + "/src/main/java/LABSETUP/browserDriver///IEDriverServer.exe");
//			System.setProperty("webdriver.chrome.driver", "/DAF_M2M/src/main/resources/LABSETUP/browserDriver/chromedriver.exe");
				// driver=new RemoteWebDriver(new URL(url), capabilities);
				capabilities.setCapability("ignoreZoomSetting", true);
				driver = new InternetExplorerDriver(capabilities);
				driver.get(URL);
				driver.manage().window().maximize();
				// pr.destroy();
				return driver;
			}
			/*
			 * if (browserName.toLowerCase().contains("ie")) {
			 * url="http://"+msrtMachineAddress+":5547/wd/hub"; DesiredCapabilities
			 * capabilities = DesiredCapabilities.internetExplorer();
			 * capabilities.setCapability(InternetExplorerDriver.
			 * INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			 * capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, URL);
			 * capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS,
			 * true); capabilities.setCapability(InternetExplorerDriver.
			 * ENABLE_ELEMENT_CACHE_CLEANUP,true); System.setProperty("webdriver.ie.driver",
			 * ".//LABSETUP/browserDriver//IEDriverServer.exe"); File ie_temp = new
			 * File(".//AutomationFramework//LABSETUP//IEDriverTemp");
			 * InternetExplorerDriverService.Builder ies = new
			 * InternetExplorerDriverService.Builder(); //ies.withExtractPath(ie_temp);
			 * capabilities.setCapability(InternetExplorerDriver.EXTRACT_PATH,ie_temp);
			 * InternetExplorerDriverService service=ies.build(); //driver=new
			 * RemoteWebDriver(new URL(url), capabilities); driver=new
			 * InternetExplorerDriver(service,capabilities); driver.get(URL); return driver;
			 * }
			 */
			if (browserName.toLowerCase().contains("chrome")) {
				url = "http://" + msrtMachineAddress + ":4445/wd/hub";
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setJavascriptEnabled(true);
				File fl = new File("");
				String stCurrentPath = fl.getCanonicalPath();
				System.setProperty("webdriver.chrome.driver",
						stCurrentPath + "/src/main/java/LABSETUP/browserDriver/chromedriver.exe");
//			System.setProperty("webdriver.chrome.driver", "/DAF_M2M/src/main/resources/LABSETUP/browserDriver/chromedriver.exe");
				// driver=new RemoteWebDriver(new URL(url), capabilities);
				driver = new ChromeDriver(capabilities);
				driver.get(URL);
				driver.manage().window().maximize();
				// pr.destroy();
				return driver;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}

		return driver;
	}
}
