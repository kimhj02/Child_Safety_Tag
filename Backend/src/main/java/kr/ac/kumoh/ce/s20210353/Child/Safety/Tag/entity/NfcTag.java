package kr.ac.kumoh.ce.s20210353.Child.Safety.Tag.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "nfc_tag")
public class NfcTag {
    @EmbeddedId
    private NfcTagId id;

    private String name;
    private String phone;
    private String address;
    private String note;

    @Column(name = "created_at")
    private String createdAt;

    // Getter, Setter
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

    @Embeddable
    public static class NfcTagId implements Serializable {
        private String uid;
        private String userId;

        public NfcTagId() {}
        public NfcTagId(String uid, String userId) {
            this.uid = uid;
            this.userId = userId;
        }
        // Getter, Setter
        public String getUid() { return uid; }
        public void setUid(String uid) { this.uid = uid; }
        public String getUserId() { return userId; }
        public void setUserId(String userId) { this.userId = userId; }
    }
} 