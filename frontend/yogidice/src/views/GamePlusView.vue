<template>
    <!-- navigation bar -->
    <div>
        <grey-bg-head-bar></grey-bg-head-bar>
    </div>
    <!-- navigation bar -->



    <!-- game 페이지 -->
    <div id="wrap-game-back">
        <div class="game-ing">
            {{클루}} 클루 게임중
        </div>

       <!-- game 메뉴하단바 -->
       <div style="position:absolute; bottom: 0px;">
            <div id="game-menu-circle"></div>
            <div id="game-menu-bottom">
                <div class="game-menu-btn"></div>
                <div class="game-menu-btn right-border"></div>
                <div class="game-menu-center">  

                </div>
                <div class="game-menu-btn left-border"></div>
                <div class="game-menu-btn"></div>
            </div>
            <!-- game 메뉴하단바 -->
            </div>
       </div>
        <!-- game 페이지 -->
    

    
</template>

<script>
import GreyBgHeadBar from '@/layouts/GreyBgHeadBar.vue';

export default {
    components: {
        GreyBgHeadBar
    },
    setup() {
        
    },
}
</script>

<style>
#wrap-game-back {
  width: 100vw;
  height: 100vh;
  background-color: var(--color-bg-base);
  overflow: scroll;
}
.game-ing {
    height: 10vh;
    background-color: rgb(107, 107, 107);
    display: flex;
    justify-content: center;
    align-items: center;
    font-weight: bold;
    color: white;
    font-size: 5vw;
}
#game-menu-bottom {
    height: 10vh;
    background-color: var(--color-bg-base);
    display: flex;
    justify-content: center;
    align-items: center;
    font-weight: bold;
    color: white;
    font-size: 5vw;
}

.game-menu-center {
    width: 18vw;
    /* border: solid 2px blue; */
    /* background-color: rgb(163, 208, 208); */
    height: 80px;
    --top: -5px;
    --left: 9vw;
    --radius: 10vw;
    background: radial-gradient(circle at var(--left) var(--top), transparent 0, transparent var(--radius), rgb(107, 107, 107) var(--radius));
}
.right-border {
    border-top-right-radius: 15px;
}
.left-border {
    border-top-left-radius: 15px;
}
.game-menu-btn {
    width: 20.5vw;
    /* border: solid 2px pink; */
    background-color: rgb(107, 107, 107);
    height: 80px;
}
#game-menu-circle {
    z-index: 3;
    position: absolute;
    left: 50%;
    bottom: 60px;
    transform: translate(-50%);
    background-color: rgb(107, 107, 107);
    box-shadow: 0px 2px 1px 2px rgba(0, 0, 0, 0.2);
    width: 16vw;
    height: 16vw;
    border-radius: 100%;

}

</style>
