<template>
    <div class="review-component-base">
        {{reviewdatum}}
    </div>
</template>

<script>
import { toRefs } from '@vue/runtime-core'
export default{
    props: {
        reviewdatum: Array,
    },
    setup(props) {
        const { reviewdatum } = toRefs(props.reviewdatum)
        return {
            
        }
    },
}
</script>

<style>
.review-component-base {
    background-color: pink;
    height: 90px;
    border: 3px blue solid;
    margin-top: 10px;
    margin-bottom: 10px;
}

</style>
