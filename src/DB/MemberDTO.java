package DB;

import java.util.ArrayList;

public class MemberDTO {
	ArrayList<MemberDTO> mlist= new ArrayList<>();
	
	String id;
	String name;
	String pwd;
	String adr;
	String cell;
	int lv;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getAdr() {
		return adr;
	}
	public void setAdr(String adr) {
		this.adr = adr;
	}
	public String getCell() {
		return cell;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	
	public ArrayList<MemberDTO> getArray() {
		for(int i=0; i<mlist.size();i++) {
			mlist.get(i);
		}
		return mlist;
	}
	
	
}
