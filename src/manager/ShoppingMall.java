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
//	private static ShoppingMall shop;
	String id = null;
	int chk = 0;

	public ShoppingMall(String id) {
		super("쇼핑몰");// super의 생성자 호출
		this.id = id;
		Dimension size = new Dimension(600, 400);
		createpanel();
		createtb();
		tablesetting();
		createchkbox();
		init();

		this.setLocation(300, 300);
		this.setSize(size);
		this.add(tabpane);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

	}

//	public static ShoppingMall getinstance() {
//		if(shop==null) {
//			shop=new ShoppingMall();
//		}
//		return shop;
//		
//	}

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
					if (e.getClickCount() == 2) {// 왼쪽 더블 클릭

					}
					if (e.getClickCount() == 3) {

					}
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
		JLabel all = new JLabel("총 금액");
		south_north.add(all);

		south_north.add(total);

		JButton addB = new JButton("장바구니에 담기");
		south_north.add(addB);

		addB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String in[] = new String[5];
				saveToDB(id, in, chk);
				new Basketlist();
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

	private void createchkbox() {
		Container c = getContentPane();
		JCheckBox box = new JCheckBox();
		Component comp = this;
		box.setHorizontalAlignment(JLabel.CENTER);
		table.getColumn("체크").setCellEditor(new DefaultCellEditor(box));
//		TableCellRenderer getTableCellRendererComponent = null;
//		table.getColumn("체크").setCellRenderer(dcr);
		box.addItemListener(new ItemListener() {
			// chk = 0;

			@Override
			public void itemStateChanged(ItemEvent e) {
//				int select = 0;
				box.setBorderPainted(true);
				box.setHorizontalAlignment(JLabel.CENTER);
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String in[] = new String[5];
					for (int i = 0; i < table.getColumnCount() - 1; i++) {
						in[i] = (String) table.getValueAt(table.getSelectedRow(), i);
						chk = 1;
					}
					modIntRow = table.getSelectedRow();
					for (int i = 0; i < initList.size(); i++) {
//						initList.get(i).setText((String) table.getValueAt(modIntRow, i));
					}
					modIntRow = table.getSelectedRow();
					System.out.println(chk);
					// saveToDB(in, chk);
					in[4] = String.valueOf(table.getSelectedRow());
//					select = 1;
				}
			}
		});
	}

//
//	private DefaultTableCellRenderer dcr = new DefaultTableCellRenderer() {
//		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
//				int row, int column) {
//			Component comp = null;
//
//			if (column == 4) {
//				JCheckBox box = new JCheckBox();
//				box.setBorderPainted(true);
//				box.setHorizontalAlignment(JLabel.CENTER);
//				comp = box;
//			}
//			return comp;
//		}
//	};

	private void saveToDB(String id, String[] in, int chk) {
		dto = new BasketlistDTO();
		// for (int i = 0; i < in.length; i++) {
		dto.setId(id);
		int code = Integer.parseInt(in[0]);
		dto.setCode(code);
		dto.setCname(in[1]);
		int cnt = Integer.parseInt(in[2]);
		dto.setCnt(cnt);
		int price = Integer.parseInt(in[3]);
		dto.setPrice(price);
		dto.setCheck(chk);
		dao.Insert(dto);

		// }
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
