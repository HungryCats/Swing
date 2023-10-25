package com.view.course;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.dao.Dao;
import com.entity.School_course_manage;

/**
 * @author HungryCats
 * @date 2023-10-22 11:00:57
 */
public class CourseView extends JFrame {

	private JPanel contentPane;
	private JTextField teacherField;
	private JTable courseTable;
	private DefaultTableModel dTableModel = null;

	School_course_manage school_course_manage = new School_course_manage();

	Dao<School_course_manage> courseDao = new Dao<>(school_course_manage);
	
	/**
	 * Create the frame.
	 */
	public CourseView() {
		setResizable(false);
		setTitle("课表管理");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 920, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel teacherLabel = new JLabel("教师姓名");
		teacherLabel.setHorizontalAlignment(SwingConstants.CENTER);
		teacherLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 18));
		teacherLabel.setBounds(10, 18, 92, 30);
		contentPane.add(teacherLabel);

		teacherField = new JTextField();
		teacherField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		teacherField.setHorizontalAlignment(SwingConstants.LEFT);
		teacherField.setBounds(105, 22, 140, 25);
		contentPane.add(teacherField);
		teacherField.setColumns(10);

		JButton updateBtn = new JButton("修改");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = courseTable.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "请先选中一条数据！");
				} else {
					// 获取选中行的数据
					Object[] rowData = new Object[courseTable.getColumnCount()];
					for (int i = 0; i < courseTable.getColumnCount(); i++) {
						rowData[i] = courseTable.getValueAt(index, i);
					}
					new CourseUpdate(rowData).setVisible(true);
					dispose();
				}
			}
		});
		updateBtn.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		updateBtn.setBounds(614, 59, 70, 25);
		contentPane.add(updateBtn);

		JButton selectBtn = new JButton("查询");
		selectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryTeacher();
			}
		});
		selectBtn.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		selectBtn.setBounds(715, 59, 70, 25);
		contentPane.add(selectBtn);

		JButton delBtn = new JButton("删除");
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delByIdCourse();
			}
		});
		delBtn.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		delBtn.setBounds(516, 59, 70, 25);
		contentPane.add(delBtn);

		JButton addBtn = new JButton("添加");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CourseAdd().setVisible(true);
				dispose();
			}
		});
		addBtn.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		addBtn.setBounds(419, 59, 70, 25);
		contentPane.add(addBtn);

		JButton resetBtn = new JButton("重置");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teacherField.setText("");
			}
		});
		resetBtn.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		resetBtn.setBounds(806, 59, 70, 25);
		contentPane.add(resetBtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 107, 890, 445);
		contentPane.add(scrollPane);

		courseTable = new JTable();
		courseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		courseTable.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		courseTable.setFillsViewportHeight(true);
		courseTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "\u7F16\u53F7", "\u6559\u5E08\u59D3\u540D", "\u73ED\u7EA7", "\u5B66\u79D1\u540D\u79F0",
						"\u6388\u8BFE\u5F00\u59CB\u65F6\u95F4", "\u6388\u8BFE\u7ED3\u675F\u65F6\u95F4",
						"\u72B6\u6001" }) {
			boolean[] columnEditables = new boolean[] { true, true, true, true, true, true, false };

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		courseTable.getColumnModel().getColumn(4).setPreferredWidth(89);
		courseTable.getColumnModel().getColumn(5).setPreferredWidth(86);
		scrollPane.setViewportView(courseTable);

		addBtn.setFocusable(false);
		delBtn.setFocusable(false);
		updateBtn.setFocusable(false);
		selectBtn.setFocusable(false);
		resetBtn.setFocusable(false);
		setLocationRelativeTo(null);

		this.dTableModel = (DefaultTableModel) courseTable.getModel();

		queryAllCourse();
	}

	public void queryAllCourse() {
		dTableModel.setRowCount(0);
		List<School_course_manage> courseList = courseDao.getAll();
		for (School_course_manage course : courseList) {
			Vector<Object> v = new Vector<Object>();
			v.add(course.getId());
			v.add(course.getTeacher_name_id());
			v.add(course.getClasses());
			v.add(course.getSubject_id());
			v.add(course.getTeaching_start_time());
			v.add(course.getTeaching_end_time());
			if (course.getState() == 1) {
				v.add("课程准备");
			} else if (course.getState() == 2) {
				v.add("授课中");
			} else {
				v.add("已结束");
			}
			dTableModel.addRow(v);
		}
	}

	public void queryTeacher() {
		String teacher = teacherField.getText().trim();
		List<School_course_manage> queryByKey = courseDao.queryByKey("teacher_name_id", teacher);
		if (teacher.isEmpty()) {
			queryAllCourse();
		} else {
			dTableModel.setRowCount(0);
			for (School_course_manage teacherName : queryByKey) {
				Vector<Object> vector = new Vector<Object>();
				vector.add(teacherName.getId());
				vector.add(teacherName.getTeacher_name_id());
				vector.add(teacherName.getClasses());
				vector.add(teacherName.getSubject_id());
				vector.add(teacherName.getTeaching_start_time());
				vector.add(teacherName.getTeaching_end_time());
				if (teacherName.getState() == 1) {
					vector.add("课程准备");
				} else if (teacherName.getState() == 2) {
					vector.add("授课中");
				} else {
					vector.add("已结束");
				}
				dTableModel.addRow(vector);
			}
		}
	}

	public void delByIdCourse() {
		int index = courseTable.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(this, "请先选中一条数据！");
		} else {
			Object id = courseTable.getValueAt(index, 0);
			courseDao.delBykey("id", id);
			JOptionPane.showMessageDialog(this, "删除成功!");
			queryAllCourse();
		}
	}
	public Object[] getCourseId() {
		int index = courseTable.getSelectedRow();
		// 获取选中行的数据
		Object[] rowData = new Object[courseTable.getColumnCount()];
		for (int i = 0; i < courseTable.getColumnCount(); i++) {
			rowData[i] = courseTable.getValueAt(index, i);
		}
		return rowData;
	}
}
