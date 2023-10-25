package com.view.school;

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
import com.dao.SchoolDao;
import com.entity.School_information;

/**
 * @author HungryCats
 * @date 2023-10-23 04:29:18
 */
public class SchoolView extends JFrame {

	private JPanel contentPane;
	private JTextField schoolField;
	private JTable schoolTable;
	private DefaultTableModel dTableModel = null;

	School_information school_information = new School_information();

	Dao<School_information> schoolDao = new Dao<School_information>(school_information);
	
	SchoolDao dSchoolDao = new SchoolDao();

	/**
	 * Create the frame.
	 */
	public SchoolView() {
		setResizable(false);
		setTitle("学校信息管理");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 920, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel schoolLabel = new JLabel("学校名称");
		schoolLabel.setHorizontalAlignment(SwingConstants.CENTER);
		schoolLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 18));
		schoolLabel.setBounds(10, 18, 92, 30);
		contentPane.add(schoolLabel);

		schoolField = new JTextField();
		schoolField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		schoolField.setHorizontalAlignment(SwingConstants.LEFT);
		schoolField.setBounds(105, 22, 140, 25);
		contentPane.add(schoolField);
		schoolField.setColumns(10);

		JButton updateBtn = new JButton("修改");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = schoolTable.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "请先选中一条数据！");
				} else {
					// 获取选中行的数据
					Object[] rowData = new Object[schoolTable.getColumnCount()];
					for (int i = 0; i < schoolTable.getColumnCount(); i++) {
						rowData[i] = schoolTable.getValueAt(index, i);
					}
					new SchoolUpdate(rowData).setVisible(true);
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
				queryKeySchool();
			}
		});
		selectBtn.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		selectBtn.setBounds(715, 59, 70, 25);
		contentPane.add(selectBtn);

		JButton delBtn = new JButton("删除");
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delByIdSchool();
			}
		});
		delBtn.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		delBtn.setBounds(516, 59, 70, 25);
		contentPane.add(delBtn);

		JButton addBtn = new JButton("添加");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SchoolAdd().setVisible(true);
				dispose();
			}
		});
		addBtn.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		addBtn.setBounds(419, 59, 70, 25);
		contentPane.add(addBtn);

		JButton resetBtn = new JButton("重置");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				schoolField.setText("");
			}
		});
		resetBtn.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		resetBtn.setBounds(806, 59, 70, 25);
		contentPane.add(resetBtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 107, 890, 445);
		contentPane.add(scrollPane);

		schoolTable = new JTable();
		schoolTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		schoolTable.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		schoolTable.setFillsViewportHeight(true);
		schoolTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "\u7F16\u53F7",
				"\u5B66\u6821\u540D\u79F0", "\u5B66\u6821\u5B98\u7F51", "\u5730\u5740", "\u5B66\u6821\u7C7B\u578B" }) {
			boolean[] columnEditables = new boolean[] { true, true, true, true, false };

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		scrollPane.setViewportView(schoolTable);
		addBtn.setFocusable(false);
		delBtn.setFocusable(false);
		updateBtn.setFocusable(false);
		selectBtn.setFocusable(false);
		resetBtn.setFocusable(false);
		setLocationRelativeTo(null);

		this.dTableModel = (DefaultTableModel) schoolTable.getModel();

		queryAllSchool();
	}

	public void queryAllSchool() {
		dTableModel.setRowCount(0);
		List<School_information> selectAll = dSchoolDao.selectAll();
		for (School_information school_information : selectAll) {
			Vector<Object> v = new Vector<Object>();
			v.add(school_information.getId());
			v.add(school_information.getSchool_name());
			v.add(school_information.getSchool_web_address());
			v.add(school_information.getSchool_area());
			v.add(school_information.getSchool_type());
			dTableModel.addRow(v);
		}
	}
	
	public void queryKeySchool() {
		String schoolName = schoolField.getText().trim();
		List<School_information> selectByKey = dSchoolDao.selectByKey(schoolName);
		if (schoolName.isEmpty()) {
			queryAllSchool();
		} else {
			dTableModel.setRowCount(0);
			for (School_information school_information : selectByKey) {
				Vector<Object> vector = new Vector<Object>();
				vector.add(school_information.getId());
				vector.add(school_information.getSchool_name());
				vector.add(school_information.getSchool_web_address());
				vector.add(school_information.getSchool_area());
				vector.add(school_information.getSchool_type());
				dTableModel.addRow(vector);
			}
		}
	}
	
	public void delByIdSchool() {
		int index = schoolTable.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(this, "请先选中一条数据！");
		} else {
			Object id = schoolTable.getValueAt(index, 0);
			dSchoolDao.delByKey(id);
			JOptionPane.showMessageDialog(this, "删除成功!");
			queryAllSchool();
		}
	}
	
}
