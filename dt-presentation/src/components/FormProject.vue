<script>
// import { send } from 'vite';
import { useVuelidate } from '@vuelidate/core'
import { helpers, required, maxLength} from '@vuelidate/validators'
import {requiredMessage, maxLengthMessage} from '../plugin/validatorMessage'
import axios from 'axios'

import getLabels from "../assets/i18N"
import LangTechSelector from "./LangTechSelector.vue"

export default {
  name: '',
  setup () {
    return { v$: useVuelidate() }
  },
  data() {
    return {
      input: '',
      inputName:'',
      inputUIId:'',
      inputDate:'',
      inputDesc:'',
      formData: {
        name: '',
        uniqueInternalId:'',
        startDate:'',
        description:'',
        langTechNames:[]
      }
    }
  },
  validations () {
    return {
      inputName: { required, maxLengthValue: maxLength(5) },
      inputUIId: { required, maxLengthValue: maxLength(5) },
      inputDate: { required },
      inputDesc : {maxLengthValue: maxLength(5) }
    }
  },
  async mounted() {
    getLabels();
  },

  methods: {
    addLangTech(langTech) {
      //this.formData.langTechNames.push(langTech);
      if(langTech.trim() != "") {
        this.formData.langTechNames.push(langTech.trim());
      }
    },
    rmLangTech(langTech) {
      this.formData.langTechNames = this.formData.langTechNames.filter((s) => s !== langTech)
    },
    getFormValues(){
      this.formData.name= this.inputName
      this.formData.uniqueInternalId= this.inputUIId
      this.formData.startDate= this.inputDate
      this.formData.description= this.inputDesc

      // const validated = this.validate();
      this.sendValidForm();
    },

//     validate() {
//     let validated = true;
//     // HTML form inputs default type = string (empty)
//     // trim inputs ("best practice")
//     const tempName = this.formData.name.trim();
//     const tempUIId = this.formData.uniqueInternalId.trim();
//     const tempStartDate = this.formData.startDate.trim();
//     const tempDesc = this.formData.description.trim();
//     // set to false as soon as an input violates a validation constraint
//     if (tempName.length === 0 || tempName.length > 200) {
//         validated = false;
//     } else if (tempUIId.length === 0 || tempUIId.length > 100) {
//         validated = false;
//     } else if (tempStartDate.length === 0) {
//         validated = false;
//     } else if (tempDesc.length > 5000) {
//         validated = false;
//     }
//     return validated;
// },

    async sendValidForm(){
      const isFormCorrect = await this.v$.$validate()
      console.log(isFormCorrect);
      if(isFormCorrect){
        this.send(this.formData);
      }else{
        alert('Input is not correct');
      }
      
    },
    async send(data) {

      await axios.post('http://localhost:8080/projects/create',data)
      .then(response => {
      console.log('Response:', response); 
      if (response.status === 202) { // Expected success status code
      alert('Your request to add project has been done!');
      } else { // Any other status code
      alert('A client or server error has occured!');
      }
      })    
      .catch(error => {console.log(error)
        alert('A client or server error has occured!');
      })

           
      //put input value null
      this.inputName ='';
      this.inputUIId ='';
      this.inputDate='';
      this.inputDesc='';

      this.v$.$reset();
      // JS object for request options
      // Needs to set method POST (GET is default)
      // Needs to specify request's body content type (expected by server)
      // const options = {
      //   method: 'POST',
      //   headers: new Headers({ 'content-type': 'application/json' }),
      //   body: json
      // }
      // // try-catch to handle potential client/server communication errors
      // try {
      //   const response = await fetch('http://localhost:8080/projects/create', options);
      //   if (response.status === 202) { // Expected success status code
      //     alert('Wow you are lucky mate, everything is okay!');
      //   } else { // Any other status code
      //     alert('A client or server error has occured!');
      //   }
      // } catch (err) {
      //   alert('An unexpected error has occured!');
      //   console.error(err);
      // }
    }
  },

  components: {
    LangTechSelector
  }
}
</script>
<template>
  <h1 class="text-center mt-3">{{ $t('formProject.pageTitle') }}</h1>
  <div class="container bg-light">
  <form  id="project-form" @submit.prevent="getFormValues" class="d-flex flex-column mx-3">
    <div class="mb-3">
      <label for="projectName" class="fs-5 form-label">{{ $t('formProject.name') }}<span class="text-danger">*</span></label>
      <input type="text" v-model="inputName" class="form-control" id="projectName" maxlength="200"
        :placeholder="$t('formProject.placeholders.name')" name="name">
      <div v-if="v$.inputName.$error" class="text-danger">{{ $t('formProject.name') }} {{ $t('formProject.validation') }}</div>
    </div>

    <div class="mb-3">
      <label for="projectId" class="fs-5 form-label">{{ $t('formProject.id') }}<span class="text-danger">*</span></label>
      <input type="text" v-model="inputUIId" class="form-control" id="projectId" maxlength="100" :placeholder="$t('formProject.placeholders.id')" name="projectId">
      <div v-if="v$.inputUIId.$error" class="text-danger">{{ $t('formProject.id') }}{{ $t('formProject.validation') }}</div>
    </div>

    <div class="mb-3">
      <label for="releaseDate" class="fs-5 form-label">{{ $t('formProject.date') }}<span class="text-danger">*</span></label>
      <input type="date" v-model="inputDate" class="form-control" id="releaseDate" name="releaseDate">
      <div v-if="v$.inputDate.$error" class="text-danger">{{ $t('formProject.date') }}{{ $t('formProject.validation') }}</div>
    </div>

    <div class="mb-3">
      <label for="description" class="fs-5 form-label">{{ $t('formProject.description') }}</label>
      <textarea class="form-control" v-model="inputDesc" id="description" maxlength="5000" rows="3" name="desc"></textarea>
      <span class="pull-right label label-default" id="count_message"></span>
      <div v-if="v$.inputDesc.$error" class="text-danger">{{ $t('formProject.description') }}{{ $t('formProject.validation') }}</div>
    </div>

    <div class="mb-3">
      <LangTechSelector 
        @add-langTech="addLangTech"
        @rm-langTech="rmLangTech"
      />
    </div>
    <div class="d-flex justify-content-center m-4">
      <button class="btn btn-primary">{{ $t('formProject.sendButton') }}</button>
    </div>
  </form>
</div>
</template>

<style scoped></style>


