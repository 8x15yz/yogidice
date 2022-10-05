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
      <div>
          <div v-for="data in datum" :key="data.key">
              <div v-if="data.review" id="review-component-base">
                  <div class="review-title-rating-wrapper">
                      <div class="review-rating review-title " @click="showDetail(data)">
                          {{data.nickName}}
                      </div>
                      <div class="rating review-rating">
                          ★{{data.rating}} 
                      </div>
                      <div v-if="isuser" class="icon-padding" @click="$emit('OpenReviewModal', [data.id, data.gameTitle])">
                          <i class="far fa-edit review-rating" ></i>
                      </div>
                  </div>
                  <div class="review-create-date">
                      {{data.createDate}}
                  </div>
                  <div class="review-content-text-overflow">
                      {{data.review}}
                  </div>
              </div>
          </div>
          <div style="height: 100px;"></div>
      </div>
    </div>
    <!-- 리뷰 컴포넌트 들어갈 곳 -->
  </div>
</template>

<script>
import axios from 'axios'
import { onMounted, ref } from "@vue/runtime-core";
// import ReviewComponents from '../card/ReviewComponents.vue';
import { useRoute } from "vue-router"

export default {
  components: {
    // ReviewComponents
  },
  setup() {
    const route = useRoute()
    let gameId = route.query.gameId
    const datum =  ref([])
        onMounted(() => {
          axios({
                    url: `https://yogidice.site/api/games/reviewAll/${gameId}`,
                    method: "get",
                  })
                  .then((res) => {
                    console.log(res.data.responses)
                    datum.value = res.data.responses
                  })
                  .catch((err) => {console.log(err)})
        });
          
        return {
            datum
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