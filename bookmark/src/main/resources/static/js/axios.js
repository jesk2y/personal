let _csrf = document.querySelector('meta[name="_csrf"]').getAttribute('content');
let _csrf_header = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

async function checkMark(isbn){
    const result = await axios.get(`/mark/check/${isbn}`)

    return result.data
}

async function getList(isbn){
    const result = await axios.get(`/mark/list?isbn=${isbn}`)

    return result.data
}

async function addMark(markObj, type){

    if(type == 'post'){	//북마크 등록, 도서관 등록
         await axios.post('/mark/', markObj, {
            headers: {  [_csrf_header]: _csrf }
        })
    }else if(type == 'put'){	//도서관 수정
        await axios.put('/mark/', markObj, {
            headers: {  [_csrf_header]: _csrf }
        })
    }
}

async function delAllMark(isbn){
    const result = await axios.delete(`/mark/deleteAll/${isbn}`, {
        headers: {  [_csrf_header]: _csrf }
    })

    return result.data
}


async function deleteMark(mno){
    await axios.delete(`/mark/${mno}`, {
        headers: {  [_csrf_header]: _csrf }
    })
}

//-------리스트
async function getAllList(url){
    const result = await axios.get(url)

    return result.data
}

async function searchList(url){
    result = await axios.get(url)

    return result.data
}