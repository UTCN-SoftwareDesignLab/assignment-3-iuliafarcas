<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create item" : "Edit item" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="item.patient_id" label="Patient ID" />
          <v-text-field v-model="item.doctor_id" label="Doctor ID" />
          <v-text-field v-model="item.start_date" label="Start date" />
          <v-text-field v-model="item.end_date" label="End Date" />
          <v-text-field v-model="item.details" label="Details" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
          <v-btn @click="deleteConsultation">Delete Consultation</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "ConsultationDialog",
  props: {
    item: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.consultation
            .create({
              patient_id: this.item.patient_id,
              doctor_id: this.item.doctor_id,
              start_date: this.item.start_date,
              end_date: this.item.end_date,
              details: this.item.details,
            })
            .then(() => this.$emit("refresh"));
      } else {
        api.consultation
            .edit({
              id: this.item.id,
              patient_id: this.item.patient_id,
              doctor_id: this.item.doctor_id,
              start_date: this.item.start_date,
              end_date: this.item.end_date,
              details: this.item.details,
            })
            .then(() => this.$emit("refresh"));
      }
    },
    deleteConsultation(){
      api.consultation.
      delete({
        id: this.item.id,
        patient_id: this.item.patient_id,
        doctor_id: this.item.doctor_id,
        start_date: this.item.start_date,
        end_date: this.item.end_date,
        details: this.item.details,
      }).then(() => this.$emit("refresh"));
    }
  },
  computed: {
    isNew: function () {
      return !this.item.id;
    },
  },
};
</script>

<style scoped></style>
