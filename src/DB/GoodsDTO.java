package DB;

public class GoodsDTO {
	int code;
	String cname;
	int cnt;
	int price;
	int check;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String name) {
		this.cname = name;
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
		String m = null;
		String[] returnData = new String[5];
		returnData[0] = String.valueOf(this.code);
		returnData[1] = this.cname;
		returnData[2] = String.valueOf(this.cnt);
		returnData[3] = String.valueOf(this.price);
		if (this.check == 0) {
			m = "false";
		} else if (this.check == 1) {
			m = "true";
		}
		returnData[4] = m;

		return returnData;
	}

}
