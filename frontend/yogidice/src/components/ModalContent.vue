<template>
  <div class="modal-wrap">
    <div class="modal-header">
      <slot name="header"></slot>
    </div>
    <div class="modal-body">
      <slot name="body"></slot>
    </div>
    <div class="modal-footer">
      <slot name="footer"></slot>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
.modal-wrap {
  width: 100%;

}
.modal-header {
  display: flex;
  justify-content: flex-start;
  align-items: flex-end;
  height: 28px;
  padding: 0px 16px 0px 16px;
  /* Inside auto layout */
}


.modal-body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 20px 0px;
  padding: 0px 16px 12px 16px;
}

.modal-footer {
  display: flex;
  justify-content: flex-end;
  padding: 0px;
}

</style>