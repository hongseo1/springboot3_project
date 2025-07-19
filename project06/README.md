## Spring Boot3 Project (게시판, 검색기능)

### 로그인 화면
<img width="1920" height="1165" alt="KakaoTalk_20250714_191720162_03" src="https://github.com/user-attachments/assets/e35bb924-132b-4d4d-aeb1-7644cc74c2a3" />

<br/> 게시판 서비스 이용을 위한 로그인 페이지입니다.<br/>
기본적인 설정으로 회원가입 시 Role.USER로 일반 사용자 권한을 자동으로 부여합니다.<br/>
이 이외에도 Role.ADMIN으로 설정 시에 관리자 계정으로 생성됩니다.

----------
### 게시판 화면
<img width="1920" height="1636" alt="KakaoTalk_20250714_191720162_06" src="https://github.com/user-attachments/assets/56728480-8a44-487a-8590-d68ceca07da4" />

<br/> 등록된 게시글을 확인할 수 있는 페이지입니다.

----------
### 게시판 내 검색 화면
<img width="1920" height="1264" alt="KakaoTalk_20250714_191720162_04" src="https://github.com/user-attachments/assets/76b7f1bc-012a-45ba-a020-4548303e221c" />

<br/> 게시글 상단에 위치한 검색바로 원하는 게시글을 쉽게 찾을 수 있습니다.

----------
### 게시판 상세 화면
<img width="1920" height="1154" alt="KakaoTalk_20250714_191720162_01" src="https://github.com/user-attachments/assets/eaf2e3d2-4f02-4ee4-b074-a692261d29e6" />

<br/> 관리자 계정으로 로그인 시 보여지는 게시글 상세 페이지 입니다. <br/> 
일반 사용자(USER): 본인이 작성한 게시글에 한하여 상세 보기 화면에서 수정 및 삭제 버튼이 활성화됩니다. <br/> 
관리자(ADMIN): 모든 게시글에 대해 수정 및 삭제 권한을 가지며, 상세 보기 화면에서 관련 버튼이 항상 활성화됩니다.
