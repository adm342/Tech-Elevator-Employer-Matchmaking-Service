import axios from "axios";

export default {

    getStudent(studentId){
        return axios.get(`/students/${studentId}`)
    },

    getStudentByUserId(){
        return axios.get("/students/home")
    },

    getAllStudents(){
        return axios.get("/students")
    },

    updateStudentBio(student){
        return axios.put(`/students/${student.studentId}`, student)
    },

    createStudent(student){
        return axios.put('/students', student)
    }
}