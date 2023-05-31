import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    student: {},
    company: {},
    interview: {},
    companies: [],
    employer: {},
    employers: [],
    matchmakingEvent: {},
    favoriteCompanies: [],
    studentInterviews: []
  },
  mutations: {
    SET_STUDENT(state, student) {
      state.student = student;
    },
    SET_COMPANY(state, company) {
      state.company = company;
    },
    SET_COMPANIES(state, companies) {
      state.companies = companies;
    },
    SET_INTERVIEW(state, interview){
      state.interview = interview;
    },
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      state.student = {};
      axios.defaults.headers.common = {};
    },
    SET_EMPLOYER(state, employer) {
      state.employer = employer;
    },
    SET_EMPLOYERS(state, employers) {
      state.employers = employers;
    },
    SET_MATCHMAKING_EVENT(state, matchmakingEvent) {
      state.matchmakingEvent = matchmakingEvent;
    },
    SET_FAVORITE_COMPANIES(state, favoriteCompanies) {
      state.favoriteCompanies = favoriteCompanies;
    },
    SET_STUDENT_INTERVIEWS(state, studentInterviews) {
      state.studentInterviews = studentInterviews;
  }
  }
})


