package org.jeskey.service;

import java.util.ArrayList;
import java.util.List;

import org.jeskey.domain.File;
import org.jeskey.mapper.BoardMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

	private final BoardMapper boardMapper;

	public void saveFiles(Long bno, List<String> fileNameList) {

		if(CollectionUtils.isEmpty(fileNameList)) {
			return;
		}

		List<File> files = new ArrayList<>();

		fileNameList.forEach(fileName -> {
			//date_uuid_fileName
			String[] arr = fileName.split("_");

			File file = File.builder()
					.date(arr[0])
					.uuid(arr[1])
					.file_name(arr[2])
					.ord(files.size())
					.bno(bno)
					.build();
			files.add(file);
		});

		boardMapper.saveFiles(files);
	}

	public void clearFiles(Long bno) {

		boardMapper.clearFiles(bno);
	}
}
