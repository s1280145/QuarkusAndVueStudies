<template>
  <div class="container">
    <div class="card">
      <div class="card-header">
        <h3>Edit Item</h3>
      </div>
      <div class="card-body">
        <form v-on:submit.prevent="updateItem">
          <div class="form-group">
            <label>Item Name:</label>
            <input type="text" class="form-control" v-model="item.name" />
          </div>
          <div class="form-group">
            <label>Item Price:</label>
            <input type="text" class="form-control" v-model="item.price" />
          </div>
          <div class="form-group">
            <input type="submit" class="btn btn-primary" value="Update Item" />
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      item: {}
    }
  },
  mounted() {
    let uri = "/items/" + this.$route.params.id;
    axios.get(uri)
        .then(response => { this.item = response.data })
  },
  methods: {
    getItem() {
      let uri = "/items/" + this.$route.params.id;
      axios.get(uri)
          .then(response => { this.item = response.data })
    },
    updateItem() {
      let uri = "/items/" + this.$route.params.id;
      axios.put(uri, this.item)
          .then(() => { this.$router.push('/index') })
    }
  }
}
</script>

<style scoped>

</style>