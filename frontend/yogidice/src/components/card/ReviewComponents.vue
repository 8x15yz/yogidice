<template>
<!-- 이거 나중에 없앨거임 내가쓴댓을인지 확인하기 위함 => 내가쓴댓글이면 수정할 수 있게 -->
<!-- <button @click="isuser = !isuser" style="position:absolute;">이거</button> -->
<!-- 이거 나중에 없앨거임 내가쓴댓을인지 확인하기 위함 => 내가쓴댓글이면 수정할 수 있게 -->
    <div>
        <div v-for="data in datum" :key="data.key" id="review-component-base">
            <div class="review-title-rating-wrapper">
                <div class="review-rating review-title " >
                    {{data.bg_title}}
                    {{data.user}}
                </div>
                <div class="rating review-rating">
                    ★{{data.rating}} 
                </div>
                <div v-if="isuser" class="icon-padding" @click="$emit('OpenReviewModal')">
                    <i class="far fa-edit review-rating" ></i>
                </div>
            </div>
            <div class="review-create-date">
                {{data.create_date}}
            </div>
            <div class="review-content-text-overflow">
                {{data.review}}
            </div>
        </div>
        <div style="height: 100px;"></div>
    </div>
</template>

<script>
import { reactive, ref } from 'vue'

export default{
    props: {
        reviewdatum: Object
    },
    emits:[
        'OpenReviewModal',
    ],
    setup(props) {
        const isuser = ref(true)
        const datum = reactive(props.reviewdatum)
        console.log('여기서는' , datum)

        return {
            isuser,
            datum,
        }
    },
}
</script>

<style>
#review-component-base {
    width: 82vw;
    background-color: white;
    height: auto;
    border-radius: 5px;
    box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
    margin-bottom: 10px;
    padding: 10px;
    border: solid 1px rgb(197, 197, 197);
    margin-left: 5px;
}
.review-title-rating-wrapper {
    display: flex;
    /* background-color: rgb(253, 232, 236); */
    align-items: center;
}
.review-content-text-overflow {
    /* overflow: hidden; */
    -webkit-line-clamp: 3;
    width: 83vw;
    /* background-color: pink; */
    margin-bottom: 10px;
}
.rating {
  align-self: center;
  color: rgb(224, 198, 3);
}
.review-rating {
    font-size: 20px;
    padding: 5px;
}
.review-title {
    font-size: 20px; 
    font-weight: bolder;
    width: 100vw;
    height: 20px;
    overflow: hidden;
    /* background-color: pink; */
} 
.review-create-date {
    margin-left: 5px;
    margin-bottom: 5px;
    color: rgb(152, 152, 152);
}
.icon-padding {
    padding: 5px;
}
</style>
