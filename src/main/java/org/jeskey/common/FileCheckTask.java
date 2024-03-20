package org.jeskey.common;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.jeskey.domain.BoardAttach;
import org.jeskey.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class FileCheckTask {

	@Value("${org.jeskey.upload.path}")
	private String uploadPath;

	private final BoardMapper boardMapper;

	private String getFolderYesterDay() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DATE, 0);	//어제 날짜

		String str = sdf.format(cal.getTime());	//어제 날짜를 yyMMdd 형식으로 변환

		return str;
	}

	@Scheduled(cron="0 0 2 * * *")
	public void checkFiles() throws Exception {

		log.warn("File Check Task run...............");

		//DB에 존재하는 파일 리스트
		List<BoardAttach> fileList = boardMapper.getOldFiles();

		List<Path> fileListPaths = fileList.stream()
				.map(vo -> Paths.get(uploadPath, vo.getDate(), vo.getUuid() + "_" + vo.getFile_name()))
				.collect(Collectors.toList());

		fileList.stream().map(vo -> Paths.get(uploadPath, vo.getDate(),
							"s_"+vo.getUuid() + "_" + vo.getFile_name()))
		.forEach(p -> fileListPaths.add(p));

		log.warn("========================================");

		fileListPaths.forEach(p -> log.warn(p));

		//서버에 저장된 파일 리스트
		File targetDir = Paths.get(uploadPath, getFolderYesterDay()).toFile();

		//서버에는 존재하나 DB에 존재하지 않는 파일 리스트
		File[] removeFiles = targetDir.listFiles(file ->
					fileListPaths.contains(file.toPath()) == false);

		for(File file : removeFiles) {
			log.warn(file.getAbsolutePath());
			file.delete();
		}
	}
}
