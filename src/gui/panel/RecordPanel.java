package gui.panel;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import org.jdesktop.swingx.JXDatePicker;

import util.GUIUtil;
import gui.model.CategoryComboBoxModel;
import util.ColorUtil;

public class RecordPanel extends JPanel {
	
	 static {
		 GUIUtil.useLNF();
	 }
	 
	 public static RecordPanel instance = new RecordPanel();
	 
	 JLabel lSpend = new JLabel("花费（￥）");
	 JLabel lCategory = new JLabel("分类");
	 JLabel lComment = new JLabel("备注");
	 JLabel lDate = new JLabel("日期");
	 //花费 文字框 默认为0
	 public JTextField tfSpend = new JTextField("0");
	 //引用category类 添加到combobox
	 public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
	 public JComboBox<String> cbCategory = new JComboBox<>(cbModel);
	 //备注 文本框 默认为空
	 public JTextField tfComment = new JTextField();
	 //时间选择空间 这里使用添加的外部库
	 public JXDatePicker datepick = new JXDatePicker(new Date());
	 //按钮
	 JButton bSubmit = new JButton("记一笔");
	 
	 
	 
	 //RecordPanel设置整体布局 
	 public RecordPanel() {
//		 this.setLayout(new BorderLayout());
//		 
//		 this.add(pInput(), BorderLayout.NORTH);
//		 this.add(pSubmiot(), BorderLayout.CENTER);
	 GUIUtil.setColor(ColorUtil.grayColor, lSpend,lCategory,lComment,lDate);
	 GUIUtil.setColor(ColorUtil.blueColor, bSubmit);
	 JPanel pInput = new JPanel();
	 JPanel pSubmit = new JPanel();
	 int gap = 40;
	 pInput.setLayout(new GridLayout(4,2,gap,gap));
	 //花费 0
	 pInput.add(lSpend);
	 pInput.add(tfSpend);
	 //分类 
	 pInput.add(lCategory);
	 pInput.add(cbCategory);
	 //备注
	 pInput.add(lComment);
	 pInput.add(tfComment);
	 //日期
	 pInput.add(lDate);
	 pInput.add(datepick);
	 //提交按钮
	 pSubmit.add(bSubmit);
	 
	 this.setLayout(new BorderLayout());
	 this.add(pInput,BorderLayout.NORTH);
	 this.add(pSubmit, BorderLayout.CENTER);
	 }
	 
//	 private JPanel plnput() {
//		 JPanel p = new JPanel();
//		 
//		 return p;
//	 }
//	 private JPanel pSubmiot() {
//		 JPanel p = new JPanel();
//		 
//		 return p;
//	 }
	 public static void main(String[] args) {
		 GUIUtil.showPanel(RecordPanel.instance);
	 }
}
