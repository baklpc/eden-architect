/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ylzl.eden.common.excel.integration.fastexcel;

import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.event.AnalysisEventListener;
import cn.idev.excel.read.builder.ExcelReaderBuilder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.ylzl.eden.common.excel.ExcelReader;
import org.ylzl.eden.common.excel.reader.ExcelReadListener;

import java.io.File;
import java.io.InputStream;

/**
 * EasyExcel 读取 Excel
 *
 * @author <a href="mailto:shiyindaxiaojie@gmail.com">gyl</a>
 * @since 2.4.x
 */
@RequiredArgsConstructor
@Data
public class FastExcelReader implements ExcelReader {

	private final ExcelReaderBuilder excelReaderBuilder;

	private boolean readExcelLine = false;

	/**
	 * 读取 Excel
	 *
	 * @param inputStream       输入流
	 * @param head              标题映射
	 * @param excelReadListener 读取监听器
	 */
	@Override
	public void read(InputStream inputStream, Class<?> head, ExcelReadListener<?> excelReadListener) {
		excelReaderBuilder.file(inputStream)
			.head(head)
			.autoCloseStream(true)
			.registerReadListener(new AnalysisEventListener<Object>() {

				@Override
				public void invoke(Object data, AnalysisContext context) {
					excelReadListener.read(data);
				}

				@Override
				public void doAfterAllAnalysed(AnalysisContext context) {
					excelReadListener.finish();
				}
			})
			.sheet()
			.doRead();
	}

	/**
	 * 读取 Excel
	 *
	 * @param file              文件
	 * @param head              标题映射
	 * @param excelReadListener 读取监听器
	 */
	@Override
	public void read(File file, Class<?> head, ExcelReadListener<?> excelReadListener) {
		excelReaderBuilder.file(file)
			.head(head)
			.registerReadListener(new AnalysisEventListener<Object>() {

				@Override
				public void invoke(Object data, AnalysisContext context) {
					excelReadListener.read(data);
				}

				@Override
				public void doAfterAllAnalysed(AnalysisContext context) {
					excelReadListener.finish();
				}
			})
			.sheet()
			.doRead();
	}
}
