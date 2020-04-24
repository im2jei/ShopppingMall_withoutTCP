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
		String m = null;
		String[] returnData = new String[5];
		returnData[0] = String.valueOf(this.code);
		returnData[1] = this.cname;
		returnData[2] = String.valueOf(this.cnt);
		returnData[3] = String.valueOf(this.price);
		if (this.check == 1) {
			m = " ";
		} else if (this.check == 2) {
			m = "üũ";
		}
		returnData[4] = m;

		return returnData;
	}

}
