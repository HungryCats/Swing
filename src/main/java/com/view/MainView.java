package com.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.dao.CoverageData;
import com.view.course.CourseView;
import com.view.school.SchoolView;

/**
 * @author HungryCats
 * @date 2023-10-22 02:18:49
 */
public class MainView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		setTitle("主页面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 15));
		menuBar.setBorderPainted(false);
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("CHART");
		menu.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 16));
		menuBar.add(menu);
		
		JMenuItem pieItem = new JMenuItem("PIE");
		pieItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 利用awt进行显示
				ChartFrame chartFrame = new ChartFrame("学籍异常信息", pieData());
				chartFrame.pack();
				chartFrame.setVisible(true);
				chartFrame.setLocationRelativeTo(null);
			}
		});
		menu.add(pieItem);
		
		JMenuItem lineItem = new JMenuItem("LINE");
		lineItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 利用awt进行显示
				ChartFrame chartFrame = new ChartFrame("实际出勤时长", linData());
				chartFrame.pack();
				chartFrame.setVisible(true);
				chartFrame.setLocationRelativeTo(null);
			}
		});
		menu.add(lineItem);
		
		JMenuItem barItem = new JMenuItem("BAR");
		barItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 利用awt进行显示
				ChartFrame chartFrame = new ChartFrame("新闻发布数量", barData());
				chartFrame.pack();
				chartFrame.setVisible(true);
				chartFrame.setLocationRelativeTo(null);
			}
		});
		menu.add(barItem);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton courseBtn = new JButton("课表管理");
		courseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CourseView().setVisible(true);
			}
		});
		courseBtn.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		courseBtn.setBounds(146, 108, 110, 45);
		contentPane.add(courseBtn);

		JButton schoolBtn = new JButton("学校信息管理");
		schoolBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SchoolView().setVisible(true);
			}
		});
		schoolBtn.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		schoolBtn.setBounds(379, 108, 110, 45);
		contentPane.add(schoolBtn);

		JButton subjectBtn = new JButton("学科管理");
		subjectBtn.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		subjectBtn.setBounds(146, 287, 110, 45);
		contentPane.add(subjectBtn);

		JButton cardBtn = new JButton("一卡通管理");
		cardBtn.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		cardBtn.setBounds(379, 287, 110, 45);
		contentPane.add(cardBtn);

		courseBtn.setFocusable(false);
		schoolBtn.setFocusable(false);
		subjectBtn.setFocusable(false);
		cardBtn.setFocusable(false);

		setLocationRelativeTo(null);
	}
	
	private JFreeChart pieData() {
		CoverageData cData = new CoverageData();
		// 创建数据
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("待审核", cData.selectPie(1));
		dataset.setValue("审核中", cData.selectPie(2));
		dataset.setValue("未通过", cData.selectPie(3));
		dataset.setValue("已通过", cData.selectPie(4));
		dataset.setValue("已驳回", cData.selectPie(5));
 
		// 创建主题样式
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		// 设置标题字体
		standardChartTheme.setExtraLargeFont(new Font("宋书", Font.BOLD, 20));
		// 设置图例的字体
		standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
		// 设置轴向的字体
		standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
		// 应用主题样式
		ChartFactory.setChartTheme(standardChartTheme);
		
		// 创建JFreeChart对象
		JFreeChart chart = ChartFactory.createPieChart(
				"饼图", // 图标题
				dataset, // 数据集
				true, true, false);
		return chart;
	}
	
	private JFreeChart linData() {
		CoverageData coverageData = new CoverageData();
		Map<String, List> selectLine = coverageData.selectLine();
		List list = selectLine.get("names");
		List list2 = selectLine.get("times");

		// 创建数据
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < list.size(); i++) {
			dataset.addValue((Integer) list2.get(i), "w", (String)list.get(i));
		}

		// 创建主题样式
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		// 设置标题字体
		standardChartTheme.setExtraLargeFont(new Font("宋书", Font.BOLD, 20));
		// 设置图例的字体
		standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
		// 设置轴向的字体
		standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
		// 应用主题样式
		ChartFactory.setChartTheme(standardChartTheme);
		
		// 创建JFreeChart对象
		JFreeChart chart = ChartFactory.createLineChart("折线图", // 图标题
				null, // x轴标题
				"Value Axis", // y轴标题
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向
				false, true, false);
		return chart;
	}
	
	private JFreeChart barData() {

		// 创建数据
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(1.0, "A", "Category1");
		dataset.addValue(2.0, "A", "Category2");
		dataset.addValue(3.0, "A", "Category3");
		dataset.addValue(4.0, "A", "Category4");
		dataset.addValue(5.0, "A", "Category5");
		
		dataset.addValue(5.0, "B", "Category1");
		dataset.addValue(2.0, "B", "Category2");
		dataset.addValue(7.0, "B", "Category3");
		dataset.addValue(6.0, "B", "Category4");
		dataset.addValue(3.0, "B", "Category5");
		
		dataset.addValue(7.0, "C", "Category1");
		dataset.addValue(9.0, "C", "Category2");
		dataset.addValue(3.0, "C", "Category3");
		dataset.addValue(6.0, "C", "Category4");
		dataset.addValue(4.0, "C", "Category5");
 
		dataset.addValue(7.0, "D", "Category1");
		dataset.addValue(9.0, "D", "Category2");
		dataset.addValue(3.0, "D", "Category3");
		dataset.addValue(6.0, "D", "Category4");
		dataset.addValue(4.0, "D", "Category5");
		
		// 创建主题样式
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		// 设置标题字体
		standardChartTheme.setExtraLargeFont(new Font("宋书", Font.BOLD, 20));
		// 设置图例的字体
		standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
		// 设置轴向的字体
		standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
		// 应用主题样式
		ChartFactory.setChartTheme(standardChartTheme);
		
		// 创建JFreeChart对象
		JFreeChart chart = ChartFactory.createBarChart(
				"柱状图", // 图标题
				"Category", 
				"Value", 
				dataset, 
				PlotOrientation.VERTICAL, 
				true, true, false);
		return chart;
	}
}
