<template>
  <div id="app">
    <div class="form-area__name">
      <p>名前</p>
      <input type="text" name="name" v-model="name"/>
      <p v-if="isInValidName" class="error">名前は4文字以上で入力してください</p>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return {
      name: 'name',
    };
  },
  computed:{
    isInValidName(){
      //文字列が4文字以上かチェックする
      return this.name.length < 4;
    }
  }
}
</script>

<style scoped>

</style>