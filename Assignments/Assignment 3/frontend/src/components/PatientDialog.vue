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
          <v-text-field v-model="item.name" label="Name" />
          <v-text-field v-model="item.card_id" label="Card ID" />
          <v-text-field v-model="item.cnp" label="CNP" />
          <v-text-field v-model="item.birth_date" label="Birthday" />
          <v-text-field v-model="item.address" label="Address" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
          <v-btn @click="deletePatient">Delete Patient</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "PatientDialog",
  props: {
    item: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.patient
          .create({
            name: this.item.name,
            card_id: this.item.card_id,
            cnp: this.item.cnp,
            birth_date: this.item.birth_date,
            address: this.item.address,
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.patient
          .edit({
            id: this.item.id,
            name: this.item.name,
            card_id: this.item.card_id,
            cnp: this.item.cnp,
            birth_date: this.item.birth_date,
            address: this.item.address,
          })
          .then(() => this.$emit("refresh"));
      }
    },
    deletePatient(){
      api.patient.
          delete({
        id: this.item.id,
        name: this.item.name,
        card_id: this.item.card_id,
        cnp: this.item.cnp,
        birth_date: this.item.birth_date,
        address: this.item.address,
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
