package DB;

public class BasketlistDTO {
int b_no;
String id;
int code;
String cname;
int cnt;
int price;
int check;
public int getB_no() {
	return b_no;
}
public void setB_no(int b_no) {
	this.b_no = b_no;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public int getCode() {
	return code;
}
public void setCode(int code) {
	this.code = code;
}
public String getCname() {
	return cname;
}
public void setCname(String cname) {
	this.cname = cname;
}
public int getCnt() {
	return cnt;
}
public void setCnt(int cnt) {
	this.cnt = cnt;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public int getCheck() {
	return check;
}
public void setCheck(int check) {
	this.check = check;
}
public String[] getArray() {
		String[] returnData = new String[7];
		returnData[0] = String.valueOf(this.b_no);
		returnData[1] = this.id;
		returnData[2] = String.valueOf(this.code);
		returnData[3] = this.cname;
		returnData[4] = String.valueOf(this.cnt);
		returnData[5] = String.valueOf(this.price);
		returnData[6] = String.valueOf(this.check);

		return returnData;
}


}
