const profileUsername = document.querySelector(".profile-username");
const passwordInputs = document.querySelectorAll(".password-input");
const submitBtn = document.querySelector(".submit-btn");

let usercode = 0;

let principal = getPrincipal();

load();

function load() {
	profileUsername.textContent = principal.username;
}

submitBtn.onclick = () => {
	
	/*$.ajax({
		type: "put",
		url: "/app/profile/account/update",
		data: JSON.stringify(account),
		contentType: "application/json; charset=utf-8",
		dataType: "text",
		success: function(data){
			if(data == "true"){
				alert("회원정보 수정 완료.");
				location.replace("/app/profile/account");
			}else {
				alert("이미 존재하는 사용자 이름입니다.");
			}
		},
		error: function(){
			alert("비동기 처리 오류");
		}
		
	});*/
}








