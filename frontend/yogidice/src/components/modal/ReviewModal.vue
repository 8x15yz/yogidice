<template>
    <div class="review-modal-form">
        <div class="review-modal-form-inner">
            <div style="margin: 10px;">리뷰</div>
            <div class="rmi-displayflex">
                <div class="review-modal-inner">
                    <div >
                        <div class="rating-comment">플레이하신 {{playgame}}에 대해 리뷰를 남겨주세요!</div>
                        <div class="star-rating-wrap">
                            <div class="star-rating">
                                ★★★★★
                                <span>★★★★★</span>
                                <input type="range" v-model="star" step="1" min="0" max="10">
                            </div>
                            <div class="star-rating-point">{{star}}점</div>
                        </div>
                        
                        <textarea class="review-modal-inner-textarea"></textarea>
                    </div>
                </div>
            </div>
            <div class="rmi-displayflex">
                <div class="review-modal-bottom"> 
                    <div class="im-not-play-this-game">게임을 플레이 하지 않았습니다</div>
                    <div class="review-modal-btn-wrap">
                        <button>등록</button>
                        <button>취소</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { ref } from 'vue'
export default {
    setup() {
        const star = ref(1)
        const playgame = '클루'
        return {
            star,
            playgame
        }
    },
}
</script>


<style>
.rating-comment {
    margin-top: 15px; 
    margin-bottom: 5px; 
    font-size: 4vw;
}
.star-rating-wrap {
    display:flex; 
    justify-content: center;
}
.star-rating-point {
    margin: 10px;
    font-size: 6vw;
    color: rgb(0, 0, 0);
}
.star-rating {
    position: relative;
    font-size: 8vw;
    color: #ddd;
    margin-bottom: 15px; 
  }
  
  .star-rating > input {
    width: 100%;
    height: 100%;
    position: absolute;
    left: 0;
    opacity: 0;
    cursor: pointer;
  }
  
  .star-rating > span {
    width: calc(v-bind ('star') * 10%);
    /* width:  50%; */
    position: absolute; 
    left: 0;
    color: rgb(255, 239, 19);
    overflow: hidden;
    pointer-events: none;
  }
.review-modal-form {
    width: 85vw;
    height: 55vh;
    background-color: white;    
    border-radius: 10px;
}
.review-modal-bottom {
    /* background-color: greenyellow; */
    width: 75vw;
    height: 10vh;
}
.review-modal-inner {
    /* background-color: pink; */
    height: 38vh;
    width: 75vw;
    border-top: 1px solid rgb(121, 121, 121);
    
}
.im-not-play-this-game {
    font-size: 1vw;
    text-align: end;
}
.rmi-displayflex {
    display: flex;
    justify-content: center;
}
.review-modal-inner-textarea {
    height: 20vh;
    width: 75vw;
    border: none;
    border-radius: 5px;
    background-color: rgb(216, 216, 216);
}
.review-modal-inner-textarea:focus {
    outline: solid 2px rgb(199, 199, 199);
}
.review-modal-btn-wrap {
    display: flex;
    justify-content: end;
    margin: 20px;
}
</style>