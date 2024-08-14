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
    public Long insertMark(MarkDTO dto) {

        if(bookRepository.existsById(dto.getBook().getIsbn())){
           return null;
        }

        bookRepository.save(dto.getBook());
        Mark mark = MarkMapper.INSTANCE.toEntity(dto);
        return markRepository.save(mark).getMno();

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

    //책이 북마크되어있는지 여부 확인
    @Override
    public boolean checkMark(String email, String isbn) {
        return markRepository.existsByMemberEmailAndBookIsbn(email, isbn);
    }
}
