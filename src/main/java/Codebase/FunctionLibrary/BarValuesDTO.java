package Codebase.FunctionLibrary;

public class BarValuesDTO {
	private String mstrModule;
	private int mPass;
	private int mFail;
	private int mtotalCS;
	private String ModuleTC;
	private String mStatus;


	public String getmStatus() {
		return mStatus;
	}
	public void setmStatus(String mStatus) {
		this.mStatus = mStatus;
	}
	public String getModuleTC() {
		return ModuleTC;
	}
	public void setModuleTC(String moduleTC) {
		ModuleTC = moduleTC;
	}
	public String getMstrModule() {
		return mstrModule;
	}
	public void setMstrModule(String mstrModule) {
		this.mstrModule = mstrModule;
	}
	public int getmPass() {
		return mPass;
	}
	public void setmPass(int mPass) {
		this.mPass = mPass;
	}
	public int getmFail() {
		return mFail;
	}
	public void setmFail(int mFail) {
		this.mFail = mFail;
	}
	public int getMtotalCS() {
		return mtotalCS;
	}
	public void setMtotalCS(int mtotalCS) {
		this.mtotalCS = mtotalCS;
	}

}
