package com.ttsc.data.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	private static final String FORMART_STR = "yyyy-MM-dd HH:mm:ss";
	private static DecimalFormat df = new DecimalFormat("#.########");
	
	private static final Logger logger = LoggerFactory
			.getLogger(FileUtil.class);

	public static String getFx(String fileName) {
		int index = fileName.lastIndexOf(".");
		String fx = fileName.substring(index + 1);
		return fx;
	}
}
