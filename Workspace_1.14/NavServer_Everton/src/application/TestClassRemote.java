package application;

import java.io.Serializable;

import com.kuka.roboticsAPI.controllerModel.sunrise.connectionLib.ISerializableType;



public class TestClassRemote implements Serializable {

	private String p1;
	private String p2;
	private String p3;
	
	public TestClassRemote(String a, String b, String c){
		p1=a;
		p2=b;
		p3=c;
	}
	

	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	public String getP3() {
		return p3;
	}

	public void setP3(String p3) {
		this.p3 = p3;
	}

}
