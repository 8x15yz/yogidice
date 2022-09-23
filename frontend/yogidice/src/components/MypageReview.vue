<template>
    <div>
        <review-components
        :reviewdatum='reviewdatum'></review-components>
        <div
        v-for="game in reviewdatum"
        :key="game.key"
        >
        {{game.title_kr}}
        </div>
    </div>
</template>

<script>
import ReviewComponents from '../components/ReviewComponents.vue';

export default {
    components: {
        ReviewComponents
    },
    setup() {
        const reviewdatum = {key: 1, title_kr: '쓰루 디 에이지스: 문명에 관한 새로운 이야기'}
        
        
        return {
            reviewdatum
        }
    },
}
</script>
