<template>
  <div>
    <div id="register-nickname">
      <label for="nickname-input" class='text-subtitle-2'>사용할 닉네임을 입력하세요</label>
      <input id="nickname-input" type="text" placeholder="카카오닉네임기본값">
      <div>
        <button @click="registNickname" class="button-small-blue text-button">계속</button>
      </div>
    </div>
  </div>

  
</template>

<script>
// import { onMounted } from '@vue/runtime-core'
export default {
  setup() {
    const registNickname = function () {
      console.log("로그인이닷")
    }
    return {
      registNickname
    }
    
    
  }

}
</script>

<style>
#register-nickname {
  display: flex;
  flex-direction: column;
  gap: 10px;
  position: absolute;
  top: 30vh;
  margin: 0 15vw;

}
#nickname-input {
  box-sizing: border-box;
  width: 70vw;
  height: 40px;
  border: 1px solid #000000;
  border-radius: 4px;
  padding: 5px 10px;
}
#register-nickname > div {
  display: flex;
  justify-content: flex-end;

}


</style>