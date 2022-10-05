<template>
    <!-- navigation bar -->
    <div>
        <grey-bg-head-bar></grey-bg-head-bar>
    </div>
    <!-- navigation bar -->

    <!-- 리뷰받는 모달폼 -->
    <div class="my-game-review-bg" v-if="reviewformouter">
        <div class="review-modal-form" v-if="reviewform">
            <div class="review-modal-form-inner">
                <div class="review-modal-title">
                    <span>리뷰</span>
                    <span style="margin-right: 20px;"><i class="fas fa-times" @click="reviewformouter = false"></i></span>
                </div>
                <div class="rmi-displayflex">
                    <div class="review-modal-inner">
                        <div>
                            <div class="rating-comment">플레이하신 {{playnowname}}에 대해 리뷰를 남겨주세요!</div>
                            <div class="star-rating-wrap">
                                <div class="star-rating">
                                    ★★★★★
                                    <span>★★★★★</span>
                                    <input type="range" v-model="star" step="1" min="0" max="10">
                                </div>
                                <div class="star-rating-point">{{star}}점</div>
                            </div>
                            
                            <textarea class="review-modal-inner-textarea" v-model="gamereviewtext"></textarea>
                        </div>
                    </div>
                </div>
                <div class="rmi-displayflex">
                    <div class="review-modal-bottom"> 
                        <div class="im-not-play-this-game">게임을 플레이하지 않았습니다</div>
                        <div class="review-modal-btn-wrap">
                            
                            <div class="review-submit" @click="submitReview">등록</div>
                            <div class="review-revoke" @click="reviewformouter = false">취소</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- 작성완료 확인모달 -->
        <div class="modal-review-submitted" v-if="!reviewform">
            <p style="font-weight: bold;">리뷰가 등록되었습니다 😄</p>
        </div>
        <!-- 작성완료 확인모달 -->
        
    </div>
    <!-- 리뷰받는 모달폼 -->

    <!-- game 페이지 -->
    <div id="wrap-game-back" style="background-color:rgb(107, 107, 107)">
        <div class="game-ing"><span style="width: 70vw;">{{playnowname}} 게임중</span>  <span style="width: 5vw;" @click="ExitGame" ><i class="fas fa-times" ></i></span></div>
        <div style="padding-top: 4vh;height:70vh; background-color:var(--color-bg-base);">
            <game-clock v-if="clock"></game-clock>
            <pick-penalty v-if="bomb"></pick-penalty>
            <pick-tagger v-if="person"></pick-tagger>
            <rolling-dice v-if="dice"></rolling-dice>
        </div>
        <div class="bottom-box">
            <!-- <div style="position:absolute; bottom: 0px;"> -->
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
        <div class="alpha-box alpha-box-info" v-show="info">
            <div class="text-headline-6">{{playnowname}} 설명서</div>
        </div>
        <div class="alpha-box alpha-box-youtub" v-show="youtub">
            <related-videos></related-videos>
        </div>
        <div class="alpha-box alpha-box-memo" v-show="memo">
            <div>메모장</div>
            <br>
            <textarea name="playing-memo" placeholder="메모할 내용을 입력하세요" id="" rows="10"></textarea>
        </div>
        <div class="alpha-box alpha-box-file" v-show="file">
            아니뭐
        </div>
    </div>

    

    
</template>

<script>
import GreyBgHeadBar from '@/layouts/GreyBgHeadBar.vue';
import GameClock from '@/components/plusgame/GameClock.vue';
import PickPenalty from '@/components/plusgame/PickPenalty.vue';
import PickTagger from '@/components/plusgame/PickTagger.vue';
import RollingDice from '@/components/plusgame/RollingDice.vue';
import RelatedVideos from '@/components/plusgame/RelatedVideos.vue'
// import gameReviewModal from '@/components/modal/gameReviewModal.vue';

import { ref, computed, reactive } from '@vue/runtime-core'
import { useStore } from 'vuex'

export default {
    components: {
        GreyBgHeadBar,
        GameClock,
        PickPenalty,
        PickTagger,
        RollingDice,
        RelatedVideos
        // gameReviewModal
    },
    setup() {
        const store = useStore()

        const submenu = ref(false);
        const dice = ref(false);
        const person = ref(false);
        const clock = ref(false);
        const bomb = ref(false);
        const info = ref(false);
        const youtub = ref(false);
        const memo = ref(false);
        const file = ref(false);
        const gamereviewtext = ref('')

        const reviewform = ref(true)
        const reviewformouter = ref(false)

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
        let playnoehistoryid = reactive(0)
        function submitReview() {
            if (gamereviewtext.value == "") {
                window.alert('리뷰를 작성하고 제출해주세요')
            }
            else {
                reviewform.value = false
                // console.log(gamereviewtext.value, star.value, playgame.value, '들꽃')
                store.dispatch("myuser/SendReview", [{
                    "rating": star.value,
                    "review": gamereviewtext.value
                }, playnoehistoryid.value])
                setTimeout(() => {
                    reviewform.value = true
                    reviewformouter.value = false
                    }, 1500);
            }
        }

        let playnowname = computed(()=>store.state.gamedetail.playnowname)

        function ExitGame() {
            reviewformouter.value = true
            store.dispatch("gamedetail/ExitGame")
            playnoehistoryid = computed(()=>store.state.gamedetail.playnoehistoryid)
            // console.log(playgame)
            // store.dispatch("myuser/SendReview")
        }
        const star = ref(1)



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
            file,
            playnowname,
            ExitGame,
            star,
            reviewform,
            submitReview,
            reviewformouter,
            gamereviewtext
        }
    }
}
</script>

<style>
.star-rating-wrap {
    display:flex; 
    justify-content: center;
}
.star-rating-point {
    margin: 10px;
    font-size: 6vw;
    color: rgb(0, 0, 0);
}
.star-rating {
    position: relative;
    font-size: 8vw;
    color: #ddd;
    margin-bottom: 15px; 
  }
  
  .star-rating > input {
    width: 100%;
    height: 100%;
    position: absolute;
    left: 0;
    opacity: 0;
    cursor: pointer;
  }
  
  .star-rating > span {
    width: calc(v-bind ('star') * 10%);
    /* width:  50%; */
    position: absolute; 
    left: 0;
    color: rgb(255, 239, 19);
    overflow: hidden;
    pointer-events: none;
  }
.my-game-review-bg {
    position: absolute;
    width: 100vw;
    height: 100vh;
    background-color: var(--color-bg-modal);
    display: flex;
    justify-content: center;
    z-index: 8;
}
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
  height: 94vh;
  background-color: var(--color-bg-base);
  overflow: scroll;
}
.game-ing {
    height: 10vh;
    background-color: rgb(107, 107, 107);
    display: flex;
    justify-content: space-evenly;
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
    position: relative;
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
    bottom: 20px;
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
    margin-bottom: 10vw;
}
.bottom-box{    
    margin-bottom:10vh;
    position: relative;
}
.alpha-box {
    width: 80vw;
    height: 60vh;
    background-color: var(--color-white);
    position: absolute;
    top:20vh;
    margin-left: 5vw;
    padding: 5vh 5vw;
    border-radius: 10px;
    box-shadow: 0px 24px 16px rgba(0, 0, 0, 0.15);
    overflow: scroll;
}
  textarea {
    width: 100%;
    border: none;
    resize: none;
  }


</style>
