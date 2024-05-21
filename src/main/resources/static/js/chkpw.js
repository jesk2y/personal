/*
	비밀번호 input 태그의 클래스는 모두 password 포함되있어야함
	메인 input 태그의 id는 #password, 일치 확인 input 태그의 id는 #password-second
	비밀번호 검증 및 일치 여부를 알려우는 알림태그 p의 믈래스는 pw-message 포함

 */
	const pw1El = document.querySelector("#password")
	const pw2El = document.querySelector("#password-second")
	
	//비밀번호 확인
	document.querySelectorAll(".password").forEach(i => i.addEventListener("blur", function(e){
		pwStatus = false
		
		const message = document.querySelector(".pw-message")
		
		//비밀번호 입력 안할시
		if(pw1El.value.length == 0 || pw2El.value.length == 0 ){
			return
		}
	
		message.style.display = 'block'
		
		//비밀번호 일치 검사
		if(pw1El.value == pw2El.value){
			message.innerText = '비밀번호가 일치합니다'
			
		}else{
			message.innerText = '비밀번호가 일치하지 않습니다'
			return
		}
	
		//비밀번호 조건 검사
		if(!isPassword(pw1El.value)){
			message.innerText = '8 ~ 16자 영문, 숫자, 특수문자를 최소 한가지씩 조합해주세요'
			return
		}
		
		message.innerText = '사용 가능합니다'
		pwStatus = true
	}))
	
	//정규식 검사
	function isId(asValue) {
		//영문자로 시작하는 영문자 또는 숫자 6~20자 
		var regExp = /^[a-z]+[a-z0-9]{5,19}$/g;
		return regExp.test(asValue);
	}
	
	function isPassword(asValue) {
		//8 ~ 16자 영문, 숫자, 특수문자를 최소 한가지씩 조합
		var regExp = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/;
		return regExp.test(asValue);
	}
	
	function isEmail(asValue) {
		var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		return regExp.test(asValue);
	}
	