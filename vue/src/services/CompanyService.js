import axios from "axios";

export default {

    getCompany(companyId){
        return axios.get(`/companies/${companyId}`)
    },
    getCompanyByUserId(){
        return axios.get('/companies/home')
    },

    getAllCompanies(){
        return axios.get('/companies')
    },
    updateCompany(company){
        return axios.put(`/companies/${company.companyId}`, company)
    },
    createCompany(company) {
        return axios.post('/companies', company)
    },
    getEmployersByCompanyId(companyId){
        return axios.get(`/companies/${companyId}/employers`)
    },
    createEmployer(employer) {
        return axios.post(`/companies/employers`, employer)
    },
    updateEmployer(employer){
        return axios.put(`/companies/employers/${employer.employerId}`, employer)
    },
    getEmployerByEmployerId(companyId, employerId){
        return axios.get(`/companies/${companyId}/employers/${employerId}`)
    },
    getAvailabilityByEmployerId(employerId){
        return axios.get(`/employers/${employerId}/schedule`)
    },
    createEmployerAvailability(schedule){
        return axios.post(`/companies/employers/schedule`, schedule)
    },
    updateEmployerAvailability(schedule){
        return axios.put(`/companies/employers/${schedule.employerAvailabilityId}`, schedule)
    }


}