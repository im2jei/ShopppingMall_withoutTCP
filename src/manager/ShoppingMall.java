package manager;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import DB.BasketlistDAO;
import DB.BasketlistDTO;
import DB.GoodsDAO;
import member.Basketlist;

public class ShoppingMall extends JFrame {
	JPopupMenu popup;
	JMenuItem save;
	Scanner in = new Scanner(System.in);
	String header[] = { "상품코드", "상품이름", "수량", "가격", "체크" };
	JTabbedPane tabpane = new JTabbedPane();
	DefaultTableModel tablemodel = new DefaultTableModel(header, 0);
	JTable table = new JTable(tablemodel);
	JScrollPane tableScroll = new JScrollPane(table);
	TableColumn column = null;
	// center panel
	JPanel tab_center = new JPanel();
	JPanel tab_south = new JPanel();
	JPanel south_north = new JPanel();

	JTextField total = new JTextField(10);

	int modIntRow = -1;

	String fileName = "data.txt";
	GoodsDAO gdao = GoodsDAO.getInstance();
	BasketlistDAO dao = BasketlistDAO.getInstance();
	BasketlistDTO dto = null;
	ArrayList<String[]> initList = new ArrayList<>();
	ArrayList<String[]> goodsList = new ArrayList<>();
	ArrayList<BasketlistDTO> rlist = new ArrayList<>();
	String id = null;
	int chk = 0;

	public ShoppingMall(String id) {
		super("쇼핑몰");// super의 생성자 호출
		this.id = id;
		System.out.println(id);
		Dimension size = new Dimension(600, 400);
		createpanel();
		createtb();
		tablesetting();
		createchkbox();
		changeCellEditor(table, column);
		init();

		this.setLocation(300, 300);
		this.setSize(size);
		this.add(tabpane);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

	}

	public void init() {
		initList = gdao.getList();
		for (int i = 0; i < initList.size(); i++) {
			tablemodel.addRow(initList.get(i));
			tablemodel.setValueAt(false, tablemodel.getRowCount() - 1, 4);
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
					String in[] = new String[5];
					modIntRow = table.getSelectedRow();
				}
			}

			private void delTableRow(int modIntRow) {
				tablemodel.removeRow(modIntRow);
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
		JLabel all = new JLabel("주문 시 금액");
		south_north.add(all);

		south_north.add(total);

		JButton addB = new JButton("장바구니에 담기");
		south_north.add(addB);

		addB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String in[] = new String[5];
				makesDTO(id, goodsList, chk);
				gotoInsert(dto);
				new Basketlist(id);
			}

		});

		JButton modB = new JButton("바로 주문하기");
		south_north.add(modB);
		modB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String in[] = new String[5];
				modIntRow = -1;
			}
		});

	}

	public void changeCellEditor(JTable table, TableColumn column) {
		TableColumn cntcombo = table.getColumnModel().getColumn(2);
		int num = 0;
		int rowNum = tablemodel.getRowCount();
		ArrayList<JComboBox> List = new ArrayList<>();
		JComboBox box = new JComboBox();
		int[] narray = null;
		for (int i = 0; i < initList.size(); i++) {
			num = Integer.parseInt(initList.get(i)[2]);
			narray = new int[num];

			System.out.println(num);
			for (int j = 1; j <= narray.length; j++) {
				box.addItem(j);
			}
		}

		table.getColumn("수량").setCellEditor(new DefaultCellEditor(box));
	}

	private void createchkbox() {
		Container c = getContentPane();
		JCheckBox box = new JCheckBox();
		Component comp = this;
		box.setHorizontalAlignment(JLabel.CENTER);
		table.getColumn("체크").setCellEditor(new DefaultCellEditor(box));
		box.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				box.setBorderPainted(true);
				box.setHorizontalAlignment(JLabel.CENTER);
				if (e.getStateChange() == ItemEvent.SELECTED) {
					modIntRow = table.getSelectedRow();
					String in[] = new String[5];
					for (int i = 0; i < table.getColumnCount() - 1; i++) {
						in[i] = (String) table.getValueAt(table.getSelectedRow(), i);
						chk = 1;
					}
					System.out.println("chk: " + chk);
					in[4] = String.valueOf(table.getSelectedRow());

					if (chk == 1) {
						String[] sarray = new String[5];
						for (int i = 0; i < table.getColumnCount(); i++) {
							String g = String.valueOf(table.getValueAt(table.getSelectedRow(), i));
							sarray[i] = g;
						}
						goodsList.add(sarray);
					}

					for (int i = 0; i < in.length; i++) {
						in[i] = total.getText();
						total.setText("");
						int sum = Integer.parseInt(in[2]) * Integer.parseInt(in[3]);
						total.setText(String.valueOf(sum));
						total = (JTextField) table.getValueAt(table.getSelectedRow(), sum);

					}
					for (String[] a : goodsList) {
						for (int i = 0; i < a.length; i++) {
							System.out.println("sarray" + "[" + i + "]" + a[i]);
						}

					}
				}
			}
		});
	}

	private void makesDTO(String id, ArrayList<String[]> goodsList, int chk) {
		for (int j = 0; j < goodsList.size(); j++) {
			dto = new BasketlistDTO();
			dto.setId(id);
			int code = Integer.parseInt(goodsList.get(j)[0]);
			dto.setCode(code);
			dto.setCname(goodsList.get(j)[1]);
			int cnt = Integer.parseInt(goodsList.get(j)[2]);
			dto.setCnt(cnt);
			int price = Integer.parseInt(goodsList.get(j)[3]);
			dto.setPrice(price);
			dto.setCheck(chk);
			rlist.add(dto);
		}
		for (int j = 0; j < rlist.size(); j++) {
			System.out.println("----------------------------");
			System.out.println("rlist: " + rlist.get(j).getId());
		}

	}

	private void gotoInsert(BasketlistDTO dto) {
		for (int j = 0; j < rlist.size(); j++) {
			dao.Insert(rlist.get(j));
		}

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
