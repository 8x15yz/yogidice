<template>
    <div class="review-modal-form">
        <div style="margin: 10px;">리뷰</div>
        <div>
            <div class="review-modal-inner">
                <div class="rmi-inner">
                    <div style="margin: 10px;">플레이하신 게임에 대해 리뷰를 납ㅁ겨주세요!!</div>
                    <div style="margin: 10px;">💛💛💛💛💛 <span>5점</span></div>
                    <textarea class="review-modal-inner-textarea"></textarea>
                </div>
            </div>
        </div>
        <div class="review-modal-btn-wrap">
            <button>등록</button>
            <button>취소</button>
        </div>
    </div>
</template>

<style>
.review-modal-form {
    width: 70vw;
    height: 45vh;
    background-color: white;    
    padding: 20px;
    border-radius: 10px;
}
.review-modal-inner {
    /* background-color: pink; */
    height: 35vh;
    width: 70vw;
    border-top: 1px solid rgb(121, 121, 121);
}
.rmi-inner {
    margin: 20px;
}
.review-modal-inner-textarea {
    height: 20vh;
    width: 60vw;
}
.review-modal-btn-wrap {
    display: flex;
    justify-content: end;
    margin: 20px;
}
</style>