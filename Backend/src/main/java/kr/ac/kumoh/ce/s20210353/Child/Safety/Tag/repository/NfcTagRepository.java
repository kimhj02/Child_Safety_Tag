package kr.ac.kumoh.ce.s20210353.Child.Safety.Tag.repository;

import kr.ac.kumoh.ce.s20210353.Child.Safety.Tag.entity.NfcTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// NFC 태그 데이터 접근을 위한 리포지토리
@Repository
public interface NfcTagRepository extends JpaRepository<NfcTag, NfcTag.NfcTagId> {
    // 기본 CRUD 메서드들은 JpaRepository에서 제공됨
    // 추가적인 쿼리 메서드가 필요하면 여기에 정의
} 