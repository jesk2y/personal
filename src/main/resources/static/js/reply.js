async function getReplyList(bno, page){
	const result = await axios.get(`/reply/list/${bno}`, {params:{page:page}});
	
	return result
}

async function insert(formObj){
	const response = await axios({
		method : 'post',
		url: '/reply/',
		data: formObj,
		headers : {
			'Content-Type':'application/json'
		}
	})
	
	return response
}

async function remove(rno){
	await axios.delete(`/reply/${rno}`)
}