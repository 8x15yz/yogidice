<template>
        <div id="container">
            <div id="cube" :class="{'show1': dicenum == 6, 
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
    <div class="rollit" @click="clickDiceInfo">이거</div>
</template>

<style>
    .rollit {
        width: 100px;
        height: 50px;
        background-color: gray;
    }

    #container {
    margin:50px auto;
    width:200px;
    height:200px;
    perspective:600px;
    }

    #cube {
    position:relative;
    transform-style: preserve-3d;
    transition: transform .4s;
    width: 100%;
    height: 100%;
    }

    #cube div {
    border:1px solid #ccc;
    border-radius:30px;
    width:198px;
    height:198px;
    position:absolute;
    background-image: url(https://1.bp.blogspot.com/-8oHADm0XlWY/UV72I6qYX_I/AAAAAAAAD8U/83sdKrWjjRg/s1600/Dice.png);
    background-repeat:no-repeat;
    opacity:.85;
    }

    .front  { transform: rotateY(   0deg ) translateZ( 100px ); background-position: -12px -12px; }
    .back   { transform: rotateX( 180deg ) translateZ( 100px ); background-position: -463px -239px; }
    .right  { transform: rotateY(  90deg ) translateZ( 100px ); background-position: -236px -12px; }
    .left   { transform: rotateY( -90deg ) translateZ( 100px ); background-position: -241px -235px; }
    .top    { transform: rotateX(  90deg ) translateZ( 100px ); background-position: -459px -12px; }
    .bottom { transform: rotateX( -90deg ) translateZ( 100px ); background-position: -12px -235px; }

    #cube.show1 { transform: translateZ( -100px ) rotateY(    0deg ); }
    #cube.show2 { transform: translateZ( -100px ) rotateX( -180deg ); }
    #cube.show3 { transform: translateZ( -100px ) rotateY(  -90deg ); }
    #cube.show4 { transform: translateZ( -100px ) rotateY(   90deg ); }
    #cube.show5 { transform: translateZ( -100px ) rotateX(  -90deg ); }
    #cube.show6 { transform: translateZ( -100px ) rotateX(   90deg ); }

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
        // let face = 1;

        // const show = function() {
        //     const divContainer = document.querySelector("#cube");
        //     divContainer.attr('class', 'show'+face);
        //     if(face == 6) {
        //         face=1;
        //     } else {
        //         face++; 
        //     }
        // };

        // var timer=setInterval("show()", 500);
        const dicenum = ref(false);
        function clickDice() {
            dicenum.value = Math.floor(Math.random()*(6-1)+1)
        }
        function clickDiceInfo() {
            for (let i = 1 ; i < 10; i ++) {
                setTimeout(() => clickDice(), 270*i);
            }
            setTimeout(() => document.querySelector("#container").className = "shake", 3000);
            setTimeout(() => document.querySelector("#diceBg").className = "zoomIn", 2900);
            setTimeout(() => document.querySelector("#container").className = "", 4000);
            setTimeout(() => document.querySelector("#diceBg").className = "", 4600);
        }
        return {
            dicenum,
            clickDiceInfo
        }
    }
}
</script>