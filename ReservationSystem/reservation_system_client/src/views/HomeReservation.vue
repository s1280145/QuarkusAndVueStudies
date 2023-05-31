<template>
  <div>
    <h1>Reservations</h1>
    <button v-on:click="moveToCreate" class="btn btn-primary">New Reservation</button>
    <table class="table">
      <thead>
      <tr>
        <th scope="col">date</th>
        <th scope="col">start time</th>
        <th scope="col">end time</th>
        <th scope="col">purpose</th>
        <th></th>
        <th></th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="reservation in reservations" :key="reservation.id">
        <td>{{reservation.date}}</td>
        <td>{{reservation.startTime}}</td>
        <td>{{reservation.endTime}}</td>
        <td>{{reservation.purpose}}</td>
        <td>
          <button v-on:click="moveToEdit(reservation.id)" class="btn btn-primary">Edit</button>
        </td>
        <td>
          <button class="btn btn-danger" v-on:click="deleteReservation(reservation.id)">Delete</button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      reservations: []
    }
  },
  mounted() {
    let uri = "/reservations";
    axios.get(uri)
        .then(response => { this.reservations = response.data })
  },
  methods: {
    listReservations() {
      let uri = "/reservations";
      axios.get(uri)
          .then(response => { this.reservations = response.data })
    },
    deleteReservation(id) {
      let uri = "/reservations/" + id;
      axios.delete(uri)
          .then(() => { this.listReservations() })
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