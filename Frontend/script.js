// API 기본 URL (백엔드 서버 주소)
const API_BASE_URL = 'http://localhost:8080/api/nfc-tags';

// DOM 요소들

const nfcForm = document.getElementById('nfcForm');
const nfcTableBody = document.getElementById('nfcTableBody');
const refreshBtn = document.getElementById('refreshBtn');

// 페이지 로드 시 데이터 로드
document.addEventListener('DOMContentLoaded', function() {
    loadNfcTags();
    
    // 폼 제출 이벤트
    nfcForm.addEventListener('submit', handleFormSubmit);
    
    // 새로고침 버튼 이벤트
    refreshBtn.addEventListener('click', loadNfcTags);
});

// 폼 제출 처리
async function handleFormSubmit(event) {
    event.preventDefault();
    
    const formData = new FormData(nfcForm);
    const nfcData = {
        id: {
            uid: formData.get('uid'),
            userId: formData.get('userId')
        },
        name: formData.get('name'),
        phone: formData.get('phone'),
        address: formData.get('address'),
        note: formData.get('note')
    };
    
    try {
        const response = await fetch(API_BASE_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(nfcData)
        });
        
        if (response.ok) {
            alert('태그 정보가 성공적으로 등록되었습니다!');
            nfcForm.reset();
            loadNfcTags(); // 테이블 새로고침
        } else {
            const errorData = await response.json();
            alert('등록 실패: ' + (errorData.message || '알 수 없는 오류가 발생했습니다.'));
        }
    } catch (error) {
        console.error('Error:', error);
        alert('서버 연결 오류가 발생했습니다.');
    }
}

// NFC 태그 목록 로드
async function loadNfcTags() {
    try {
        const response = await fetch(API_BASE_URL);
        
        if (response.ok) {
            const nfcTags = await response.json();
            displayNfcTags(nfcTags);
        } else {
            console.error('Failed to load NFC tags');
            nfcTableBody.innerHTML = '<tr><td colspan="8" style="text-align: center; color: red;">데이터를 불러오는데 실패했습니다.</td></tr>';
        }
    } catch (error) {
        console.error('Error:', error);
        nfcTableBody.innerHTML = '<tr><td colspan="8" style="text-align: center; color: red;">서버 연결 오류가 발생했습니다.</td></tr>';
    }
}

// NFC 태그 목록 표시
function displayNfcTags(nfcTags) {
    if (nfcTags.length === 0) {
        nfcTableBody.innerHTML = '<tr><td colspan="8" style="text-align: center;">등록된 태그가 없습니다.</td></tr>';
        return;
    }
    
    nfcTableBody.innerHTML = nfcTags.map(tag => `
        <tr>
            <td>${tag.id.uid}</td>
            <td>${tag.id.userId}</td>
            <td>${tag.name || '-'}</td>
            <td>${tag.phone || '-'}</td>
            <td>${tag.address || '-'}</td>
            <td>${tag.note || '-'}</td>
            <td>${formatDate(tag.createdAt)}</td>
            <td>
                <button class="delete-btn" onclick="deleteNfcTag('${tag.id.uid}', '${tag.id.userId}')">삭제</button>
            </td>
        </tr>
    `).join('');
}

// NFC 태그 삭제
async function deleteNfcTag(uid, userId) {
    if (!confirm('정말로 이 태그를 삭제하시겠습니까?')) {
        return;
    }
    
    try {
        const response = await fetch(`${API_BASE_URL}/${uid}/${userId}`, {
            method: 'DELETE'
        });
        
        if (response.ok) {
            alert('태그가 성공적으로 삭제되었습니다!');
            loadNfcTags(); // 테이블 새로고침
        } else {
            alert('삭제 실패: 알 수 없는 오류가 발생했습니다.');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('서버 연결 오류가 발생했습니다.');
    }
}

// 날짜 포맷팅
function formatDate(dateString) {
    if (!dateString) return '-';
    
    const date = new Date(dateString);
    return date.toLocaleString('ko-KR', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit'
    });
}

// 에러 처리 함수
function handleError(error, message = '오류가 발생했습니다.') {
    console.error('Error:', error);
    alert(message);
} 