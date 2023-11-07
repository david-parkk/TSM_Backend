

// 구글 OAuth2 인증 URL
var authUrl = "https://accounts.google.com/o/oauth2/auth/oauthchooseaccount?client_id=250111845113-f0romlsa9803m32cg4lonk6p29j1n6d6.apps.googleusercontent.com&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Flogin%2Foauth2%2Fcode%2Fgoogle&response_type=code&scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email%20https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile&service=lso&o2v=1&theme=glif&flowName=GeneralOAuthFlow";

// 구글 OAuth2 인증 페이지를 열기 위한 팝업 윈도우 옵션
var popupOptions = "width=800,height=600";

// 구글 OAuth2 인증 페이지를 열고 인증 코드를 받는 함수
function loginWithGoogle() {

  // 팝업 윈도우를 열고 authUrl로 이동
//  var popup = window.open(authUrl, "Google Login", null);
   location.href=authUrl;

  // 팝업 윈도우의 URL이 변경될 때마다 실행되는 이벤트 리스너
  window.addEventListener("message", function(event) {
    // 팝업 윈도우가 보낸 메시지가 인증 코드인 경우
    if (event.data && event.data.code) {
      // 인증 코드를 콘솔에 출력
      console.log("Authorization code: " + event.data.code);
      // 팝업 윈도우를 닫음
//      popup.close();
    }
  });
}

// ".login-with-google" 클래스 이름을 가진 <div>를 찾음
var loginDiv = document.querySelector(".login-with-google");

// <div>를 클릭하면 loginWithGoogle 함수를 실행하도록 이벤트 리스너 추가
loginDiv.addEventListener("click", loginWithGoogle);

