<template>
  <div>
    <h1 class="mt-5">Reservations</h1>
    <div class="d-flex justify-content-between">
      <button v-on:click="pageReload" class="btn btn-primary ms-5">Reload</button>
      <button v-on:click="moveToCreate" class="btn btn-primary me-5">New Reservation</button>
    </div>
    <table class="table mt-3">
      <thead>
      <tr>
        <th scope="col">status</th>
        <th scope="col">date</th>
        <th scope="col">start time</th>
        <th scope="col">end time</th>
        <th scope="col">user</th>
        <th scope="col">purpose</th>
        <th></th>
        <th></th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="reservation in reservations" :key="reservation.id">
        <td v-if="reservation.usageSituation" class="text-success h3"><b>using</b></td>
        <td v-else>not use</td>
        <td>{{reservation.date}}</td>
        <td>{{reservation.startTime}}</td>
        <td>{{reservation.endTime}}</td>
        <td>{{reservation.userName}}</td>
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
  created: function () {
    this.listReservations()
  },
  methods: {
    pageReload() {
      this.$router.go({path: this.$router.currentRoute.path, force: true})
    },
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