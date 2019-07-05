package com.agent.autojob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.sql.DataSource;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ZipGenerationTasklet implements Tasklet, InitializingBean, StepExecutionListener {

	/* private Resource directory; */
	List<String> fileList = new ArrayList<String>();

	@Value("${output_dir}")
	private String outputDir;

	@Value("${input_dir}")
	private String inputDir;

	/*
	 * private String OUTPUT_ZIP_FILE = "D:\\MyFile.zip"; private String
	 * SOURCE_FOLDER = "D:\\NisargPics";
	 */

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		zipIt(outputDir +"/"+ "MyFile.zip");

		return RepeatStatus.FINISHED;

	}

	/*
	 * public Resource getDirectory() { return directory; }
	 * 
	 * public void setDirectory(Resource directory) { this.directory = directory; }
	 */
	public void zipIt(String zipFile) {
		byte[] buffer = new byte[1024];
		try {
			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			File folder = new File(inputDir);
			String[] files = folder.list();

			System.out.println("Output to Zip : " + zipFile);

			for (String file : files) {
				System.out.println("File Added : " + file);
				ZipEntry ze = new ZipEntry(file);
				zos.putNextEntry(ze);
				FileInputStream in = new FileInputStream(inputDir + File.separator + file);
				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}

				in.close();
			}

			zos.closeEntry();
			zos.close();
			System.out.println("Done");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {

	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

}