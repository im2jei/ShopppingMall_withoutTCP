package member;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import DB.BasketlistDTO;
import DB.BasketlistDAO;

public class Basketlist extends JFrame {
	Scanner in = new Scanner(System.in);
	String header[] = { "상품코드", "상품이름", "수량", "가격", "체크" };
	JTabbedPane tabpane = new JTabbedPane();
	DefaultTableModel tablemodel = new DefaultTableModel(header, 0);
	JTable table = new JTable(tablemodel);
	JScrollPane tableScroll = new JScrollPane(table);

	// center panel
	JPanel tab_center = new JPanel();
	JPanel tab_south = new JPanel();
	JPanel south_north = new JPanel();

	JTextField[] txtfield = new JTextField[5];
	JTextField tfield = null;

	int modIntRow = -1;

	String fileName = "data.txt";

	BasketlistDAO dao = BasketlistDAO.getInstance();
	BasketlistDTO dto = null;
	ArrayList<String[]> initList = new ArrayList<>();

	public Basketlist() {
		super("장바구니");// super의 생성자 호출
		Dimension size = new Dimension(600, 400);
		createpanel();
		createtb();
		tablesetting();

		init();

		this.setLocation(300, 300);
		this.setSize(size);
		this.add(tabpane);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

	}

	public void init() {
		initList = dao.getList();
		for (int i = 0; i < initList.size(); i++) {
			tablemodel.addRow(initList.get(i));
		}
	}

	public void tablesetting() {
		table.setRowMargin(0);
		table.getColumnModel().setColumnMargin(0);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);

		table.setShowVerticalLines(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1) {// 마우스 왼쪽 클릭
					modIntRow = table.getSelectedRow();

					if (e.getClickCount() == 2) {// 왼쪽 더블 클릭

					}
					if (e.getClickCount() == 3) {

						modIntRow = table.getSelectedRow();
					}
				}
			}
		});
		DefaultTableCellRenderer ts = new DefaultTableCellRenderer();
		ts.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tc = table.getColumnModel();
		for (int i = 0; i < tc.getColumnCount(); i++) {
			tc.getColumn(i).setCellRenderer(ts);
		}

	}

	private void createtb() {
		south_north.setLayout(new BoxLayout(south_north, BoxLayout.X_AXIS));
		JLabel all = new JLabel("총 금액");
		south_north.add(all);

		JTextField total = new JTextField(5);
		south_north.add(total);

		JButton modB = new JButton("주문하기");
		south_north.add(modB);
		modB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String in[] = new String[5];
				modIntRow = -1;
			}
		});

	}

	private void saveToDB(String[] in) {
		dto = new BasketlistDTO();
		dto.setCode(Integer.parseInt(in[0]));
		dto.setCname(in[1]);
		dto.setCnt(Integer.parseInt(in[2]));
		dto.setPrice(Integer.parseInt(in[3]));
		dao.Insert(dto);

	}

	private void createpanel() {
		this.add(tab_center, "Center");
		this.add(tab_south, "South");

		tab_center.setLayout(new BorderLayout());
		tab_center.add(tableScroll, "Center");
		tab_center.add(tab_south, "South");
		tabpane.add("shopping", tab_center);

		tab_south.setLayout(new BorderLayout());
		tab_south.add(south_north, "North");

	}

}
