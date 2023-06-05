<template>
  <div class="container mt-5">
    <div class="card">
      <div class="card-header">
        <h3>Edit Reservation</h3>
      </div>
      <div class="card-body">
        <form v-on:submit.prevent="updateReservation">
          <div class="form-group">
            <label>Reservation Date:</label>
            <input type="date" class="form-control" v-model="reservation.date" />
          </div>
          <div class="form-group">
            <label>Reservation Start Time:</label>
            <input type="time" class="form-control" v-model="reservation.startTime" />
          </div>
          <div class="form-group">
            <label>Reservation End Time:</label>
            <input type="time" class="form-control" v-model="reservation.endTime" />
          </div>
          <div class="form-group">
            <label>Name:</label>
            <input type="text" class="form-control" v-model="reservation.userName" />
          </div>
          <div class="form-group">
            <label>Purpose:</label>
            <input type="text" class="form-control" v-model="reservation.purpose" />
          </div>
          <div class="form-group">
            <input type="submit" class="btn btn-primary mt-5" value="Edit Reservation" />
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
      reservation: {}
    }
  },
  mounted() {
    let uri = "/reservations/" + this.$route.params.id;
    axios.get(uri)
        .then(response => {
          this.reservation = response.data
        })
  },
  methods: {
    updateReservation() {
      let uri = "/reservations/" + this.$route.params.id;
      axios.put(uri, this.reservation)
          .then(() => { this.$router.push('/home') })
    }
  }
}
</script>

<style scoped>

</style>