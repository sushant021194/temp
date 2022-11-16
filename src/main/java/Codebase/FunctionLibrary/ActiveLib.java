package Codebase.FunctionLibrary;

public class ActiveLib {

	public String Key, Value;
	public boolean status;
	
	public ActiveLib(String strKey,String strValue) {
		// TODO Auto-generated constructor stub
		Key=strKey;
		Value=strValue;	
		status=false;
	}
	
	public String  getValue(){
		return Value;
	}
	
	public String  getKey(){
		return Key;
	}
}
