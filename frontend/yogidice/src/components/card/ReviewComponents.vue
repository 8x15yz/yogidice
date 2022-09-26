<template>
<button @click="testisuser" style="position:absolute;">이거</button>
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
                <div v-if="isuser" @click="reviewUpdateModalOn" class="icon-padding">
                    <i class="far fa-edit review-rating"></i>
                </div>
            </div>
            <div class="review-create-date">
                {{data.create_date}}
            </div>
            <div class="review-content-text-overflow ">
                {{data.review}}
            </div>
        </div>
        <div style="height: 100px;"></div>
    </div>
</template>

<script>
import { reactive } from '@vue/runtime-core'

export default{
    props: {
        reviewdatum: Object
    },
    setup(props) {
        let isuser = true
        const datum = reactive(props.reviewdatum)

        const reviewUpdateModalOn = function () {
            console.log('네')
        }
        const testisuser = function () {
            isuser == false
            console.log(isuser)
        }
        return {
            isuser,
            datum,
            reviewUpdateModalOn,
            testisuser
        }
    },
}
</script>

<style>
#review-component-base {
    width: 326px;
    background-color: white;
    height: auto;
    border-radius: 5px;
    box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
    margin-top: 10px;
    margin-bottom: 10px;
    padding: 10px;
    border: solid 1px rgb(197, 197, 197);
}
.review-title-rating-wrapper {
    display: flex;
    /* background-color: rgb(253, 232, 236); */
    align-items: center;
}
.review-content-text-overflow {
    overflow: hidden;
    -webkit-line-clamp: 3;
    width: 300px;
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
    width: 270px;
    height: 25px;
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
