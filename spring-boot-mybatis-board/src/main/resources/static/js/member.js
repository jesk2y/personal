
async function checkLogin(formObj){
	const result = await axios({
		method : 'post',
		url: '/checkLogin',
		data: formObj,
		headers : {
			'Content-Type':'application/json'
		}
	})
	
	return result.data
}

async function checkId(user_id){
	const result = await axios.get(`/checkId/${user_id}`);
	
	return result.data
}

async function insert(formObj){
	const response = await axios({
		method : 'post',
		url: '/signup',
		data: formObj,
		headers : {
			'Content-Type':'application/json'
		}
	})
	
	return response.data
}

