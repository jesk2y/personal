package org.jeskey.service;

import java.util.ArrayList;
import java.util.List;

import org.jeskey.domain.File;
import org.jeskey.mapper.FileMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

	private final FileMapper fileMapper;

	public void saveFiles(long bno, List<String> fileNameList) {

		if(CollectionUtils.isEmpty(fileNameList)) {
			return;
		}

		List<File> files = new ArrayList<>();

		fileNameList.forEach(fileName -> {

			String[] arr = fileName.split("_");

			File file = File.builder()
					.uuid(arr[0])
					.file_name(arr[1])
					.date(arr[2])
					.ord(files.size())
					.bno(bno)
					.build();

			files.add(file);
		});

		fileMapper.saveFiles(files);
	}
}
