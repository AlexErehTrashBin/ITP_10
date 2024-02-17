package ru.vsu.cs.course1;

import ru.vsu.cs.course1.model.Triangle;
import ru.vsu.cs.util.ArrayUtils;
import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.List;


public class FrameMain extends JFrame {
	private JPanel panelMain;
	private JTable tableInput;
	private JButton buttonLoadInputFromFile;
	private JButton buttonRandomInput;
	private JButton buttonSaveInputInfoFile;
	private JButton buttonSolve;
	private JButton buttonSaveOutputIntoFile;
	private JTable tableOutput;

	private final JFileChooser fileChooserOpen;
	private final JFileChooser fileChooserSave;


	public FrameMain() {
		this.setTitle("FrameMain");
		this.setContentPane(panelMain);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();

		JTableUtils.initJTableForArray(tableInput, 100, true, true, true, false);
		JTableUtils.initJTableForArray(tableOutput, 100, true, true, true, false);
		//tableOutput.setEnabled(false);
		tableInput.setRowHeight(25);
		tableOutput.setRowHeight(25);

		fileChooserOpen = new JFileChooser();
		fileChooserSave = new JFileChooser();
		fileChooserOpen.setCurrentDirectory(new File("."));
		fileChooserSave.setCurrentDirectory(new File("."));
		FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
		fileChooserOpen.addChoosableFileFilter(filter);
		fileChooserSave.addChoosableFileFilter(filter);

		fileChooserSave.setAcceptAllFileFilterUsed(false);
		fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
		fileChooserSave.setApproveButtonText("Save");

		JMenuBar menuBarMain = new JMenuBar();
		setJMenuBar(menuBarMain);

		JMenu menuLookAndFeel = new JMenu();
		menuLookAndFeel.setText("Вид");
		menuBarMain.add(menuLookAndFeel);
		SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

		JTableUtils.writeArrayToJTable(tableInput, new int[][]{
				{0, 0, 2, 0, 0, 2},
				{0, 0, 4, 0, 0, 4},
				{0, 0, 8, 0, 0, 8},
				{0, 3, 8, 0, 0, 8}
		});

		this.pack();


		buttonLoadInputFromFile.addActionListener(actionEvent -> {
			try {
				if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
					double[][] arr = ArrayUtils.readDoubleArray2FromFile(fileChooserOpen.getSelectedFile().getPath());
					JTableUtils.writeArrayToJTable(tableInput, arr);
				}
			} catch (Exception e) {
				SwingUtils.showErrorMessageBox(e);
			}
		});
		buttonRandomInput.addActionListener(actionEvent -> {
			try {
				int[][] intMatrix = ArrayUtils.createRandomIntMatrix(
						tableInput.getRowCount(), tableInput.getColumnCount(), 4);
				JTableUtils.writeArrayToJTable(tableInput, intMatrix);
			} catch (Exception e) {
				SwingUtils.showErrorMessageBox(e);
			}
		});
		buttonSaveInputInfoFile.addActionListener(actionEvent -> {
			try {
				if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
					double[][] matrix = JTableUtils.readDoubleMatrixFromJTable(tableInput);
					String file = fileChooserSave.getSelectedFile().getPath();
					if (!file.toLowerCase().endsWith(".txt")) {
						file += ".txt";
					}
					ArrayUtils.writeArrayToFile(file, matrix);
				}
			} catch (Exception e) {
				SwingUtils.showErrorMessageBox(e);
			}
		});
		buttonSaveOutputIntoFile.addActionListener(actionEvent -> {
			try {
				if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
					int[][] matrix = JTableUtils.readIntMatrixFromJTable(tableOutput);
					String file = fileChooserSave.getSelectedFile().getPath();
					if (!file.toLowerCase().endsWith(".txt")) {
						file += ".txt";
					}
					ArrayUtils.writeArrayToFile(file, matrix);
				}
			} catch (Exception e) {
				SwingUtils.showErrorMessageBox(e);
			}
		});
		buttonSolve.addActionListener(actionEvent -> {
			try {
				JTableUtils.writeArrayToJTable(tableOutput, new String[]{null});
				double[][] matrix = JTableUtils.readDoubleMatrixFromJTable(tableInput);
				if (matrix == null || matrix.length == 0) {
					return;
				}
				List<Triangle> triangles = TaskUtils.getTrianglesFromMatrix(matrix);
				List<List<Triangle>> subsets = Task.getSubsets(triangles);
				matrix = TaskUtils.convertSubsetsToMatrix(subsets);
				JTableUtils.writeArrayToJTable(tableOutput, matrix);
			} catch (Exception e) {
				SwingUtils.showErrorMessageBox(e);
			}
		});
	}
}
