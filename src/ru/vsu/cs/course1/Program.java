package ru.vsu.cs.course1;

import ru.vsu.cs.course1.cli.InputArgs;
import ru.vsu.cs.util.SwingUtils;

import java.util.Locale;

import static ru.vsu.cs.course1.cli.InputArgsUtils.*;


public class Program {

	public static void winMain() {
		SwingUtils.setLookAndFeelByName("Windows");
		Locale.setDefault(Locale.ROOT);
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(() -> new FrameMain().setVisible(true));
	}

	public static void main(String[] args){
		InputArgs params = parseCmdArgs(args);
		if (params.window) {
			winMain();
		}
		if (params.runTests){
			runTests();
		}
		if (params.runIndividualFilesCheck){
			multipleFilesCheck(params.inputFiles, params.outputFiles);
		}
		if (params.runIndividualFileCheck) {
			individualFileCheck(params.inputFile, params.outputFile);
		}
	}
}
