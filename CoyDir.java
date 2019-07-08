import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class CoyDir {

	public static void main(String[] args) {
		String outputDir = "C:\\outputcopy\\";
		String currentDate = "20190707";
		
		String inputDir = "C:\\outputs";
		File inputFile = new File(inputDir/* + "\\" + "temp_" + currentDate+".csv" */);
		File outputFile = new File(outputDir /* +"\\"+ "temp_" + currentDate + "\\temp_" + currentDate+".csv" */);
		copyFolder(inputFile,outputFile);
	}

	static void copyFolder(File src, File dest) {
		// checks
		if (src == null || dest == null)
			return;
		if (!src.isDirectory())
			return;
		if (dest.exists()) {
			if (!dest.isDirectory()) {
				// System.out.println("destination not a folder " + dest);
				return;
			}
		} else {
			dest.mkdir();
		}

		if (src.listFiles() == null || src.listFiles().length == 0)
			return;

		String strAbsPathSrc = src.getAbsolutePath();
		String strAbsPathDest = dest.getAbsolutePath();

		try {
			Files.walkFileTree(src.toPath(), new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					File dstFile = new File(
							strAbsPathDest + file.toAbsolutePath().toString().substring(strAbsPathSrc.length()));
					if (dstFile.exists())
						return FileVisitResult.CONTINUE;

					if (!dstFile.getParentFile().exists())
						dstFile.getParentFile().mkdirs();

					// System.out.println(file + " " + dstFile.getAbsolutePath());
					Files.copy(file, dstFile.toPath());

					return FileVisitResult.CONTINUE;
				}
			});

		} catch (IOException e) {
			// e.printStackTrace();
			return;
		}

		return;
	}

}
