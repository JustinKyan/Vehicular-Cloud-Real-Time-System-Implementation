package main;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.*;

public class VCRTSRun extends Thread{
    public static void main(String[] args) throws UnknownHostException, IOException {
		new MainGUI("CarBoard");
	}
    
}