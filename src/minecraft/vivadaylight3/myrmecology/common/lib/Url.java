package vivadaylight3.myrmecology.common.lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import vivadaylight3.myrmecology.common.Reference;

public class Url {
    
    private BufferedReader reader;
    
    public static final String VERSION_TYPE_1 = "Major";
    public static final String VERSION_TYPE_2 = "Minor";
    public static final String VERSION_TYPE_3 = "Build";

    public Url(String url){
	
	try {
	    reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
	} catch (MalformedURLException e) {
	} catch (IOException e) {
	}
	
    }

    public String getLatestVersion(){
	
	try {
	    return reader.readLine();
	} catch (IOException e) {
	}
	
	return null;
	
    }
    
    public String getUpdateType(){
	
	int[] dotPos = getDotPositions(getLatestVersion());
	
	String[] result = getVersionMajMinBd(getLatestVersion());
	
	if(Integer.parseInt(result[0]) >= Integer.parseInt(Reference.VERSION_MAJOR)){
	    
	    return VERSION_TYPE_1;
	    
	}else if(Integer.parseInt(result[1]) >= Integer.parseInt(Reference.VERSION_MINOR)){
	    
	    return VERSION_TYPE_2;
	    
	}else if(Integer.parseInt(result[2]) >= Integer.parseInt(Reference.VERSION_BUILD)){
	    
	    return VERSION_TYPE_3;
	    
	}
	
	return null;
	
    }
    
    public int[] getDotPositions(String version){
	
	int[] dotPos = new int[3];
		
	if(version != null){
	    
	    int index = 0;
	    
	    for(int k = 0; k < dotPos.length; k++){
		
		if(k == 0){
		    
		    index = 0;
		    
		}else{
		    
		    index = dotPos[k-1] + 1;
		    
		}
		
		dotPos[k] = version.indexOf(".", index);
		
	    }
	    
	}
	
	return dotPos;
	
    }
    
    private String[] getVersionMajMinBd(String version){
	
	int[] dotPos = getDotPositions(getLatestVersion());
	
	String[] result = new String[dotPos.length];
	
	int endIndex;
	
	for(int k = 0; k < result.length; k++){
	    
	    if(k == dotPos.length - 1){
		
		endIndex = getLatestVersion().length();
		
	    }else{
		
		endIndex = dotPos[k + 1];
		
	    }
	    
	    result[k] = getLatestVersion().substring(dotPos[k], endIndex);
	    
	}
	
	return result;
	
    }
    
    public boolean updateIsAvailable(){
	
	String type = getUpdateType();
	
	if(type == VERSION_TYPE_1 || type == VERSION_TYPE_2 || type == VERSION_TYPE_3){
	    
	    return true;
	    
	}else{
	
	    return false;
	
	}
		
    }

}
