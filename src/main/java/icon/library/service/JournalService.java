package icon.library.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import icon.library.dto.JournalRequestDto;
import icon.library.entity.Journal;
import icon.library.repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JournalService {

    private final JournalRepository journalRepository;
    public Journal save(JournalRequestDto journalRequestDto) throws JsonProcessingException {
        return journalRepository.save(convertRequest(journalRequestDto));
    }

    public Journal update(String id, JournalRequestDto journalRequestDto){
        Journal journal = journalRepository.findById(id).orElseThrow();
        journal.setTitle(journalRequestDto.getTitle());
        journal.setAuthor(journalRequestDto.getAuthor());
        journal.setPublishedYear(journalRequestDto.getPublishedYear());
        journal.setRoomLocation(journalRequestDto.getRoomLocation());
        journal.setCabinetLocation(journalRequestDto.getCabinetLocation());
        journal.setShelfLocation(journalRequestDto.getShelfLocation());
        return journalRepository.save(journal);
    }

    public void delete(String id){
        Journal journal = journalRepository.findById(id).orElseThrow();
        journalRepository.delete(journal);
    }

    private Journal convertRequest(JournalRequestDto journalRequestDto) throws JsonProcessingException {
        String journalString = new ObjectMapper().writeValueAsString(journalRequestDto);
        return new ObjectMapper().readValue(journalString, Journal.class);
    }
}
