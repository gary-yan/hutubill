package gui.panel;

import java.awt.BorderLayout;
import java.awt.color.*;
import javax.swing.JPanel;
import javax.swing.JButton;

import util.ColorUtil;
import util.GUIUtil;


public class BackupPanel extends JPanel {
	static {
		GUIUtil.useLNF();
	}
	
	public static BackupPanel instance = new BackupPanel();
	JButton bBackup = new JButton("备份");
//	JButton bA = new JButton("bA");
	public BackupPanel() {
		GUIUtil.setColor(ColorUtil.blueColor, bBackup);
//		this.setLayout(new BorderLayout());
		this.add(bBackup);
//		this.add(BP(), BorderLayout.SOUTH);
	}
//	private JPanel BP() {
//		JPanel BP = new JPanel();
//		BP.add(bA);
		
		
//		return BP;
//	}
//	
	public static void main(String[] args) {
		GUIUtil.showPanel(BackupPanel.instance);
	}
}
