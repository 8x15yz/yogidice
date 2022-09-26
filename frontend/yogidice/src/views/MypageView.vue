<template>
    <div class="mypage-container">
        <div class="mypage-blank">
            <grey-bg-head-bar></grey-bg-head-bar>
        </div>
        <div>
            <!-- 회원정보 나오는곳 -->
            <div class="profile-container-outer">
                <div class="profile-container">
                    <p class="profile-inner" style="font-size : 35px">
                        <span @click="myPageBtn('main')">아기공룡둘째</span>
                    </p>
                    <span>💌</span><span class="profile-email">8x15yz@gmail.com</span>
                </div>
            </div>
            <!-- 회원정보 나오는곳 -->




            <!-- 마이페이지 세부페이지 버튼-->
            <div class="mypage-link-btn-outer">
                <div class="mypage-link-btn-wrapper">
                    <div class="mypage-link-btn-inner" @click="myPageBtn('play')">
                        <div>
                            <div>🎮</div>
                            <div class="displayFlex">
                                <p class="mp-btn-p">플레이</p>
                                <p class="mp-btn-p">(23)</p>
                            </div>
                        </div>
                    </div>
                    <div class="mypage-link-btn-inner" @click="myPageBtn('review')">
                        <div>
                            <div>✏</div>
                            <div class="displayFlex">
                                <p class="mp-btn-p">리뷰</p>
                                <p class="mp-btn-p">(12)</p>
                            </div>
                        </div>
                    </div>
                    <div class="mypage-link-btn-inner" @click="myPageBtn('bookmark')">
                        <div>
                            <div>📍</div>
                            <div class="displayFlex">
                                <p class="mp-btn-p">북마크</p>
                                <p class="mp-btn-p">(40)</p>
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
                            <span>아기공룡둘째</span><span>님은</span>
                        </div>
                        <div class="mp-bg-s-inner">
                            <span id="mypage-cate-result">경제</span><span id="mypage-cate-result">마니아</span><span> 입니다</span><span>❓</span>
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
                <word-cloud v-if="mainview"></word-cloud>
                <mypage-play v-if="playview"></mypage-play>
                <div>
                    <mypage-bookmark v-if="bookmarkview"></mypage-bookmark>
                </div>
                <div>
                    <mypage-review v-if="reviewview"></mypage-review>
                </div>
                <div v-if="bookmarkview" class="cardlist-my">
                    <small-card-list></small-card-list>
                </div>
            </div>
            <!-- 워드클라우드 / 플레이 / 리뷰 / 북마크 상세 들어갈 곳 -->
        </div>
    </div>
</template>

<script>
import { ref } from 'vue'
import WordCloud from '../components/WordCloud.vue';
import MypageReview from '../components/MypageReview.vue';
// import MypageBookmark from '../components/MypageBookmark.vue';
import MypagePlay from '../components/MypagePlay.vue';
import SmallCardList from '../components/card/SmallCardList.vue';
import GreyBgHeadBar from '@/layouts/GreyBgHeadBar.vue'


export default {
    components: {
    WordCloud,
    MypageReview,
    // MypageBookmark,
    MypagePlay,
    SmallCardList,
    GreyBgHeadBar
  },
  setup() {
        const mainview = ref(true);
        const playview = ref(false);
        const reviewview = ref(false);
        const bookmarkview = ref(false);

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
        return {
        myPageBtn,
        mainview, 
        playview,
        reviewview,
        bookmarkview
        }
    }
  }

</script>

<style>
.cardlist-my {
    /* background-color: yellow; */
    /* height: 70%; */
}
.mypage-blank {
    height:70px;
}
.mypage-container {
    background-color: rgba(246, 246, 246, 0.575);
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
    width: 360px;
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
    /* background-color: pink; */
}
.mypage-link-btn-wrapper {
    display: flex;
    justify-content: space-evenly;
    /* background-color: skyblue; */
    background-color: white;
    outline: 1px black solid;
    border-radius: 4px;
    height: 100px;
    width: 300px;
    box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.2);

}
.mypage-link-btn-inner {
    display: flex;
    justify-content: center;
    align-items: center;
    outline: 0.5px black solid;
    border-radius: 4px;
    width: 100%;
}
.mypage-link-btn-inner > div {
    text-align: center;
}
.mypage-link-btn-inner > div > div {
    margin-top: 10px;
    margin-bottom: 10px;
}
.mp-btn-p {
    margin: 0px;
}

.mypage-boardgame-style {
    display: flex;
    justify-content: center;
    height: 100px;
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
    /* height: 43vh; */
    overflow: hidden;
}
</style>


