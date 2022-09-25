<template>
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
</template>

<script>
export default {

}
</script>

<style>
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
</style>