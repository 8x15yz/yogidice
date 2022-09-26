<template>
  <div class="review-box">
    <div class="rating-total">
      <div>
        <div class="text-huge">3.5</div>
        <div class="text-caption">210명 참여</div>
      </div>
      <div class="count-rating">
        <div class="rating-graph">
          <span class="rating">★1</span>
          <span class="review-progress">
            <span class="review-progress-value"></span>
          </span>
        </div>
        <div class="rating-graph">
          <span class="rating">★2</span>
          <span class="review-progress">
            <span class="review-progress-value"></span>
          </span>
        </div>
        <div class="rating-graph">
          <span class="rating">★3</span>
          <span class="review-progress">
            <span class="review-progress-value"></span>
          </span>
        </div>
        <div class="rating-graph">
          <span class="rating">★4</span>
          <span class="review-progress">
            <span class="review-progress-value"></span>
          </span>
        </div>
        <div class="rating-graph">
          <span class="rating">★5</span>
          <span class="review-progress">
            <span class="review-progress-value"></span>
          </span>
        </div>
      </div>
    </div>


    <!-- 리뷰 컴포넌트 들어갈 곳 -->
    <div class="game-review-bottom-container">
      <review-components :reviewdatum='reviewdatum'></review-components>
    </div>
    <!-- 리뷰 컴포넌트 들어갈 곳 -->
  </div>
</template>

<script>
import { onMounted } from "@vue/runtime-core";
import ReviewComponents from '../card/ReviewComponents.vue';

export default {
  components: {
    ReviewComponents
  },
  setup() {
        onMounted(() => console.log("안녕"));
        const reviewdatum =  [{'user': '아기공룡둘째', 'rating': 4, 'review' : '깔끔한 룰과 적당한 메커니즘 요소들의 조화로 어느정도의 재미는 누구에게나 보편적으로 보장되는듯 하다. 그러나 그 이상의 특별함이나 테마가 확 와닿는건 없었다', 'create_date' : '2022-09-18'}, {'user': '토리', 'rating': 6, 'review' : '덱게임을 좋아하는 분이라면 추천. 도미니언 이후로 최고임', 'create_date' : '2022-09-13' }, {'user': '아빠와아들' ,'rating': 4, 'review' : '벌써 3주째 주말마다 하고 있습니다. 꿀잼', 'create_date' : '2022-05-07'}, {'user': '행성파괴범' ,'rating': 7, 'review' :  '그냥 테마와 메커니즘의 결합도가 정말 아름다운 게임입니다. 호텔에 손님 받고 보너스 콤보 효과를 받는 점이 너무 좋고요! 적절한 운요소도 게임을 가볍게 해서 좋습니다 +_+ 그리고 가볍고 캐릭터들의 뽕맛도 너무 마음에 듭니다. 2인 베스트인게 아쉽지만 왈츠 확장으로 해결 되었다니 너무 기대 됩니다! 커플 유로 게임으로 가장 추천드리고 싶습니다.', 'create_date' : '2022-04-07' }]
        return {
            reviewdatum
        }
    },
}
</script>

<style>
.review-box {
  width: 100vw;
}
.rating-total {
  display: flex;
  width: 80vw;
  height: 10vh;
  margin: 10vh 10vw 2vh 10vw;
  box-shadow: 4px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 4px;  
  background-color: white;
}
.count-rating {
  display: flex;
  flex-direction: column;
  height: 10vh;
  margin-top: 2px;
  gap:2px;
}
.count-rating span {
  font-family: "Pretendard";
  font-style: normal;
  font-weight: 400;
  font-size: 8px;
  text-align: left;
}
.review-progress {
  background: var(--color-grey-3);
  justify-content: flex-start;
  align-items: center;
  position: relative;
  display: flex;
  height: 100%;
  width: 40vw;
  align-self: center;
}
.review-progress-value {
  animation: loading 3s normal forwards;
  background: linear-gradient(to right, #FFEEEE 0%, #FF6E6E 100%);
  height: 100%;
  width: 100%
}
.rating-graph {
  display: flex;
  gap: 2px;
}
@keyframes loading {
  0% { width: 0; }
  100% { width: 60%; }
}
.game-review-bottom-container {
    display: flex;
    justify-content: center;
    align-items: center;
    /* height: 43vh; */
    overflow: hidden;
}

</style>