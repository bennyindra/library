package icon.library.service;

import icon.library.dto.BorrowedBookDto;
import icon.library.dto.BorrowedBookRequestDto;
import icon.library.entity.BorrowedBook;
import icon.library.entity.Member;
import icon.library.enums.BorrowingMemberStatus;
import icon.library.enums.BorrowingStatus;
import icon.library.repository.BookRepository;
import icon.library.repository.BorrowedBookRepository;
import icon.library.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowedBookService {

    private final BorrowedBookRepository borrowedBookRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public List<BorrowedBook> booking(BorrowedBookRequestDto borrowedBookRequestDto){
        Member member = memberRepository.findById(borrowedBookRequestDto.getMemberId()).orElseThrow();
        List<BorrowedBook> list = new ArrayList<>();
        int totalBookTaken = 0;
        for (BorrowedBookDto borrowedBookDto : borrowedBookRequestDto.getBorrowedBookDtoList()){
            BorrowedBook borrowedBook = new BorrowedBook();
            borrowedBook.setBook(bookRepository.findById(borrowedBookDto.getBookId()).orElseThrow());
            borrowedBook.setMember(member);
            borrowedBook.setFrom(borrowedBookDto.getFrom() == null ? LocalDate.now(): borrowedBookDto.getFrom());
            borrowedBook.setTo(borrowedBookDto.getTo() == null ? LocalDate.now(): borrowedBookDto.getTo());
            borrowedBook.setTotal(borrowedBook.getTotal());
            borrowedBook.setStatus(BorrowingStatus.BORROWED);
            totalBookTaken += borrowedBook.getTotal();
            list.add(borrowedBook);
        }
        member.setStatus(BorrowingMemberStatus.ON_BORROWING);
        member.setTotalBookTaken(member.getTotalBookTaken() + totalBookTaken);
        memberRepository.save(member);
        return borrowedBookRepository.saveAll(list);
    }

    @Transactional
    public List<BorrowedBook> returned(BorrowedBookRequestDto borrowedBookRequestDto){
        Member member = memberRepository.findById(borrowedBookRequestDto.getMemberId()).orElseThrow();
        List<BorrowedBook> list = new ArrayList<>();
        int totalBookReturned = 0;
        for (BorrowedBookDto borrowedBookDto : borrowedBookRequestDto.getBorrowedBookDtoList()){
            BorrowedBook borrowedBook = borrowedBookRepository.findById(borrowedBookDto.getId()).orElseThrow();
            if (!member.getId().equals(borrowedBook.getMember().getId())){
                continue;
            }
            borrowedBook.setStatus(BorrowingStatus.RETURNED);
            totalBookReturned += borrowedBook.getTotal();
            list.add(borrowedBook);
        }

        list = borrowedBookRepository.saveAll(list);

        if (member.getTotalBookTaken() == totalBookReturned){
            member.setStatus(BorrowingMemberStatus.NOT_BORROWING);
            member.setTotalBookTaken(0);
        } else if(member.getTotalBookTaken() > totalBookReturned)  {
            member.setTotalBookTaken(member.getTotalBookTaken() - totalBookReturned);
            List<BorrowedBook> listBorrowedBook = borrowedBookRepository.findAllByMemberIdAndStatus(borrowedBookRequestDto.getMemberId(), BorrowingStatus.BORROWED);
            Optional<BorrowedBook> optionalLongestBorrowedBook = listBorrowedBook.stream().max(Comparator.comparing(BorrowedBook::getTo));
            if (optionalLongestBorrowedBook.isPresent()){
                BorrowedBook longestBookBorrowed = optionalLongestBorrowedBook.get();
                if (longestBookBorrowed.getTo().isAfter(LocalDate.now())){
                    member.setStatus(BorrowingMemberStatus.LATE_RETURNING);
                }
            }
        }
        memberRepository.save(member);

        return list;
    }

}
