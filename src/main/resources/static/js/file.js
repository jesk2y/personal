async function uploadToServer(formObj){
	const response = await axios({
		method: 'post',
		url: '/upload',
		data:formObj,
		headers: {
			'Content-Type':'multipart/form-date'
		}
	})
	
	return response.data
}

async function removeFileToServer(date, fileName){
	const response = await axios({
		method : 'delete',
		url: '/delete/'+date+'/'+fileName,
		headers:{
			'Content-Type':'String'
		}
	})
	
	return response.data
}