package API_E2E;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class payloads {

	

	
	public static String token;
	public static String userID;
	public static String actualMess;
	
	public static loginPLSerialization loginPLS() {
		
		loginPLSerialization lo = new loginPLSerialization();
		lo.setUserEmail("mathum@gmail.com");
		lo.setUserPassword("Success@1");
		return lo;
	}
	 
	

	public static loginPLDeserialization loginPLDe() {
		
		loginPLDeserialization lod = new loginPLDeserialization();
		 token = lod.getToken();
		 userID = lod.getUserId();
		 actualMess = lod.getMessage();
		return lod;
		 
		
	}
	
	public static purchasePLSerialization purchasePLS() {
		productPLordersSerialization arPL = new productPLordersSerialization();
		arPL.setCountry("India");
		arPL.setProductOrderedId(eCommerceE2E.productID);
		
		List<productPLordersSerialization> purar = new ArrayList<productPLordersSerialization>();
		purar.add(arPL);
		
		
		purchasePLSerialization pur = new purchasePLSerialization();
		pur.setOrders(purar);
	
		
		return pur;
		
	}
	
	
}
