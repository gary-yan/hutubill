package test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import util.GUIUtil;
import util.CircleProgressBar;
import util.ColorUtil;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUIUtil.useLNF();
//		JPanel p = new JPanel();
//		p.add(new JButton("b 1"));
//		p.add(new JButton("b 2"));
//		GUIUtil.showPanel(p);
		//面板
		JPanel p = new JPanel();
		//进度条组件
		CircleProgressBar cpb = new CircleProgressBar();
		cpb.setBackgroundColor(ColorUtil.blueColor);
		cpb.setProgress(0);
		//按钮
		JButton b = new JButton("click");
		//添加组件
		p.setLayout(new BorderLayout());
		p.add(cpb, BorderLayout.CENTER);
		p.add(b, BorderLayout.SOUTH);
		//显示面板
		GUIUtil.showPanel(p);
		
		//给按钮加监听
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new SwingWorker() {

					@Override
					protected Object doInBackground() throws Exception {
						// TODO Auto-generated method stub
						for(int i = 0; i<101; i++) {
							cpb.setProgress(i);
							cpb.setForegroundColor(ColorUtil.getByPercentage(i));
						try {
							Thread.sleep(1000);
						}catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						}
						return null;
					}
					
				}.execute();
			}
			
		});
		
	}

}
