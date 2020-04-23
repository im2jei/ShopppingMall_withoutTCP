package member;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DB.MemberDAO;
import DB.MemberDTO;
import manager.Setting;
import manager.ShoppingMall;

public class Login extends JFrame implements ActionListener {
	JPanel nP, cP, sP, eP;
	JLabel idLabel, pwLabel, joinlabel;
	JTextField idField, pwdField, loginField;
	JButton loginBtn, joinBtn;

	Login() {
		super("Login");
		createpanel();
		setClose();
	}

	private void createpanel() {
		this.setLayout(new BorderLayout());
		nP = new JPanel();
		nP.setBorder(new EmptyBorder(10, 10, 0, 10));
		idLabel = new JLabel("ID");
		nP.add(idLabel);

		// 텍스트 필드 만들기
		idField = new JTextField(15);
		nP.add(idField);

		// center 패널 만들기
		cP = new JPanel();
		pwLabel = new JLabel("암 호");
		pwdField = new JPasswordField(15);

		cP.add(pwLabel);
		cP.add(pwdField);
		// south 패널 만들기
		sP = new JPanel();
		loginBtn = new JButton("로그인");
		sP.add(loginBtn);

		eP = new JPanel();
		joinBtn = new JButton("회원가입");
		sP.add(joinBtn);
		loginBtn.addActionListener(this);
		joinBtn.addActionListener(this);

		// 패널 프레임에 넣기 위치도 지정"".
		this.add(nP, "North");
		this.add(cP, "Center");
		this.add(sP, "South");
		this.add(eP, "East");

		// 색 지정
		// this.setBackground(Color,blue);

		// 사이즈 정해주기
		this.setBounds(400, 500, 300, 200);

	}

	public void setClose() {
		// this.setVisible(false);
		// 자동으로 꺼지게만듬
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		// true면 화면에서 나타나고 false면 화면에서 사라져라
		this.setVisible(true);
	}

	private void loginchk() {
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					MemberDAO dao = MemberDAO.getInstance();
					MemberDTO member = dao.loginchk(idField.getText());
					if (member != null) {
						if (idField.getText().equals(member.getId()) && pwdField.getText().equals(member.getPwd())) {
							JOptionPane.showMessageDialog(null, "로그인 완료");
							if (member.getLv() == 1) {
								System.out.println("쇼핑몰창 뜨게하기");
								new ShoppingMall(member.getId());
								dispose();
							} else if (member.getLv() == 5) {
								System.out.println("관리자창 뜨게 하기 관리자 객체를 관리자의 창으로 보내깅");
								new Setting();
								dispose();
							}
						} else {
							JOptionPane.showMessageDialog(null, "존재하지 않는 아이디거나 비밀번호가 맞지 않습니다.");
							setDefaultCloseOperation(DISPOSE_ON_CLOSE);
							idField.setText("");
							pwdField.setText("");
						}
					} else if (member == null) {
						JOptionPane.showMessageDialog(null, "존재하지 않는 아이디거나 비밀번호가 맞지 않습니다.");
						setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						idField.setText("");
						pwdField.setText("");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		loginchk();
		gosignup();
	}

	private void gosignup() {
		joinBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SignUp();
			}

		});
	}

}
