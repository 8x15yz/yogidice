<template>
    <div class="review-components-overflow">
        <!-- 데이터 없을때 -->
        <div v-if="reviewdatum.length == 0" class="review-components-msg">
            <div>
                <div>남긴 리뷰가 없습니다.</div>
                <div>리뷰를 남겨보세요! 😄</div>
            </div>
        </div>
        <!-- 데이터 없을때 -->
        

        <!-- 데이터 들어오면 -->
        <review-components class="review-components-overflow"
        :reviewdatum='reviewdatum'
         @OpenReviewModal="OpenReviewModal"
        ></review-components>
        <!-- 데이터 들어오면 -->
    </div>
</template>

<script>
import ReviewComponents from './card/ReviewComponents.vue';
import { onMounted, reactive } from "@vue/runtime-core";
import { getCurrentInstance } from "vue";
import axios from 'axios'
import { api } from '@/api/api'

export default {
    components: {
        ReviewComponents
    },
    emits:[
    'OpenReviewModal',
    ],
    setup() {
        console.log(api().get())
        const { emit } = getCurrentInstance();
        onMounted(() => console.log("안녕"));
        // const reviewdatum =  [{'bg_title': '아르낙의 잊혀진 유적( 2020 )', 'rating': 4, 'review' : '깔끔한 룰과 적당한 메커니즘 요소들의 조화로 어느정도의 재미는 누구에게나 보편적으로 보장되는듯 하다. 그러나 그 이상의 특별함이나 테마가 확 와닿는건 없었다', 'create_date' : '2022-09-18'}, {'bg_title': '마블 챔피언스: 카드 게임 코어 세트( 2019 )', 'rating': 6, 'review' : '덱게임을 좋아하는 분이라면 추천. 도미니언 이후로 최고임', 'create_date' : '2022-09-13' }, {'bg_title': '아크 노바(2021)' ,'rating': 4, 'review' : '벌써 3주째 주말마다 하고 있습니다. 꿀잼', 'create_date' : '2022-05-07'}, {'bg_title': '그랜드 오스트리아 호텔' ,'rating': 7, 'review' :  '그냥 테마와 메커니즘의 결합도가 정말 아름다운 게임입니다. 호텔에 손님 받고 보너스 콤보 효과를 받는 점이 너무 좋고요! 적절한 운요소도 게임을 가볍게 해서 좋습니다 +_+ 그리고 가볍고 캐릭터들의 뽕맛도 너무 마음에 듭니다. 2인 베스트인게 아쉽지만 왈츠 확장으로 해결 되었다니 너무 기대 됩니다! 커플 유로 게임으로 가장 추천드리고 싶습니다.', 'create_date' : '2022-04-07' }]
        const reviewdatum =  reactive([])
        axios.get("https://j7b206.p.ssafy.io/api/users/history", {
            headers: {
                Authorization: `Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNjY0NDE0NTY2LCJleHAiOjE2NjU3MTA1NjZ9.bB21Sm7fGkA6VcxmZR0uEEVm4t-L-visG0TQZ-HO9D0`
            }
        })
        .then(res => {
            console.log(res.data.responses)
            for (let rdr in res.data.responses) {
                console.log('이거', rdr)
                let reviewuser = new Object();
                reviewuser.bg_title = res.data.responses[rdr].gameTitle
                reviewuser.rating = res.data.responses[rdr].rating
                reviewuser.create_date = res.data.responses[rdr].createDate
                reviewuser.review = res.data.responses[rdr].review
                reviewdatum.push(reviewuser)
            }
            console.log('dlrj', reviewdatum)
        })
        .catch(err => {
            console.log(err)
        })
        console.log(reviewdatum)
        const OpenReviewModal = function() {
            emit("OpenReviewModal");
        }
        return {
            reviewdatum,
            OpenReviewModal
        }
    },
}
</script>

<style>
.review-components-overflow {
    align-items: center;
    overflow: auto;
    height: 50vh;
    width: 90vw;
    /* background-color: pink; */
}
.review-components-overflow::-webkit-scrollbar{width: 0px;}
.review-components-msg {
    font-size: 20px;
    font-weight: bold;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 90vw;
    height: 90vw;
    background-color: rgba(255, 255, 255, 0.823);
}
</style>
