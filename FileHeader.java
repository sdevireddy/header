package com.agent.autojob;

import java.io.IOException;
import java.io.Writer;

import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.stereotype.Component;

@Component
public class FileHeader implements FlatFileHeaderCallback {

	@Override
	public void writeHeader(Writer writer) throws IOException {
		writer.write("id,name,dob,description,mailid,state");
	}
}
