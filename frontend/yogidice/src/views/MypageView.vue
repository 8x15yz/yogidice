<template>
<div style="position: absolute; z-index: 3;">
    <button @click="testlogin">임시로그인</button>
    <button @click="logOut">임시로그아웃</button>
    <button @click="viewUserInfo">회원정보보기</button>
</div>
    <div class="mypage-container">
        <!-- 헤더있는곳 -->
        <div>
            <grey-bg-head-bar></grey-bg-head-bar>
        </div>
        <!-- 헤더있는곳 -->


        <!-- 리뷰받는 모달폼 -->
        <div class="my-review-bg" v-if="reviewmodalview">
            <review-modal 
            @CloseReviewModal="CloseReviewModal"
            ></review-modal>
        </div>
        <!-- 리뷰받는 모달폼 -->


        <!-- 회원정보 나오는곳 -->
        <div class="profile-container-outer">
            <div class="profile-container">
                <p class="profile-inner" style="font-size : 35px">
                    <span @click="myPageBtn('main')">{{nickName}}</span>
                </p>
                <div style="padding: 3px;"><img src="../static/kakaologo.png" alt="" width="20" height="20">{{kakaoId}}{{lengamecategory}}</div>
            </div>
        </div>
        <!-- 회원정보 나오는곳 -->


        <!-- 마이페이지 세부페이지 버튼-->
        <div class="mypage-link-btn-outer">
            <div class="mypage-link-btn-wrapper">
                <div class="mypage-link-btn-inner border-rad-left" @click="myPageBtn('play')">
                    <div>
                        <div><i class="fas fa-chess"></i></div>
                        <div class="displayFlex">
                            <p class="mp-btn-p">플레이</p>
                            <p class="mp-btn-p" style="color: var(--color-mint); margin-left: 3px;">{{userplaygames.length}}</p>
                        </div>
                    </div>
                </div>
                <div class="mypage-link-btn-inner" @click="myPageBtn('review')">
                    <div>
                        <div><i class="fas fa-pencil-alt"></i></div>
                        <div class="displayFlex">
                            <p class="mp-btn-p">리뷰</p>
                            <p class="mp-btn-p" style="color: var(--color-mint); margin-left: 3px;">{{userplaygames.length}}</p>
                        </div>
                    </div>
                </div>
                <div class="mypage-link-btn-inner border-rad-right" @click="myPageBtn('bookmark')">
                    <div>
                        <div><i class="fas fa-bookmark"></i></div>
                        <div class="displayFlex">
                            <p class="mp-btn-p">북마크</p>
                            <p class="mp-btn-p" style="color: var(--color-mint); margin-left: 3px;">{{userbookgames.length}}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 마이페이지 세부페이지 버튼-->


        <!-- 안내문 보여주는곳 -->
        <div>
            <div class="mypage-boardgame-style">
                
                <!-- mainview : 보드게임 성향 알려주는곳 -->
                <div v-if="mainview">
                    <div class="mp-bg-s-inner">
                        <span>{{nickName}}</span><span>님은</span>
                    </div>
                    <div class="mp-bg-s-inner">
                        <span id="mypage-cate-result">{{toponemec}}</span><span id="mypage-cate-result">마니아</span><span> 입니다</span><span>❓</span>
                    </div>
                </div>
                <!-- mainview : 보드게임 성향 알려주는곳 -->


                <!-- play : 플레이한 보드게임 알려주는곳-->
                <div v-if="playview">
                    <div class="mp-bg-s-inner">
                        <span>내가 플레이한 게임</span><span>(23)</span>
                    </div>
                </div>
                <!-- play : 플레이한 보드게임 알려주는곳-->

                <!-- play : 북마크한 보드게임 알려주는곳-->
                <div v-if="bookmarkview">
                    <div class="mp-bg-s-inner">
                        <span>내가 북마크한 게임</span><span>(12)</span>
                    </div>
                </div>
                <!-- play : 북마크한 보드게임 알려주는곳-->


                <!-- reviewview : 리뷰한 보드게임 보여주는곳 -->
                <div v-if="reviewview">
                    <div class="mp-bg-s-inner">
                        <span>내가 남긴 리뷰</span><span>(40)</span>
                    </div>
                </div>
                <!-- reviewview : 리뷰한 보드게임 보여주는곳 -->

            </div>
        </div>
        <!-- 안내문 보여주는곳 -->


        <!-- 워드클라우드 / 플레이 / 리뷰 / 북마크 상세 들어갈 곳 -->
        <div class="mypage-bottom-container">
            <div v-if="mainview">
                <div style="height: 400px;"></div>
                <ber-chart></ber-chart>
                <word-cloud></word-cloud>
            </div>
            <mypage-play 
            :userplaygames="userplaygames" 
            v-if="playview"
            class="play-components-overflow"
            @OpenReviewModal="OpenReviewModal"
            ></mypage-play>
            <div>
                <mypage-bookmark v-if="bookmarkview"></mypage-bookmark>
            </div>
            <div>
                <mypage-review 
                v-if="reviewview"
                :reviewdatum='reviewdatum'
                @OpenReviewModal="OpenReviewModal"
                ></mypage-review>
            </div>
            <div v-if="bookmarkview" class="cardlist-my">
                <my-page-bookmark></my-page-bookmark>
            </div>
        </div>
        <!-- 워드클라우드 / 플레이 / 리뷰 / 북마크 상세 들어갈 곳 -->
    </div>
</template>

<script>
import { ref, computed} from 'vue'

import axios from 'axios'
import { useStore } from 'vuex'

import WordCloud from '../components/WordCloud.vue';
import BerChart from '../components/BerChart.vue';
import MypageReview from '../components/MypageReview.vue';
import MypageBookmark from '../components/MypageBookmark.vue';
import MypagePlay from '../components/MypagePlay.vue';
import GreyBgHeadBar from '@/layouts/GreyBgHeadBar.vue';
import ReviewModal from '@/components/modal/ReviewModal.vue';


export default {
    components: {
    WordCloud,
    MypageReview,
    MypageBookmark,
    MypagePlay,
    GreyBgHeadBar,
    ReviewModal,
    BerChart
  },
  
  setup() {
        const store = useStore()

        const mainview = ref(true);
        const playview = ref(false);
        const reviewview = ref(false);
        const bookmarkview = ref(false);
        const reviewmodalview = ref(false)
        // const ParentMechanism = ['추카퍼', '경제', '파티', '조건', '말', '전략']
        
        store.dispatch("myuser/GetUserInfo")

        const lengamecategory = computed(()=>store.state.myuser.likePMec)
        const toponemec = computed(()=>store.state.myuser.likePMecMax)
        
        let nickName = computed(()=>store.state.myuser.nickName)
        let kakaoId = computed(()=>store.state.myuser.kakaoId)
        let userplaygames = computed(()=>store.state.myuser.history)
        let userbookgames = computed(()=>store.state.myuser.bookmark)
        

        
        const myPageBtn = function(option) { // eslint-disable-line no-unused-vars
            if (option == 'play') {
                playview.value = true
                mainview.value = false
                reviewview.value = false
                bookmarkview.value = false
            }
            else if (option == 'review') {
                playview.value = false
                mainview.value = false
                reviewview.value = true
                bookmarkview.value = false
            }
            else if (option == 'bookmark') {
                playview.value = false
                mainview.value = false
                reviewview.value = false
                bookmarkview.value = true
            }
            else if (option == 'main') {
                playview.value = false
                mainview.value = true
                reviewview.value = false
                bookmarkview.value = false
            }
        
        }
        const CloseReviewModal = function() { 
            reviewmodalview.value = false
        }
        const OpenReviewModal = function() { 
            reviewmodalview.value = true
        }

        // 곧없앨거
        function testlogin() {
            axios({
                url: 'https://j7b206.p.ssafy.io/api/users/login',
                method: 'post',
                data: {
                    kakaoId: 'test4',
                    nickName: 'test4'
                }
            })
            .then(function a(response) { 
                console.log(response.headers.authorization) 
                localStorage.setItem("token", response.headers.authorization);
            })
            .catch(function (error) {
                console.log(error);
            });
        }
        function logOut() {
            localStorage.removeItem("token")
        }
        function viewUserInfo() {
            // for (let mec of likeMecha.value) {
            //     console.log(mec[1], mec[0])
            // }
            
        }
        // 곧없앨거

        return {
            myPageBtn,
            mainview, 
            playview,
            reviewview,
            bookmarkview,
            userplaygames,
            reviewmodalview,
            CloseReviewModal,
            OpenReviewModal,
            testlogin,
            logOut,
            viewUserInfo,
            nickName,
            userbookgames,
            kakaoId,
            lengamecategory,
            toponemec
        }
    }
  }

</script>

<style>
.my-review-bg {
    position: absolute;
    width: 100vw;
    height: 100vh;
    background-color: var(--color-bg-modal);
    display: flex;
    justify-content: center;

}
.play-components-overflow {
    overflow: auto;
    height: 50vh;
    width: 95vw;
}
.play-components-overflow::-webkit-scrollbar{width: 0px;}
.mypage-blank {
    height:70px;
}
.mypage-container {
    height: 100vh;
}
.displayFlex {
    display: flex;
}
.profile-container-outer {
    display: flex;
    justify-content: center;
}
.profile-container {
    text-align: end;
    padding: 20px;
    padding-bottom: 10px;
    width: 100vw;
    /* background-color: pink; */
    height: 15vw;
}
.profile-container > p {
    padding: 0px;
    margin: 0px;
    text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.3);
}
.profile-container > span {
    padding: 3px;
}
.mypage-link-btn-outer {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 150px;
    width: 100%;
    /* background-color: yellow; */
}
.mypage-link-btn-wrapper {
    display: flex;
    justify-content: space-evenly;
    /* background-color: skyblue; */
    background-color: white;
    outline: 1px black solid;
    border-radius: 4px;
    height: 25vw;
    width: 75vw;
    box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);

}
.mypage-link-btn-inner {
    display: flex;
    justify-content: center;
    align-items: center;
    outline: 0.5px black solid;
    width: 100%;
}
.mypage-link-btn-inner > div {
    text-align: center;
}
.mypage-link-btn-inner > div > div {
    margin-top: 10px;
    margin-bottom: 10px;
}
.border-rad-left {
    border-top-left-radius: 4px;
    border-bottom-left-radius:4px;
}
.border-rad-right {
    border-top-right-radius: 4px;
    border-bottom-right-radius:4px;
}
.mp-btn-p {
    margin: 0px;
}

.mypage-boardgame-style {
    display: flex;
    justify-content: center;
    height: 20vw;
    /* background-color: pink; */
    align-items: center;
}
.mypage-boardgame-style > div {
    text-align: center;
}
.mp-bg-s-inner{
    margin: 10px;
}
.mp-bg-s-inner > span {
    font-size: 20px;
}
#mypage-cate-result {
    font-size: 30px;
    font-weight: bolder;
    color: rgb(51, 162, 206);
}

.mypage-bottom-container {
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: rgba(255, 255, 255, 0.493);
    height: 50vh;
    overflow: auto;
    /* background-color: brown; */
}
</style>


