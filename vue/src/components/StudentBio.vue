<!--<template>
<div>
    <div class="diagonal">
<h2>Welcome {{ this.$store.state.student.firstName }} {{ this.$store.state.student.lastName }}</h2>
</div>
<div>
    <h1>The doors just opened to your new future.</h1>
    </div>

<ul>
    <li>email: {{ this.$store.state.student.emailAddress  }}</li>
    <li>LinkedIn: {{ this.$store.state.student.linkedinUrl }}</li>
    <li>Resume: {{ this.$store.state.student.resumeUrl }}</li>
    <li>Tech Elevator Program ID: {{ this.$store.state.student.teProgramId }}</li>
    <li>Tech Elevator Pathway ID: {{ this.$store.state.student.tePathwayId }}</li>
</ul>

</div>
  
</template>-->

<template>
<div>
    <div class="diagonal">
        <h2>
          Welcome {{ this.$store.state.student.firstName }}
          {{ this.$store.state.student.lastName }}
        </h2>
      </div>
      <div>
        <h1>The doors just opened to your new future.</h1>
      </div>

  <form v-on:submit.prevent="updateStudent" class="studentBioForm">
    <div>
      
      <div class="status-message error" v-show="errorMsg !== ''">
        {{ errorMsg }}
      </div>

      <div class="form-group">
        <label for="emailAddress">Email: </label>
        <input
          id="emailAddress"
          type="text"
          class="form-control"
          v-model="student.emailAddress"
          
        />
      </div>

      <div class="form-group">
        <label for="linkedinUrl">LinkedIn: </label>
        <input
          id="linkedinUrl"
          type="text"
          class="form-control"
          v-model="student.linkedinUrl"
        />
      </div>

      <div class="form-group">
        <label for="resumeUrl">Resume: </label>
        <input
          id="resumeUrl"
          type="text"
          class="form-control"
          v-model="student.resumeUrl"
          
        />
      </div>

      <ul>
        <li>
          Tech Elevator Program ID: {{ this.$store.state.student.teProgramId }}
        </li>
        <li>
          Tech Elevator Pathway ID: {{ this.$store.state.student.pathwayId }}
        </li>
      </ul>
    </div>
  </form>
</div>
</template>

<script>
import studentService from "../services/StudentService";

/*export default {
    name: 'student',
    data() {
        return {
            student: {},
        }
    },
    created(){
        studentService.getStudentByUserId().then( (response) => {
            this.$store.commit("SET_STUDENT", response.data);
        })

    },*/

export default {
  name: "student",
  props: {
    studentID: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
      student: {
        emailAddress: "",
        linkedinUrl: "",
        resumeUrl: "",
        teProgramId: "",
        tePathwayId: "",
      },
      errorMsg: "",
    };
  },
  created(){
        studentService.getStudentByUserId().then( (response) => {
            this.$store.commit("SET_STUDENT", response.data);
        })

    },
  methods: {
    updateStudent(student) {
      studentService
        .updateStudentBio(student)
        .then((response) => {
          if (response.status === 200) {
            this.$router.push(`/students/${student.studentId}`);
          }
        })
        .catch((error) => {
          this.handleErrorResponse(error, "updating");
        });
    },
  },
};
</script>

<style scoped>




h2 {
  display: block;
  text-transform: capitalize;
  font-size: 500%;
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  text-align: center;
  
}

h1{
display: block;
    font-size: 250%;
    
    color:#00ADEE ;
    text-align: center;
}

ul {
  display: flex;
  justify-content: center;
  background-color: #00ADEE;

  border: 1px solid black;
  border-radius: 15px;
}

li {

    font-size: 200%;
    
    list-style: none;
    padding: 5px;
    margin: 3%;
    text-align: center;
    margin-left: 0;
        
}

.form-group {

  font-size: 200%;
  margin-top: 25%;
   
    list-style: none;
    padding: 5px;
    margin:3%;
  
    text-align: center;
    

}

</style>

