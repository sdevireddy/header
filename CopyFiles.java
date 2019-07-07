import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CopyFiles {

	public static void main(String args[]) throws IOException {
		String currentDate = "20190707";
		String outputDir = "C:\\outputcopy\\";

		createDirectory(outputDir, currentDate);
		String inputDir = "C:\\outputs";
		File inputFile = new File(inputDir + "\\" + "temp_" + currentDate+".csv");
		File outputFile = new File(outputDir +"\\"+ "temp_" + currentDate + "\\temp_" + currentDate+".csv");
		copyFiles(inputFile, outputFile);

		// File folder = new File(inputDir);
		// String[] files = folder.list();
		// boolean result=findDir(outfolder, currentDate);
		// System.out.println("Directory found" + outfolder);

	}

	/*
	 * private static void copyFiles(String inputDir, String currentDate) {
	 * 
	 * InputStream is = null; OutputStream os = null; try { is = new
	 * FileInputStream(src); os = new FileOutputStream(dest);
	 * 
	 * // buffer size 1K byte[] buf = new byte[1024];
	 * 
	 * int bytesRead; while ((bytesRead = is.read(buf)) > 0) { os.write(buf, 0,
	 * bytesRead); } } finally { is.close(); os.close(); }
	 * 
	 * 
	 * }
	 */

	/*
	 * public void zipIt(String zipFile) { byte[] buffer = new byte[1024]; try {
	 * FileOutputStream fos = new FileOutputStream(zipFile); ZipOutputStream zos =
	 * new ZipOutputStream(fos); File folder = new File(inputDir); String[] files =
	 * folder.list();
	 * 
	 * System.out.println("Output to Zip : " + zipFile);
	 * 
	 * for (String file : files) { System.out.println("File Added : " + file);
	 * ZipEntry ze = new ZipEntry(file); zos.putNextEntry(ze); FileInputStream in =
	 * new FileInputStream(inputDir + File.separator + file); int len; while ((len =
	 * in.read(buffer)) > 0) { zos.write(buffer, 0, len); }
	 * 
	 * in.close(); }
	 * 
	 * zos.closeEntry(); zos.close(); System.out.println("Done"); } catch
	 * (IOException ex) { ex.printStackTrace(); } }
	 */

	private static void createDirectory(String outputDir, String currentDate) {
		File outfolder = new File(outputDir);
		File file = new File(outputDir + "temp_" + currentDate);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}
	}

	private static boolean findDir(File root, String name) {
		if (root.getName().contains(name)) {
			return true;
		}

		File[] files = root.listFiles();

		if (files != null) {
			for (File f : files) {
				if (f.isDirectory()) {
					boolean myResult = findDir(f, name);
					// this just means this branch of the
					// recursion reached the end of the
					// directory tree without results, but
					// we don't want to cut it short here,
					// we still need to check the other
					// directories, so continue the for loop
					if (myResult == false) {
						continue;
					}
					// we found a result so return!
					else {
						return true;
					}
				}
			}
		}

		// we don't actually need to change this. It just means we reached
		// the end of the directory tree (there are no more sub-directories
		// in this directory) and didn't find the result
		return false;
	}

	private static void copyFiles(File source, File dest) throws IOException {
		//File source = new File("src/resources/bugs.txt");
        //File dest = new File("src/resources/bugs2.txt");

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
