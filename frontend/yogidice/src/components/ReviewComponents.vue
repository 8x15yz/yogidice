<template>
    <div>
        <div v-for="data in datum" :key="data.key" id="review-component-base">
            <div class="review-title-rating-wrapper">
                <div style="font-size: 20px; font-weight: bolder;">
                    {{data.bg_title}}
                </div>
                <div>
                    {{data.rating}} 
                </div>
            </div>
            <div>
                {{data.create_date}}
            </div>
            <div class="review-content-text-overflow">
                {{data.review}}
            </div>
            
        </div>
    </div>
</template>

<script>
import { onMounted, reactive } from '@vue/runtime-core'

export default{
    props: {
        reviewdatum: Object
    },
    setup(props) {
        onMounted(() => console.log(props.reviewdatum));
        const datum = reactive(props.reviewdatum)
        console.log(datum)
        return {
            datum
        }
    },
}
</script>

<style>
#review-component-base {
    background-color: white;
    height: 100px;
    border-radius: 5px;
    box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);
    margin-top: 10px;
    margin-bottom: 10px;
}
.review-title-rating-wrapper {
    display: flex;
    justify-content: space-between;
}
.review-content-text-overflow {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    -webkit-line-clamp: 3;
    width: 360px;
}

</style>
