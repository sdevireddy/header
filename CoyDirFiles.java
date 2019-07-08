import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class CoyDirFiles {

	public static void main(String[] args) throws IOException {
		System.out.println("Running application");
		String outputDir = "C:\\outputcopy\\t";
		String currentDate = "20190708";

		String inputDir = "C:\\outputs\\temp";
		File inputFile = new File(inputDir/* + "\\" + "temp_" + currentDate+".csv" */);
		File outputFile = new File(outputDir /* +"\\"+ "temp_" + currentDate + "\\temp_" + currentDate+".csv" */);
		File input = new File("C:\\outputs\\temp");
		File[] files = input.listFiles();

		createDirectory();
		// String currentDate = "20190708";

		// File[] files = inputFilesList.listFiles();

		if (files != null) {
			for (File outerFile : files) {
				if (outerFile.isDirectory()) {
					File[] innerFile = outerFile.listFiles();
					for (File f : innerFile) {
						if (f.isFile()) {
							System.out.println("Absolute path" + outerFile.getAbsolutePath());
							System.out.println("Absolute path" + f.getName());
							File copyFile = new File(outerFile.getAbsolutePath() + "\\" + f.getName());
							File outFile = new File(outputDir + "\\" + currentDate + "\\" + f.getName());
							if (f.getName().contains(currentDate))
								copyFiles(copyFile, outFile);
						} else {
							continue;
						}
					}

				}

			}
		}

	}

	private static void createDirectory() {
		String currentDate = "20190708";
		File outputFile = new File("C:\\outputcopy\\t" + "\\" + "20190708");
		if (!outputFile.exists()) {
			outputFile.mkdir();
		}

	}

	private static void copyFiles(File source, File dest) throws IOException {
		// File source = new File("src/resources/bugs.txt");
		// File dest = new File("src/resources/bugs2.txt");

		InputStream is = null;
		OutputStream os = null;

		try {

			is = new FileInputStream(source);
			os = new FileOutputStream(dest);

			byte[] buffer = new byte[1024];
			int length;

			while ((length = is.read(buffer)) > 0) {

				os.write(buffer, 0, length);
			}

		} finally {

			if (is != null) {
				is.close();
			}

			if (os != null) {
				os.close();
			}
		}
	}

}
