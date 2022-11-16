package Codebase.FunctionLibrary;

public class InputDataLib {

	public String Key, Value;
	public boolean status;
	
	public InputDataLib(String strKey,String strValue,boolean st) {
		// TODO Auto-generated constructor stub
		Key=strKey;
		Value=strValue;	
		status=st;
	}
	
	public String  getValue(){
		return Value;
	}
	
	public String  getKey(){
		return Key;
	}
}
