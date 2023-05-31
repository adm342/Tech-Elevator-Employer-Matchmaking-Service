<template>
  <div id="register" class="text-center">
    <form @submit.prevent="register">
      <h1>Create Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="role">Role</label>
        <select id="role" v-model="user.role" required>
        <option value="" disabled selected>--Select A Role--</option>
        <option value='student'>Student</option>
        <option value="employer">Company</option>
        </select>
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>

<div class="company-input-group" v-if="user.role === 'employer'">
        <h2>Company Registration</h2>
        <div class="company-input-group">
        <label for="companyName">Company Name:</label>
        <input type="text" id="companyName" v-model="company.companyName" required />
        </div>
        <div class="company-input-group">
        <label for="companyWebsiteURL">Website URL:</label>
        <input type="text" id="companyWebsiteURL" v-model="company.websiteUrl"  />
        </div>
        <div class="company-input-group">
        <label for="companyDescription">Description:</label>
        <input type="text" id="companyDescription" v-model="company.description" />
        </div>
        <div class="company-input-group">
        <label for="jobPostingUrl">Job Posting:</label>
        <input type="text" id="jobPostingUrl" v-model="company.jobPostingUrl" />
        </div>
        <div class="company-input-group">
        <label for="youtubeUrl">Youtube URL:</label>
        <input type="text" id="youtubeUrl" v-model="company.youtubeLinkUrl" />
        </div>
        <div class="company-input-group">
        <label for="companyLogoUrl">Company Logo URL:</label>
        <input type="text" id="companyLogoUrl" v-model="company.imageUrl" />
        </div>
</div>
        
        <!-- <div class="employer-input-group" v-if="user.role === 'employer'">
        <h2>Employer Registration</h2>
        <div class="employer-input-group">
        <label for="employerFirstName">First Name:</label>
        <input type="text" id="employerFirstName" v-model="employerFirstName" />
        </div>
        <div class="employer-input-group">
        <label for="employerLastName">Last Name:</label>
        <input type="text" id="employerLasttName" v-model="employerLastName" />
        </div>
        <div class="employer-input-group">
        <label for="employerTitle">Title:</label>
        <input type="text" id="employerTitle" v-model="employerTitle" />
        </div>
        <div class="employer-input-group">
        <label for="employerEmail">Email:</label>
        <input type="email" id="employerEmail" v-model="employerEmail" />
        </div>
        <div class="employer-input-group">
        <label for="employerLinkedin">Linkedin URL:</label>
        <input type="text" id="employerLinkedin" v-model="employerLinkedin" />
        </div>
        </div> -->
      

      <div class="student-input-group" v-if="user.role === 'student'">
        <h2>Student Registration</h2>
        <div class="student-input-group">
        <label for="firstName">First Name:</label>
        <input type="text" id="first-name" v-model="student.firstName" required />
        </div>
        <div class="student-input-group">
        <label for="lastName">Last Name:</label>
        <input type="text" id="last-name" v-model="student.lastName" required />
        </div>
        <div class="student-input-group">
        <label for="emailAddress">Email Address:</label>
        <input type="email" id="email-address" v-model="student.emailAddress" required />
        </div>
        <div class="student-input-group">
        <label for="linkedinUrl">LinkedIn URL:</label>
        <input type="text" id="linkedinUrl" v-model="student.linkedinUrl" required />
        </div>
        <div class="student-input-group">
        <label for="resumeUrl">Resume URL:</label>
        <input type="text" id="resumeUrl" v-model="student.resumeUrl" required />
        </div>
        <div class="student-input-group">
        <label for="teprogramid" required>Tech Elevator Program</label>
        <select id="teprogramid" v-model="student.teProgramId">
        <option value="" disabled selected>--Select A Tech Elevator Program--</option>
        <option value='1'>National Live Remote</option>
        <option value="2">Cincinnati</option>
        <option value="3">Cleveland</option>
        </select>
      </div>
        <div class="student-input-group">
        <label for="pathwayprogram">Pathway Program</label>
        <select id="pathwayprogram" v-model="student.pathwayId" required>
        <option value="" disabled selected>--Select A Pathway Program--</option>
        <option value='1'>National Live Remote</option>
        <option value="2">Cincinnati</option>
        <option value="3">Cleveland</option>
        </select>
      </div>

      </div>

      <button type="submit">Create Account</button>

    </form>
  </div>
</template>

<script>
import studentService from '../services/StudentService';
import authService from '../services/AuthService';
import companyService from '../services/CompanyService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: '',
      },
      company: {
        companyName: '',
        websiteUrl: '',
        description: '',
        jobPostingUrl: '',
        youtubeLinkUrl: '',
        imageUrl: ''
      },
      student: {
        firstName: '',
        lastName: '',
        emailAddress: '',
        linkedinUrl: '',
        resumeUrl: '',
        teProgramId: 0,
        pathwayId: 0
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              if (this.user.role === 'student') {
                studentService.createStudent(this.student)
              }
              if (this.user.role === 'employer') {
                companyService.createCompany(this.company)
              }
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}
.student-input-group {
  margin-bottom: 1rem;
}
.company-input-group {
  margin-bottom: 1rem;
}
.employer-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}
</style>
