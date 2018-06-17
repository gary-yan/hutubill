package gui.panel;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.page.SpendPage;
import service.SpendService;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import util.GUIUtil;
import util.CircleProgressBar;
import util.ColorUtil;

public class SpendPanel extends WorkingPanel {

	static {
		GUIUtil.useLNF();
	}

	public static SpendPanel instance = new SpendPanel();

	JLabel lMonthSpend = new JLabel("本月消费");
	JLabel lTodaySpend = new JLabel("今日消费");
	JLabel lAvgSpendPerDay = new JLabel("日均消费");
	JLabel lMonthLeft = new JLabel("本月剩余");
	JLabel lDayAvgAvailable = new JLabel("日均可用");
	JLabel lMonthLeftDay = new JLabel("距离月末");

	JLabel vMonthSpend = new JLabel("￥2300");
	JLabel vTodaySpend = new JLabel("￥25");
	JLabel vAvgSpendPerDay = new JLabel("￥120");
	JLabel vMonthAvailable = new JLabel("￥2022");
	JLabel vDayAvgAvailable = new JLabel("￥365");
	JLabel vMonthLeftDay = new JLabel("15天");

	CircleProgressBar bar;

	public SpendPanel() {
		// 设置SpendPanel为borderlayout
		this.setLayout(new BorderLayout());
		// 创建barCircleProgressBar对象 并设置颜色
		bar = new CircleProgressBar();
		bar.setBackground(ColorUtil.blueColor);
		// 设置字体颜色
		GUIUtil.setColor(ColorUtil.grayColor, lMonthSpend, lTodaySpend, lAvgSpendPerDay, lMonthLeft, lDayAvgAvailable,
				lMonthLeftDay, vAvgSpendPerDay, vMonthAvailable, vDayAvgAvailable, vMonthLeftDay);
		GUIUtil.setColor(ColorUtil.blueColor, vMonthSpend, vTodaySpend);
		// 设置字体
		vMonthSpend.setFont(new Font("微软雅黑", Font.BOLD,23));
		vTodaySpend.setFont(new Font("微软雅黑", Font.BOLD,23));
		// 设置中间和南部区域
		this.add(center(), BorderLayout.CENTER);
		this.add(south(), BorderLayout.SOUTH);

	}

	// 基于整体JPanel private center方法
	private JPanel center() {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(west(), BorderLayout.WEST);
		p.add(center2(), BorderLayout.CENTER);

		return p;
	}

	// 基于 整体 JPanel private south方法
	private JPanel south() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 4));
		
		p.add(lAvgSpendPerDay);
		p.add(lMonthLeft);
		p.add(lDayAvgAvailable);
		p.add(lMonthLeftDay);
		p.add(vAvgSpendPerDay);
		p.add(vMonthAvailable);
		p.add(vDayAvgAvailable);
		p.add(vMonthLeftDay);

		return p;
	}

	// 基于JPanel中的center方法的private Component West方法
	private Component west() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4,1));
		p.add(lMonthSpend);
		p.add(vMonthSpend);
		p.add(lTodaySpend);
		p.add(vTodaySpend);
		return p;

	}

	// 基于JPanel中的center方法的private Component center2方法
	private Component center2() {
		return bar;
	}
	// main函数 showpanel
	public static void main(String[] args) {
		GUIUtil.showPanel(SpendPanel.instance);
	}

	@Override
	public void updateData() {
		// TODO Auto-generated method stub
		SpendPage spend = new SpendService().getSpendPage();
		vMonthSpend.setText(spend.monthSpend);
		vTodaySpend.setText(spend.todaySpend);
        vAvgSpendPerDay.setText(spend.avgSpendPerDay);
        vMonthAvailable.setText(spend.monthAvailable);
        vDayAvgAvailable.setText(spend.dayAvgAvailable);
        vMonthLeftDay.setText(spend.monthLeftDay);
        
        bar.setProgress(spend.usagePercentage);
        
        if (spend.isOverSpend) {
            vMonthAvailable.setForeground(ColorUtil.warningColor);
            vMonthSpend.setForeground(ColorUtil.warningColor);
            vTodaySpend.setForeground(ColorUtil.warningColor);
 
        } else {
            vMonthAvailable.setForeground(ColorUtil.grayColor);
            vMonthSpend.setForeground(ColorUtil.blueColor);
            vTodaySpend.setForeground(ColorUtil.blueColor);
        }
        bar.setForegroundColor(ColorUtil.getByPercentage(spend.usagePercentage));
        addListener();
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
		
	}
}
