<template>
  <div class='search-bar'>
    <span class="material-icons">search</span>
    <input type="text" placeholder="보드게임을 검색해보세요!">
  </div>
</template>

<script>
export default {

}
</script>

<style>
.search-bar {
  box-sizing: border-box;

  /* Auto layout */
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 3px 10px;
  gap: 8px;

  width: 90vw;
  height: 32px;
  left: 5vw;

  /* white */

  background: #FFFFFF;
  /* black */

  border: 1px solid #000000;
  border-radius: 8px;
}
.search-bar input {
  width:100%;
  border-width: 0ch;
}


</style>