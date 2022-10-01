<template>
    <!-- navigation bar -->
    <div>
        <grey-bg-head-bar></grey-bg-head-bar>
    </div>
    <!-- navigation bar -->



    <!-- game 페이지 -->
    <div id="wrap-game-back">
        <div class="game-ing">{{클루}} 클루 게임중</div>
        <div>
            <game-clock v-if="clock"></game-clock>
            <pick-penalty v-if="bomb"></pick-penalty>
            <pick-tagger v-if="person"></pick-tagger>
            <rolling-dice v-if="dice"></rolling-dice>
        </div>
    </div>
    <!-- game 페이지 -->

    
    
    <!-- game 메뉴하단바 -->
    <div style="position:absolute; bottom: 0px;">
    <div class="speech-bubble" v-if="submenu">
        <div :class="{'bubble-tip-selected': info == true, 'bubble-tip-unsel': info == false}" @click="subMenuBtn('info')"><i class="fas fa-info-circle"></i></div>
        <div :class="{'bubble-tip-selected': youtub == true, 'bubble-tip-unsel': youtub == false}" @click="subMenuBtn('youtub')"><i class="fas fa-play-circle"></i></div>
        <div :class="{'bubble-tip-selected': memo == true, 'bubble-tip-unsel': memo == false}" @click="subMenuBtn('memo')"><i class="fas fa-pencil-alt"></i></div>
        <div :class="{'bubble-tip-selected': file == true, 'bubble-tip-unsel': file == false}" @click="subMenuBtn('file')"><i class="fas fa-folder"></i></div>
    </div>
    <div id="game-menu-circle" @click="submenu = !submenu"><i class="fas fa-plus"></i></div>
        <div id="game-menu-bottom">
            <div  :class="{'game-menu-btn game-menu-selected': dice == true, 'game-menu-btn game-menu-unsel': dice == false}">
                <div @click="subMenuBtn('dice')">
                    <div class="gmb-df ">🎲</div>
                    <div>주사위</div>
                </div>
            </div>
            <div :class="{'game-menu-btn right-border game-menu-selected': person == true, 'game-menu-btn right-border game-menu-unsel': person == false}">
                <div @click="subMenuBtn('person')">
                    <div class="gmb-df">🎲</div>
                    <div>술래</div>
                </div>
            </div>
            <div class="game-menu-center"></div>
            <div :class="{'game-menu-btn left-border game-menu-selected': clock == true, 'game-menu-btn left-border game-menu-unsel': clock == false}">
                <div @click="subMenuBtn('clock')">
                    <div class="gmb-df">🎲</div>
                    <div>타이머</div>
                </div>
            </div>
            <div :class="{'game-menu-btn game-menu-selected': bomb == true, 'game-menu-btn game-menu-unsel': bomb == false}">
                <div @click="subMenuBtn('bomb')">
                    <div class="gmb-df">🎲</div>
                    <div>벌칙</div>
                </div>
            </div>
        </div>
    </div>

    
</template>

<script>
import GreyBgHeadBar from '@/layouts/GreyBgHeadBar.vue';
import GameClock from '@/components/plusgame/GameClock.vue';
import PickPenalty from '@/components/plusgame/PickPenalty.vue';
import PickTagger from '@/components/plusgame/PickTagger.vue';
import RollingDice from '@/components/plusgame/RollingDice.vue';
import { ref } from '@vue/runtime-core'

export default {
    components: {
        GreyBgHeadBar,
        GameClock,
        PickPenalty,
        PickTagger,
        RollingDice
    },
    setup() {
        const submenu = ref(false);
        const dice = ref(false);
        const person = ref(false);
        const clock = ref(false);
        const bomb = ref(false);
        const info = ref(false);
        const youtub = ref(false);
        const memo = ref(false);
        const file = ref(false);

        const subMenuBtn = function(option) { 
            if (option == 'dice') {
                dice.value = !dice.value
                person.value = false
                clock.value = false
                bomb.value = false
            }
            else if (option == 'person') {
                dice.value = false
                person.value = !person.value
                clock.value = false
                bomb.value = false
            }
            else if (option == 'clock') {
                dice.value = false
                person.value = false
                clock.value = !clock.value
                bomb.value = false
            }
            else if (option == 'bomb') {
                dice.value = false
                person.value = false
                clock.value = false
                bomb.value = !bomb.value
            } 
            else if (option == 'info') {
                info.value = !info.value
                youtub.value = false
                memo.value = false
                file.value = false
                submenu.value = !submenu.value
            } 
            else if (option == 'youtub') {
                info.value = false
                youtub.value = !youtub.value
                memo.value = false
                file.value = false
                submenu.value = !submenu.value
            }
            else if (option == 'memo') {
                info.value = false
                youtub.value = false
                memo.value = !memo.value
                file.value = false
                submenu.value = !submenu.value
            }
            else if (option == 'file') {
                info.value = false
                youtub.value = false
                memo.value = false
                file.value = !file.value
                submenu.value = !submenu.value
            } 
        }
        const closeSubMenuBtn = function(option) {
            if (option == 'main') {
                dice.value = false
                person.value = false
                clock.value = false
                bomb.value = false
            }
            else if (option == 'sub') {
                info.value = false
                youtub.value = false
                memo.value = false
                file.value = false
            }
        }

        return {
            submenu,
            subMenuBtn,
            closeSubMenuBtn,
            dice,
            person,
            clock,
            bomb,
            info,
            youtub,
            memo,
            file
        }
    }
}
</script>

<style>
.bubble-tip-unsel {
    height: 27px;
    width: 40px;
    display: flex;
    justify-content: center;
    align-items: center;
}
.bubble-tip-selected {
    background-color: rgb(194, 194, 194);
    height: 27px;
    width: 40px;
    border-radius: 40px;
    display: flex;
    justify-content: center;
    align-items: center;
}
.game-menu-btn {
    width: 20.5vw;
    /* border: solid 2px pink; */
    background-color: rgb(107, 107, 107);
    height: 80px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 4vw;
}
.game-menu-unsel {
    background-color: rgb(107, 107, 107);
}
.game-menu-selected {
    background-color: rgb(56, 56, 56);
}
.speech-bubble {
	position: absolute;
	background: #e3e3e3;
    height: 20px;
    width: 200px;
    top: -120px;
    left: 50%;
    transform: translate(-50%);
    box-shadow: 0px 5px 7px 0px rgba(0, 0, 0, 0.2);
    border-radius: 20px;
    padding: 5px;
    padding-bottom: 10px;
    display: flex;
    justify-content: space-evenly;
}
.speech-bubble:after {
	content: '';
	position: absolute;
	bottom: 0;
	left: 50%;
	width: 0;
	height: 0;
	border: 21px solid transparent;
	border-top-color: #e3e3e3;
	border-bottom: 0;
	margin-left: -21px;
	margin-bottom: -21px;
    filter: drop-shadow(0px 5px 2px rgba(0, 0, 0, 0.2));
}
#wrap-game-back {
  width: 100vw;
  height: 83vh;
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
    position: sticky;
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
.gmb-df {
    display: flex;
    justify-content: center;
    margin-bottom: 5px;
}
#game-menu-circle {
    z-index: 3;
    position: absolute;
    left: 50%;
    bottom: 53px;
    transform: translate(-50%);
    background-color: rgb(107, 107, 107);
    box-shadow: 0px 2px 1px 2px rgba(0, 0, 0, 0.2);
    width: 16vw;
    height: 16vw;
    border-radius: 100%;
    font-size: 10vw; 
    color: white;
    display: flex;
    justify-content: center;
    align-items: center;
}

</style>
