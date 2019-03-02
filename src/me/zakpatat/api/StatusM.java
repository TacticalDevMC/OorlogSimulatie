package me.zakpatat.api;

import java.util.HashMap;

public class StatusM {
	
	private String instance;
    private static StatusM statusM;
	private HashMap<String, String> st = new HashMap<String, String>();
	
    private StatusM() {

    }
    


    public static StatusM getManager() {
        if (statusM == null)
            statusM = new StatusM();

        return statusM; 
    }

    public String getStatus(String id) {
    	st.get(id);
    	return instance;
    }
	public void setWaiting(String id) {
		st.remove(id);
		st.put(id, "Waiting");
		
	}
	public void setStarting(String id) {
		st.remove(id);
		st.put(id, "Starting");
		
	}
	public void setPlaying(String id) {
		st.remove(id);
		st.put(id, "Playing");
		
	}
	public void setEnding(String id) {
		st.remove(id);
		st.put(id, "Ending");
		
	}
	public void setRestarting(String id) {
		st.remove(id);
		st.put(id, "Restarting");
		
	}
	

}
