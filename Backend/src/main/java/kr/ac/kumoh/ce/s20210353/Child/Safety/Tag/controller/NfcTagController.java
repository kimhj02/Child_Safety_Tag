package kr.ac.kumoh.ce.s20210353.Child.Safety.Tag.controller;

import kr.ac.kumoh.ce.s20210353.Child.Safety.Tag.entity.NfcTag;
import kr.ac.kumoh.ce.s20210353.Child.Safety.Tag.service.NfcTagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nfc-tags")
public class NfcTagController {
    private final NfcTagService service;

    public NfcTagController(NfcTagService service) {
        this.service = service;
    }

    @GetMapping
    public List<NfcTag> getAll() {
        return service.findAll();
    }

    @GetMapping("/{uid}/{userId}")
    public Optional<NfcTag> getById(@PathVariable String uid, @PathVariable String userId) {
        return service.findById(new NfcTag.NfcTagId(uid, userId));
    }

    @PostMapping
    public NfcTag create(@RequestBody NfcTag tag) {
        return service.save(tag);
    }

    @DeleteMapping("/{uid}/{userId}")
    public void delete(@PathVariable String uid, @PathVariable String userId) {
        service.deleteById(new NfcTag.NfcTagId(uid, userId));
    }
} 