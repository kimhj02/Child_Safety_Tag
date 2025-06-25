package kr.ac.kumoh.ce.s20210353.Child.Safety.Tag.repository;

import kr.ac.kumoh.ce.s20210353.Child.Safety.Tag.entity.NfcTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NfcTagRepository extends JpaRepository<NfcTag, NfcTag.NfcTagId> {
} 