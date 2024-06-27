package com.jeskey.bookmark.service;

import com.jeskey.bookmark.config.GenericMapper;
import com.jeskey.bookmark.domain.MarkInfo;
import com.jeskey.bookmark.dto.MarkInfoDTO;
import com.jeskey.bookmark.repository.MarkInfoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarkInfoServiceImpl implements MarkInfoService {

    private MarkInfoDTO entityToDTO(MarkInfo entity){

        return GenericMapper.MARK_INFO_INSTANCE.toDto(entity);
    }

    private MarkInfo dtoToEntity(MarkInfoDTO dto){

        return GenericMapper.MARK_INFO_INSTANCE.toEntity(dto);
    }

    private final MarkInfoRepository markInfoRepository;

    @Override
    public void addMarkInfo(MarkInfoDTO dto) {

        markInfoRepository.save(dtoToEntity(dto));
    }

    @Override
    @Transactional
    public void updateMarkInfo(MarkInfoDTO dto) {
        //MarkInfo markInfo = dtoToEntity(dto); 이렇게 하면 안됨

        Optional<MarkInfo> result = markInfoRepository.findById(dto.getIno());
        MarkInfo markInfo = result.orElseThrow();

        markInfo.update(dto.getLibrary(),dto.getLocation(),dto.getType());
    }

    @Override
    public List<MarkInfoDTO> getMarkInfoList(Long mno) {
        List<MarkInfo> entityList = markInfoRepository.findAllByMarkMno(mno);

        return entityList.stream().map(this::entityToDTO).toList();
    }

/*    @Override
    public MarkInfoDTO getMarkInfo(Long ino) {

        return null;
    }*/

    @Override
    public void deleteMarkInfo(Long ino) {
        markInfoRepository.deleteById(ino);
    }
}
