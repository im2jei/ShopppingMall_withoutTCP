package manager;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import DB.GoodsDTO;

public class MyCheckbox extends DefaultTableCellRenderer {
	GoodsDTO dto = null;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component comp = this;
		if (column == 4) {
			JCheckBox box = new JCheckBox();
	         box.setBorderPainted(true);
	         box.setHorizontalAlignment(JLabel.CENTER);
		}
		return this;
	}
}
