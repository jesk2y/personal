async function getReplyList(bno, page){
	
	const result = await axios.get(`/reply/list/${bno}`, {params:page});
	
	return result
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