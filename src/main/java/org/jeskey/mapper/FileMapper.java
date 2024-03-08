package org.jeskey.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.jeskey.domain.File;

@Mapper
public interface FileMapper {

	int saveFiles(List<File> files);

	int clearFiles(Long bno);
}
