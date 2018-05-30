package gui.panel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import util.CenterPanel;
import util.GUIUtil;

public class MainPanel extends JPanel {
	//加载皮肤
	static {
		GUIUtil.useLNF();
	}
	
	public static MainPanel instance = new MainPanel();
	public JToolBar tb = new JToolBar();//工具实例化
	//按钮实例化
	public JButton bSpend = new JButton();
	public JButton bRecord = new JButton();
	public JButton bCategory = new JButton();
	public JButton bReport = new JButton();
	public JButton bConfig = new JButton();
	public JButton bBackup = new JButton();
	public JButton bRecover = new JButton();

	public CenterPanel workingPanel;

	private MainPanel() {
		//为按钮设置图标
		GUIUtil.setImageIcon(bSpend, "home.png", "消费一览");
		GUIUtil.setImageIcon(bRecord, "record.png", "记一笔");
		GUIUtil.setImageIcon(bCategory, "category2.png", "消费分类");
		GUIUtil.setImageIcon(bReport, "report.png", "月消费报表");
		GUIUtil.setImageIcon(bConfig, "config.png", "设置");
		GUIUtil.setImageIcon(bBackup, "backup.png", "备份");
		GUIUtil.setImageIcon(bRecover, "restore.png", "恢复");
		
		//将按钮添加到工具栏
		tb.add(bSpend);
		tb.add(bRecord);
		tb.add(bCategory);
		tb.add(bReport);
		tb.add(bConfig);
		tb.add(bBackup);
		tb.add(bRecover);
		tb.setFloatable(false);//工具栏设置不浮动
		workingPanel = new CenterPanel(0.8);

		setLayout(new BorderLayout());
		add(tb, BorderLayout.NORTH);

		add(workingPanel, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		GUIUtil.showPanel(MainPanel.instance, 1);
	}
}