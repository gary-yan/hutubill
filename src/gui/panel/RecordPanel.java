package gui.panel;


import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import util.GUIUtil;
import util.CircleProgressBar;
import util.ColorUtil;

public class RecordPanel extends JPanel {
	
	 static {
		 GUIUtil.useLNF();
	 }
	 
	 public static RecordPanel instance = new RecordPanel();
	 JLabel Spend = new JLabel("花费（￥）");
	 JLabel Category = new JLabel("分类");
	 JLabel note = new JLabel("备注");
	 JLabel Date = new JLabel("日期");
	 
	 //RecordPanel设置整体布局 
	 public RecordPanel() {
		 this.setLayout(new BorderLayout());
		 
		 this.add(plnput(), BorderLayout.NORTH);
		 this.add(pSubmiot(), BorderLayout.CENTER);
	 }
	 
	 private JPanel plnput() {
		 JPanel p = new JPanel();
		 
		 return p;
	 }
	 private JPanel pSubmiot() {
		 JPanel p = new JPanel();
		 
		 return p;
	 }
}
