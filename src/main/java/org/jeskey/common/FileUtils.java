package org.jeskey.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jeskey.dto.FileDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;

@Component
public class FileUtils {

	@Value("${org.jeskey.upload.path}")
	private String uploadPath;

	//이미지 업로드
	public FileDTO uploadFile(MultipartFile multipartFile) {

		if(multipartFile.isEmpty()) {
			return null;
		}

		String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toString();
		String uuid = UUID.randomUUID().toString();

		String fileName = multipartFile.getOriginalFilename();
		String saveName = uuid+"_"+fileName;

	    String savePath = makeDirectory(uploadPath + File.separator + today);

		Path path = Paths.get(savePath, saveName);

		try {
			multipartFile.transferTo(path);	//파일 저장

			//섬네일 생성
			if(Files.probeContentType(path).startsWith("image")){

				File thumbFile = new File(savePath, "s_"+ path.getFileName());
				Thumbnailator.createThumbnail(path.toFile(), thumbFile, 200, 200);
			}

		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		FileDTO dto = FileDTO.builder()
						.uuid(uuid)
						.fileName(fileName)
						.date(today).build();

		return dto;
	}

	//이미지 리스트 업로드
	public List<FileDTO> uploadFiles(List<MultipartFile> multipartFiles){

		List<FileDTO> files = new ArrayList<>();

		for(MultipartFile multipartFile : multipartFiles) {
			if(multipartFile.isEmpty()) {
				continue;
			}
			files.add(uploadFile(multipartFile));
		}

		return files;
	}

	//파일 삭제
	public void deleteFile(String date, String fileName){

		Path path = Paths.get(uploadPath, date, fileName);

		File file = new File(path.toString());

		try {
			file.delete();

			if(Files.probeContentType(path).startsWith("image")){
				deleteThumbnail(date, fileName);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//파일들 삭제
	public void deleteFiles(List<FileDTO> files) {

		for(FileDTO file : files) {
			deleteFile(file.getDate(),
					file.getUuid() + "_" + file.getFileName());
		}
	}

	//섬네일 이미지 삭제
	public void deleteThumbnail(String date, String fileName) {

		String thumbnailPath = Paths.get(uploadPath, date, "s"+"_"+fileName).toString();
		File thumbnailFile = new File(thumbnailPath);

		if(thumbnailFile.exists()) {
			thumbnailFile.delete();
		}
	}

	//디렉터리 생성
	private String makeDirectory(String filePath) {
		File dir = new File(filePath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		return dir.getPath();
	}

	//이미지 화면 출력용 Path 리턴
	public String getPath(String date, String fileName) {

		return Paths.get(uploadPath, date, fileName).toString();
	}
}
