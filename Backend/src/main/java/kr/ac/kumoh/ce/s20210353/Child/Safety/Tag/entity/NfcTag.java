package kr.ac.kumoh.ce.s20210353.Child.Safety.Tag.entity;

import jakarta.persistence.*;
import java.io.Serializable;

// NFC 태그 정보를 저장하는 엔티티
@Entity
@Table(name = "nfc_tag")
public class NfcTag {
    
    // 복합 키 (uid + userId)
    @EmbeddedId
    private NfcTagId id;

    // 태그에 등록된 이름
    private String name;
    
    // 연락처 전화번호
    private String phone;
    
    // 주소 정보
    private String address;
    
    // 추가 메모
    private String note;

    // 생성 시간
    @Column(name = "created_at")
    private String createdAt;

    // Getter, Setter 메서드들
    public NfcTagId getId() { return id; }
    public void setId(NfcTagId id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    // 복합 키를 정의하는 내부 클래스
    @Embeddable
    public static class NfcTagId implements Serializable {
        
        // NFC 태그의 고유 식별자
        private String uid;
        
        // 사용자 ID
        private String userId;

        // 기본 생성자
        public NfcTagId() {}
        
        // 매개변수 생성자
        public NfcTagId(String uid, String userId) {
            this.uid = uid;
            this.userId = userId;
        }
        
        // Getter, Setter
        public String getUid() { return uid; }
        public void setUid(String uid) { this.uid = uid; }
        public String getUserId() { return userId; }
        public void setUserId(String userId) { this.userId = userId; }
        
        // TODO: equals()와 hashCode() 메서드 구현 필요
    }
} 