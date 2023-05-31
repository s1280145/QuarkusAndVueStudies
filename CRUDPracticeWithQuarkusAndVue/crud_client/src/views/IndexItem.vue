<template>
  <div>
    <h1>Items</h1>

    <table class="table table-hover">
      <thead>
      <tr>
        <td>ID</td>
        <td>Item Name</td>
        <td>Item Price</td>
        <td>Actions</td>
      </tr>
      </thead>

      <tbody>
      <tr v-for="item in items" :key="item.id">
        <td>{{ item.id }}</td>
        <td>{{ item.name }}</td>
        <td>{{ item.price }}</td>
        <td>
          <button v-on:click="moveToEdit(item.id)" class="btn btn-primary">Edit</button>
        </td>
        <td>
          <button class="btn btn-danger" v-on:click="deleteItem(item.id)">Delete</button>
        </td>
      </tr>
      </tbody>
    </table>
    <button @click="moveToCreate">作成</button>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      items: []
    }
  },
  mounted() {
    axios.get('/items')
        .then(response => this.items = response.data)
        .catch(error => console.log(error))
  },
  methods: {
    listItems() {
      axios.get('/items')
          .then(response => { this.items = response.data })
    },
    deleteItem(id) {
      let uri = "/items/" + id;
      axios.delete(uri)
          .then(() => { this.listItems() })
    },
    moveToEdit(id) {
      let uri = "/edit/" + id;
      this.$router.push(uri)
    },
    moveToCreate() {
      this.$router.push('/create')
    }
  }
}
</script>

<style scoped>

</style>