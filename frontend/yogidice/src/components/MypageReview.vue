<template>
    <div class="review-components-overflow">
        <review-components class="review-components-overflow"
        :reviewdatum='reviewdatum'></review-components>
    </div>
</template>

<script>
import ReviewComponents from '../components/ReviewComponents.vue';
import { onMounted } from "@vue/runtime-core";

export default {
    components: {
        ReviewComponents
    },
    setup() {
        onMounted(() => console.log("안녕"));
        const reviewdatum =  [{'bg_title': '아르낙의 잊혀진 유적( 2020 )', 'rating': 4, 'review' : '깔끔한 룰과 적당한 메커니즘 요소들의 조화로 어느정도의 재미는 누구에게나 보편적으로 보장되는듯 하다. 그러나 그 이상의 특별함이나 테마가 확 와닿는건 없었다', 'create_date' : '2022-09-18'}, {'bg_title': '마블 챔피언스: 카드 게임 코어 세트( 2019 )', 'rating': 6, 'review' : '덱게임을 좋아하는 분이라면 추천. 도미니언 이후로 최고임', 'create_date' : '2022-09-13' }, {'bg_title': '아크 노바(2021)' ,'rating': 4, 'review' : '벌써 3주째 주말마다 하고 있습니다. 꿀잼', 'create_date' : '2022-05-07'}, {'bg_title': '그랜드 오스트리아 호텔' ,'rating': 7, 'review' :  '그냥 테마와 메커니즘의 결합도가 정말 아름다운 게임입니다. 호텔에 손님 받고 보너스 콤보 효과를 받는 점이 너무 좋고요! 적절한 운요소도 게임을 가볍게 해서 좋습니다 +_+ 그리고 가볍고 캐릭터들의 뽕맛도 너무 마음에 듭니다. 2인 베스트인게 아쉽지만 왈츠 확장으로 해결 되었다니 너무 기대 됩니다! 커플 유로 게임으로 가장 추천드리고 싶습니다.', 'create_date' : '2022-04-07' }]
        return {
            reviewdatum
        }
    },
}
</script>

<style>
.review-components-overflow {
    overflow: auto;
    height: 50vh;
}

</style>
