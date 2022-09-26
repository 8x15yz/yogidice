<template>
    <div>
        <review-components class="review-component-base"
        :reviewdatum='reviewdatum'></review-components>
        <!-- <div
        v-for="game in reviewdatum"
        :key="game.key"
        >
        {{game.title_kr}}
        </div> -->
    </div>
</template>

<script>
import ReviewComponents from '../components/ReviewComponents.vue';

export default {
    components: {
        ReviewComponents
    },
    setup() {
        const reviewdatum =  [{'key': 1, 'title_kr': '쓰루 디 에이지스: 문명에 관한 새로운 이야기', 'thumburl': 'wys2/swf_upload/2022/02/24/1645643684643042_lg.jpg'}, {'key': 2, 'title_kr': '가이아 프로젝트', 'thumburl': 'data/boardgame_strategy/2021/03/09/1615274670-490381.jpg'}, {'key': 3, 'title_kr': '황혼의 투쟁', 'thumburl': 'data/boardgame_strategy/2021/02/03/1612344501-765004.jpg'}, {'key': 4, 'title_kr': '푸에르토 리코', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640328882-556458.jpg'}, {'key': 5, 'title_kr': '팬데믹 레거시: 시즌 1', 'thumburl': 'data/boardgame_strategy/2020/04/02/1585818479-857227.png'}, {'key': 6, 'title_kr': '7 원더스: 대결', 'thumburl': 'data/boardgame_strategy/2021/03/09/1615276187-302156.png'}, {'key': 7, 'title_kr': '아그리콜라', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640329018-317029.png'}, {'key': 8, 'title_kr': '버건디의 성', 'thumburl': 'data/boardgame_strategy/2020/04/02/1585818831-183512.jpg'}, {'key': 9, 'title_kr': '테라 미스티카 빅 박스', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640329073-893153.jpg'}, {'key': 10, 'title_kr': '브라스: 버밍엄', 'thumburl': 'wys2/swf_upload/2022/02/13/1644738474034685_lg.jpg'}, {'key': 11, 'title_kr': '안드로이드: 넷러너', 'thumburl': 'wys2/swf_upload/2022/04/01/1648822880778532_lg.jpg'}, {'key': 12, 'title_kr': '메이지 나이트: 얼티밋 에디션', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640329191-62264.jpg'}, {'key': 13, 'title_kr': '촐킨: 마야의 달력', 'thumburl': 'data/boardgame_strategy/2021/02/03/1612346336-782738.jpg'}, {'key': 14, 'title_kr': '도미니언', 'thumburl': 'data/boardgame_strategy/2021/02/03/1612344032-986124.png'}, {'key': 15, 'title_kr': '아컴 호러: 카드 게임', 'thumburl': 'wys2/swf_upload/2022/07/18/1658093520711797_lg.jpg'}, {'key': 16, 'title_kr': '버라지', 'thumburl': 'wys2/swf_upload/2022/04/26/1650902133768524_lg.jpg'}, {'key': 17, 'title_kr': '더 갤러리스트', 'thumburl': 'wys2/swf_upload/2022/01/16/1642264092333367_lg.jpg'}, {'key': 18, 'title_kr': '르아브르', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640329356-468430.png'}, {'key': 19, 'title_kr': '브래스', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640329424-901592.jpg'}, {'key': 20, 'title_kr': '테라 미스티카', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640329478-974134.jpg'}, {'key': 21, 'title_kr': '오딘을 위하여', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640329574-996804.png'}, {'key': 22, 'title_kr': '트라야누스', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640329639-798299.png'}, {'key': 23, 'title_kr': '스플렌더', 'thumburl': 'data/boardgame_strategy/2021/12/24/1640329799-345514.png'}, {'key': 24, 'title_kr': '레지스탕스: 아발론', 'thumburl': 'data/boardgame_strategy/2022/01/02/1641052726-191444.png'}, {'key': 25, 'title_kr': '티츄', 'thumburl': 'data/boardgame_strategy/2022/01/02/1641052786-66356.jpg'}]
        
        
        return {
            reviewdatum
        }
    },
}
</script>
