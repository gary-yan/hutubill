package startup;

import javax.swing.SwingUtilities;

import gui.frame.MainFrame;
import gui.panel.MainPanel;
import gui.panel.SpendPanel;
import util.GUIUtil;
//稍微复杂点的程序都有一个类，专门负责启动的事情，通常这个类叫做startup.Bootstrap
public class Bootstrap {
	public static void main(String[] args) throws Exception{
		GUIUtil.useLNF();
		
		SwingUtilities.invokeAndWait(new Runnable() {
			@Override
			public void run() {
				MainFrame.instance.setVisible(true);
				MainPanel.instance.workingPanel.show(SpendPanel.instance);
			}
		});
	}
}
