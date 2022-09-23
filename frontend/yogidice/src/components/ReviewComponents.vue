<template>
    <div v-for="data in datum" :key="data.key">
        나오고는 있오ㅓ?
        {{datum}}
        <div>
            {{data.title_kr}} {{}}
        </div>
    </div>
</template>

<script>
import { toRefs } from '@vue/runtime-core'
export default{
    props: {
        reviewdatum: Array,
    },
    setup(props) {
        const { datum } = toRefs(props.reviewdatum)
        console.log(datum)
        return {
            datum
        }
    },
}
</script>

<style>
#review-component-base {
    background-color: pink;
    height: 90px;
    border: 3px blue solid;
    margin-top: 10px;
    margin-bottom: 10px;
}

</style>
