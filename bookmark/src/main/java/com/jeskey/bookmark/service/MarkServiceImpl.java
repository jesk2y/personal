package com.jeskey.bookmark.service;

import com.jeskey.bookmark.domain.Mark;
import com.jeskey.bookmark.dto.ListDTO;
import com.jeskey.bookmark.dto.MarkDTO;
import com.jeskey.bookmark.mapper.MarkMapper;
import com.jeskey.bookmark.repository.BookRepository;
import com.jeskey.bookmark.repository.MarkInfoRepository;
import com.jeskey.bookmark.repository.MarkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MarkServiceImpl implements MarkService{

    private final BookRepository bookRepository;
    private final MarkRepository markRepository;
    private final MarkInfoRepository markInfoRepository;


    @Override
    @Transactional
    public void insertMark(MarkDTO dto) {

        Mark getOne = markRepository.findByMemberEmailAndBookIsbn(dto.getEmail(), dto.getBook().getIsbn());

        if(getOne == null){
            //책이 Book DB에 존재하지 않을 시 책을 저장한다.
            if(!bookRepository.existsById(dto.getBook().getIsbn())){
                bookRepository.save(dto.getBook());
            }

            Mark mark = MarkMapper.INSTANCE.toEntity(dto);
            markRepository.save(mark);
        }
    }


    @Override
    @Transactional
    public void deleteMark(Long mno) {

        markRepository.deleteMarkInfosByMno(mno);   //모든 도서관 정보 삭제하기
        markRepository.deleteById(mno); //마크 삭제하기
    }

    @Override
    @Transactional
    public void changeRead(Long mno) {
        Optional<Mark> result = markRepository.findById(mno);
        Mark mark = result.orElseThrow();

        mark.changeRead();
    }

    @Override
    public MarkDTO getOne(String email, String isbn) {
        Mark mark = markRepository.findByMemberEmailAndBookIsbn(email, isbn);

        return MarkMapper.INSTANCE.toDTO(mark);
    }

    @Override
    public List<MarkDTO> getList(ListDTO listDTO) {

        List<MarkDTO> list = markRepository.getList(listDTO)
                        .stream().map(MarkMapper.INSTANCE::toDTO).toList();
        return list;
    }
}
