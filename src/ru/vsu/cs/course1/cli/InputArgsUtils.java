package ru.vsu.cs.course1.cli;

import org.apache.commons.cli.*;
import ru.vsu.cs.course1.FileUtils;
import ru.vsu.cs.course1.Task;
import ru.vsu.cs.course1.model.Triangle;

import java.io.File;
import java.util.List;

public class InputArgsUtils {
	public static final String WINDOWED_HT = "Запустить с оконным интерфейсом.";
	public static final String HELP_HT = "Показать справку.";
	public static final String TEST_HT = "Прогнать файлы из папки testIn и записать результаты в файлы с теми же названиями, но в папку testOut.";
	public static final String INPUT_HT = "Входной файл.";
	public static final String OUTPUT_HT = "Выходной файл.";
	public static final String IF_HT = "Входные файлы.";
	public static final String OF_HT = "Выходные файлы. " +
			"Очерёдность соответствует порядку получения входных файлов.";

	/*
	 * Объявление возможных аргументов командной строки
	 * */
	public static Options fillOptions(){
		Options options = new Options();
		Option windowed = Option.builder("w")
				.longOpt("window")
				.desc(WINDOWED_HT)
				.build();
		Option help = Option.builder("h")
				.longOpt("help")
				.desc(HELP_HT)
				.build();
		Option tests = Option.builder("t")
				.longOpt("tests")
				.desc(TEST_HT)
				.build();
		Option input = Option.builder("i")
				.longOpt("input-file")
				.hasArg()
				.desc(INPUT_HT)
				.build();
		Option output = Option.builder("o")
				.longOpt("output-file")
				.hasArg()
				.desc(OUTPUT_HT)
				.build();
		Option inputFiles = Option.builder("if")
				.longOpt("input-files")
				.hasArgs()
				.valueSeparator(',')
				.desc(IF_HT)
				.build();
		Option outputFiles = Option.builder("of")
				.longOpt("output-files")
				.hasArgs()
				.valueSeparator(',')
				.desc(OF_HT)
				.build();
		options.addOption(windowed);
		options.addOption(help);
		options.addOption(input);
		options.addOption(output);
		options.addOption(inputFiles);
		options.addOption(outputFiles);
		options.addOption(tests);

		return options;
	}
	public static void individualFileCheck(String inFile, String outFile){
		System.out.printf("------------------------------%n");
		System.out.printf("Обрабатываю файл: %s %n", inFile);
		List<Triangle> triangles = FileUtils.getTrianglesFromFile(inFile);
		if (triangles.size() == 0) {
			System.err.printf("Не удалось прочитать тестовый массив или он пустой. %n%n");
			System.exit(1);
		}
		System.out.printf("Входные данные: %n");
		for (Triangle t: triangles) {
			System.out.println(t);
		}
		System.out.println();
		List<List<Triangle>> subsets = Task.getSubsets(triangles);
		System.out.printf("Выходные данные: %n");
		for (List<Triangle> subset: subsets) {
			for (Triangle t: subset) {
				System.out.println(t);
			}
			System.out.println();
		}
		System.out.printf("Результат будет записан в %s %n", outFile);
		FileUtils.writeSubsetsToFile(subsets, outFile);
		System.out.printf("------------------------------%n");
	}
	public static void multipleFilesCheck(String[] inFiles, String[] outFiles){
		int overallCalculations = Math.min(inFiles.length, outFiles.length);
		for (int iFI = 0; iFI < overallCalculations; iFI++) {
			System.out.printf("%n%n%s -> %s %n", inFiles[iFI], outFiles[iFI]);
			individualFileCheck(inFiles[iFI], outFiles[iFI]);
		}
		if (inFiles.length == outFiles.length) return;
		System.err.println("Были пропущены некоторые файлы из-за несоответствия входных и выходных значений\n" +
				"аргументов командной строки (-i и -o ИЛИ -if и -of)");
	}
	public static void runTests() {
		String workingDir = System.getProperty("user.dir");
		File inputFilesDir = new File(workingDir + "\\testIn\\");
		File outputFilesDir = new File(workingDir + "\\testOut\\");
		if (!inputFilesDir.exists()){
			try {
				inputFilesDir.mkdir();
			} catch (Exception e){
				System.err.println(e.getMessage());
				System.exit(10);
			}

		}
		if (!outputFilesDir.exists()){
			try {
				outputFilesDir.mkdir();
			} catch (Exception e){
				System.err.println(e.getMessage());
				System.exit(11);
			}
		}
		File[] inputFiles = inputFilesDir.listFiles();
		if (inputFiles == null) return;
		String[] inputFilesPaths = new String[inputFiles.length];
		String[] outputFilesPaths = new String[inputFiles.length];
		for (int i = 0; i < inputFiles.length; i++) {
			inputFilesPaths[i] = "testIn\\" + inputFiles[i].getName();
			outputFilesPaths[i] = "testOut\\" + inputFiles[i].getName();
			//System.out.println(inputFiles[i].getName());
		}

		multipleFilesCheck(inputFilesPaths, outputFilesPaths);

	}

	public static InputArgs parseCmdArgs(String[] args) {
		InputArgs inputArgs = new InputArgs();
		if (args == null) return inputArgs;
		Options options = fillOptions();
		/*
		 * Непосредственный парсинг.
		 * */
		CommandLine line;
		try {
			line = new DefaultParser().parse(options, args);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return inputArgs;
		}

		if (line.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("<cmd> [OPTIONS]", options);
			return inputArgs;
		}
		if (line.hasOption("w")) {
			System.out.println("Будет запущен оконный интерфейс");
			inputArgs.window = true;
		}
		if (line.hasOption("t")) {
			System.out.println("Тесты:");
			inputArgs.runTests = true;
		}
		if (line.hasOption("i") && line.hasOption("o")) {
			String inputFile = line.getOptionValue("i");
			String outputFile = line.getOptionValue("o");
			System.out.println("Будут обработаны следующие файлы: ");
			System.out.printf("%s - как входной. %n", inputFile);
			System.out.printf("%s - как выходной. %n", outputFile);
			inputArgs.inputFile = inputFile;
			inputArgs.outputFile = outputFile;
			inputArgs.runIndividualFileCheck = true;
		}
		if (line.hasOption("if") && line.hasOption("of")) {
			inputArgs.runIndividualFilesCheck = true;
			String[] inputFiles = line.getOptionValues("if");
			String[] outputFiles = line.getOptionValues("of");
			inputArgs.inputFiles = inputFiles;
			inputArgs.outputFiles = outputFiles;
		}
		return inputArgs;
	}
}
