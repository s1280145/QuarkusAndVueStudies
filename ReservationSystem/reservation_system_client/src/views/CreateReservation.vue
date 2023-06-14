<template>
  <div>
    <div class="container mt-5">
      <div class="card">
        <div class="card-header">
          <h3>Add Reservation</h3>
        </div>
        <div class="card-body">
          <form v-on:submit.prevent="addReservation">
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
              <p v-if="isInValidName(reservation.userName)" class="text-danger">この項目は必須入力です</p>
            </div>
            <div class="form-group">
              <label>Purpose:</label>
              <input type="text" class="form-control" v-model="reservation.purpose" />
            </div>
            <input type="hidden" class="form-control" name="reservation.usageSituation" value="0" />
            <div class="form-group">
              <input v-bind:disabled="noReservation" type="submit"  class="btn btn-primary mt-5" value="Add Reservation" />
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data() {
    return {
      reservation: {}
    }
  },
  methods: {
    addReservation() {
      let uri = "/reservations"
      axios.post(uri, this.reservation)
          .then(() => {this.$router.push('/home')})
    },
    isInValidName(username){
      if(username === undefined) return true;
      return username.length < 1;
    }
  },
  computed: {
    noReservation() {
      if(this.reservation.userName === undefined || this.reservation.purpose === undefined) return true;
      if(this.reservation.date && this.reservation.startTime && this.reservation.endTime && this.reservation.userName.length > 0 && this.reservation.purpose.length > 0) return false;
      return true;
    }
  }
}
</script>

<style scoped>

</style>