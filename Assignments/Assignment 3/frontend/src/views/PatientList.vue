<template>
  <v-card>
    <v-card-title>
      Patients
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-btn @click="addPatient">Add Patient</v-btn>
      <v-btn @click="addConsultation">Add Consultation</v-btn>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="items"
        :search="search"
        @click:row="editItem"
    ></v-data-table>
    <v-card-title>
      Consultations
    </v-card-title>
    <v-data-table
        :headers="headers2"
        :items="consultations"
        :search="search"
        @click:row="editConsultation"
    ></v-data-table>
    <PatientDialog
      :opened="dialogVisible"
      :item="selectedItem"
      @refresh="refreshList"
    ></PatientDialog>

    <ConsultationDialog
        :opened="dialogVisible2"
        :item="selectedConsultation"
        @refresh="refreshList"
    ></ConsultationDialog>
  </v-card>


</template>

<script>
import api from "../api";
import PatientDialog from "../components/PatientDialog";
import ConsultationDialog from "../components/ConsultationDialog";

export default {
  name: "PatientList",
  components: {ConsultationDialog, PatientDialog },
  data() {
    return {
      items: [],
      consultations: [],
      search: "",
      headers: [
        {
          text: "ID",
          align: "start",
          sortable: false,
          value: "id",
        },
        { text: "Name", value: "name"},
        { text: "Card ID", value: "card_id"},
        { text: "CNP", value: "cnp"},
        { text: "Birth Date", value: "birth_date"},
        { text: "Address", value: "address"},

      ],

      headers2: [
        {
          text: "Patient ID",
          align: "start",
          sortable: false,
          value: "patient_id",
        },
        { text: "Doctor ID", value: "doctor_id"},
        { text: "Start date", value: "start_date"},
        { text: "End Date", value: "end_date"},
        { text: "Details", value: "details"},

      ],

      dialogVisible: false,
      selectedItem: {},

      dialogVisible2: false,
      selectedConsultation: {},
    };
  },
  methods: {
    editItem(item) {
      this.selectedItem = item;
      this.dialogVisible = true;
    },
    addPatient() {
      this.dialogVisible = true;
    },
    addConsultation() {
      this.dialogVisible2 = true;
    },
    editConsultation(item) {
      this.selectedConsultation = item;
      this.dialogVisible2 = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedItem = {};
      this.items = await api.patient.allPatients();
      this.consultations = await  api.consultation.allConsultations();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
