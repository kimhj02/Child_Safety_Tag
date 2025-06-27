package kr.ac.kumoh.ce.s20210353.Child.Safety.Tag.controller;

import kr.ac.kumoh.ce.s20210353.Child.Safety.Tag.entity.NfcTag;
import kr.ac.kumoh.ce.s20210353.Child.Safety.Tag.service.NfcTagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
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
    public Optional<NfcTag> getById(@PathVariable("uid") String uid, @PathVariable("userId") String userId) {
        return service.findById(new NfcTag.NfcTagId(uid, userId));
    }

    @PostMapping
    public NfcTag create(@RequestBody NfcTag tag) {
        return service.save(tag);
    }

    @DeleteMapping("/{uid}/{userId}")
    public ResponseEntity<Void> delete(@PathVariable("uid") String uid, @PathVariable("userId") String userId) {
        boolean deleted = service.deleteById(new NfcTag.NfcTagId(uid, userId));
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/uid/{uid}")
    public ResponseEntity<NfcTag> getByUid(@PathVariable("uid") String uid) {
        Optional<NfcTag> tag = service.findByUid(uid);
        return tag.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}