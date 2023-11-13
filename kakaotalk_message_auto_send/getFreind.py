from PyKakao import Message

# 메시지 API 인스턴스 생성
MSG = Message(service_key = "421b9abac8ea3360f9c7030a1c79234a")

# 카카오 인증코드 발급 URL 생성
auth_url = MSG.get_url_for_generating_code()
print(auth_url)

# 카카오 인증코드 발급 URL 접속 후 리다이렉트된 URL
url = "https://localhost:5000/?code=tYrQqXS0w5lCO0kaK4MW-wzPHgaPxeBAyY8IurAy4j1vjifIs9T5JWi5EPVTk8abBPvNpwo9cxgAAAGJB7v3Sw"

# 위 URL로 액세스 토큰 추출
access_token = MSG.get_access_token_by_redirected_url(url)

# 액세스 토큰 설정
MSG.set_access_token(access_token)

# 텍스트 메시지 전송
text = "텍스트 영역입니다. 최대 200자 표시 가능합니다."
link = {
            "web_url": "https://developers.kakao.com",
            "mobile_web_url": "https://developers.kakao.com"
        }
button_title = "바로 확인"

MSG.send_text(text=text, link={}, button_title=button_title)