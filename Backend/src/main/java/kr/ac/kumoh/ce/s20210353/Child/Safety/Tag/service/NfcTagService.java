package kr.ac.kumoh.ce.s20210353.Child.Safety.Tag.service;

import kr.ac.kumoh.ce.s20210353.Child.Safety.Tag.entity.NfcTag;
import kr.ac.kumoh.ce.s20210353.Child.Safety.Tag.repository.NfcTagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NfcTagService {
    private final NfcTagRepository repository;

    public NfcTagService(NfcTagRepository repository) {
        this.repository = repository;
    }

    public List<NfcTag> findAll() {
        return repository.findAll();
    }

    public Optional<NfcTag> findById(NfcTag.NfcTagId id) {
        return repository.findById(id);
    }

    public NfcTag save(NfcTag tag) {
        return repository.save(tag);
    }

    public boolean deleteById(NfcTag.NfcTagId id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
} 