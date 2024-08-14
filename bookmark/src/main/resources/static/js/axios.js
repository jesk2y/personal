/*
let _csrf = document.querySelector('meta[name="_csrf"]').getAttribute('content');
let _csrf_header = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');
*/

async function checkMark(isbn){
    const result = await axios.get(`/mark/check/${isbn}`)

    return result.data
}

async function getInfoList(mno){
    const result = await axios.get(`/info/list/${mno}`)

    return result.data
}

async function addMark(markObj, type){
//북마크 등록
const result = await axios.post('/mark/', markObj/*, {
        headers: {  [_csrf_header]: _csrf }
}*/)
    return result.data //등록만 마크 번호(mno)
}

async function modifyMarkInfo(markInfoObj){
    await axios.put('/markInfo/', markObj/*, {
            headers: {  [_csrf_header]: _csrf }
    }*/)
}

async function delAllMark(isbn){
    const result = await axios.delete(`/mark/deleteAll/${isbn}`/*, {
        headers: {  [_csrf_header]: _csrf }
    }*/)

    return result.data
}


async function deleteMark(mno){
    await axios.delete(`/mark/${mno}`/*, {
        headers: {  [_csrf_header]: _csrf }
    }*/)
}

async function getOne(isbn){
    const result = await axios.get(`/content?isbn=${isbn}`)

    return result.data
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