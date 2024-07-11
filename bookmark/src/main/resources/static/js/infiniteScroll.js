const container = document.querySelector(".row")
const listEnd = document.querySelector('#listEnd');
let currentIndex = document.querySelectorAll(".listBox").length + 1;

var page = 2;// 페이지 변수 밖으로 꺼냄


const observeIntersection = (listEnd, callback) => {
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                callback();
            }
        });
    });
    observer.observe(listEnd);
};

const callNextPage = () => {
    return () => {
        getList(page++);
    }
}

observeIntersection(listEnd, callNextPage());