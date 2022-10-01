<template>
        <!-- 주사위 하나일때 -->
        <div v-if="diceview == 1">
            <div id="container">
                <div id="cube-1" :class="{'show1': dicenum == 6, 
                                        'show2': dicenum == 1, 
                                        'show3': dicenum == 2, 
                                        'show4': dicenum == 3, 
                                        'show5': dicenum == 4, 
                                        'show6': dicenum == 5}">
                    <div class="top"></div>
                    <div class="front"></div>
                    <div class="left"></div>
                    <div class="back"></div>
                    <div class="right"></div>
                    <div class="bottom"></div>
                </div>
            </div>
            <div style="display: flex;">
                <div class="rollit text-button" id="roll-button" @click="clickDiceInfo">Roll It!</div>
                <div class="rollit text-button" id="roll-button" @click="diceview = 2">2 peice</div>
            </div>
        </div>
        <!-- 주사위 하나일때 -->

        <!-- 주사위 두개일때 -->
        <div v-if="diceview == 2" >
            <div id="container-wrap">
                    <div id="container-2">
                        <div id="cube-2" :class="{'show1': dicenum == 6, 
                                                'show2': dicenum == 1, 
                                                'show3': dicenum == 2, 
                                                'show4': dicenum == 3, 
                                                'show5': dicenum == 4, 
                                                'show6': dicenum == 5}">
                            <div class="top"></div>
                            <div class="front"></div>
                            <div class="left"></div>
                            <div class="back"></div>
                            <div class="right"></div>
                            <div class="bottom"></div>
                        </div>
                    </div>
                    <div id="container-2">
                        <div id="cube-2" :class="{'show1': dicenum2 == 6, 
                                                'show2': dicenum2 == 1, 
                                                'show3': dicenum2 == 2, 
                                                'show4': dicenum2 == 3, 
                                                'show5': dicenum2 == 4, 
                                                'show6': dicenum2 == 5}">
                            <div class="top"></div>
                            <div class="front"></div>
                            <div class="left"></div>
                            <div class="back"></div>
                            <div class="right"></div>
                            <div class="bottom"></div>
                        </div>
                    </div>
                </div>
                <div style="display: flex;">
                        <div class="rollit text-button" id="roll-button" @click="clickDiceInfo">Roll It!</div>
                        <div class="rollit text-button" id="roll-button" @click="diceview = 1">1 peice</div>
                    </div>
            </div>
        <!-- 주사위 두개일때 -->
</template>

<style>
    #roll-button{
    font-size: 6vw;
    height: 8vh;
    background-color: var(--color-bg-modal);
    }
    .rollit {
        width: 100px;
        height: 50px;
        background-color: gray;
    }
    #container-wrap {
        display: flex;
        justify-content: space-evenly;
        background-color: pink;
    }
    #container {
        margin:50px auto;
        margin-top: 17vh;
        width:200px;
        height:200px;
        perspective:800px;
        background-color: yellow;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    #container-2 {
        margin-top: 0px;
        margin-bottom: 0px;
        margin:50px auto;
        margin-top: 17vh;
        width:120px;
        height:80px;
        perspective: 800px;
        background-color: yellow;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    #cube-1 {
    position:relative;
    transform-style: preserve-3d;
    transition: transform .4s;
    width: 50%;
    height: 50%;
    }

    #cube-2 {
    position:relative;
    transform-style: preserve-3d;
    transition: transform .4s;
    width: 50%;
    height: 50%;
    }

    #cube-2 > div, #cube-1 > div {
    border:1px solid #ccc;
    border-radius:10px;
    width:98px;
    height:98px;
    position:absolute;
    /* background-image: url(https://1.bp.blogspot.com/-8oHADm0XlWY/UV72I6qYX_I/AAAAAAAAD8U/83sdKrWjjRg/s1600/Dice.png); */
    background-repeat:no-repeat;
    opacity:.85;
    }

    /* .front  { transform: rotateY(   0deg ) translateZ( 50px ); background-position: -12px -12px; }
    .back   { transform: rotateX( 180deg ) translateZ( 50px ); background-position: -463px -239px; }
    .right  { transform: rotateY(  90deg ) translateZ( 50px ); background-position: -236px -12px; }
    .left   { transform: rotateY( -90deg ) translateZ( 50px ); background-position: -241px -235px; }
    .top    { transform: rotateX(  90deg ) translateZ( 50px ); background-position: -459px -12px; }
    .bottom { transform: rotateX( -90deg ) translateZ( 50px ); background-position: -12px -235px; } */

    .front  { transform: rotateY(   0deg ) translateZ( 48px ); background-image: url( "../../static/front.png"); background-size:contain;}
    .back   { transform: rotateX( 180deg ) translateZ( 48px ); background-image: url("../../static/back.png"); background-size:contain;}
    .right  { transform: rotateY(  90deg ) translateZ( 48px ); background-image: url("../../static/right.png"); background-size:contain;}
    .left   { transform: rotateY( -90deg ) translateZ( 48px ); background-image: url("../../static/left.png"); background-size:contain;}
    .top    { transform: rotateX(  90deg ) translateZ( 48px ); background-image: url("../../static/top.png"); background-size:contain;}
    .bottom { transform: rotateX( -90deg ) translateZ( 48px ); background-image: url("../../static/bottom.png"); background-size:contain;}

    #cube-1.show1, #cube-2.show1 { transform: translateZ( -48px ) rotateY(    0deg ); }
    #cube-1.show2, #cube-2.show2 { transform: translateZ( -48px ) rotateX( -180deg ); }
    #cube-1.show3, #cube-2.show3 { transform: translateZ( -48px ) rotateY(  -90deg ); }
    #cube-1.show4, #cube-2.show4 { transform: translateZ( -48px ) rotateY(   90deg ); }
    #cube-1.show5, #cube-2.show5 { transform: translateZ( -48px ) rotateX(  -90deg ); }
    #cube-1.show6, #cube-2.show6 { transform: translateZ( -48px ) rotateX(   90deg ); }

    @keyframes shake {
        0%, 100% {
            transform: translate3d(0, 0, 0)
        }
        10%,
        30%,
        50%,
        70%,
        90% {
            transform: translate3d(-10px, 0, 0)
        }
        20%,
        40%,
        60%,
        80% {
            transform: translate3d(10px, 0, 0)
        }
        }

        .shake {
        -webkit-animation-duration: 1s;
        animation-duration: 1s;
        -webkit-animation-fill-mode: both;
        animation-fill-mode: both;
        animation-name: shake;
        }
        @keyframes zoomIn {
            0% {
                opacity: 0;
                transform: scale3d(.3, .3, .3)
            }
            50% {
                opacity: 1
            }
            }

            .zoomIn {
            background-color: gray; height: 500px;
            -webkit-animation-duration: 1s;
            animation-duration: 1s;
            -webkit-animation-fill-mode: both;
            animation-fill-mode: both;
            animation-name: zoomIn
            }

</style>

<script>
import { ref } from '@vue/runtime-core'

export default {
    setup () {
        const diceview = ref(1);
        const dicenum = ref(Math.floor(Math.random()*(6-1)+1));
        const dicenum2 = ref(Math.floor(Math.random()*(6-1)+1));
        function clickDice() {
            dicenum.value = Math.floor(Math.random()*(6-1)+1)
            dicenum2.value = Math.floor(Math.random()*(6-1)+1)
        }
        function clickDiceInfo() {
            // if (num == 2) {
                for (let i = 1 ; i < 14; i ++) {
                    setTimeout(() => clickDice(), 190*i);
                }
                // setTimeout(() => document.querySelector("#container").className = "shake", 3000);
                // setTimeout(() => document.querySelector("#diceBg").className = "zoomIn", 2900);
                // setTimeout(() => document.querySelector("#container").className = "", 4000);
                // setTimeout(() => document.querySelector("#diceBg").className = "", 4600);
            // }
        }
        return {
            dicenum,
            dicenum2,
            clickDiceInfo,
            diceview
        }
    }
}
</script>