package Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	public static void main(String[] args) throws Exception {
		ServerSocket serverS = null;
		Socket withClient = null;
		serverS = new ServerSocket();
		serverS.bind(new InetSocketAddress("10.0.0.67",9999));
		
		ArrayList<Socket> cList = new ArrayList<>();
		while(true) {
			System.out.println("서 버대기중");
			withClient = serverS.accept();
			cList.add(withClient);
			System.out.println(cList);
			System.out.println(withClient.getInetAddress()+"클라이언트 접속 함");
		
		}

	}

}
