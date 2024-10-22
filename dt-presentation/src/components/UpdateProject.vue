<script>
import { useVuelidate } from '@vuelidate/core'
import { helpers, required, maxLength} from '@vuelidate/validators'
import {requiredMessage, maxLengthMessage} from '../plugin/validatorMessage'
import axios from 'axios'
import LangTechSelector from "./LangTechSelector.vue"

export default {
  name: '',
  setup () {
    return { v$: useVuelidate() }
  },
  data() {
    return {
      // nameInitial: "FFF",
      // idInitial: "87",

      //inputStack: '',
      input: '',
      stacks: [],
      //stackDatas: [],
      inputName:'',
      inputUIId:'',
      inputDate:'',
      inputDesc:'',
      formData: {
        name: '',
        uniqueInternalId:'',
        startDate:'',
        description:'',
        langTechNames:[],
        projectID: null
      }

    }
  },
  validations () {
    return {
      inputName: { required, maxLengthValue: maxLength(10) },
      inputUIId: { required, maxLengthValue: maxLength(10) },
      inputDate: { required },
      inputDesc : {maxLengthValue: maxLength(10) }
    }
  },
  created() {
    this.fetchData();
    this.projectID = this.$route.params.id;

  },

  async mounted() {

    // try {
    //   const response = await fetch("http://localhost:8080/langTechs");
    //   this.data = await response.json();
    //   this.stackDatasTemps = this.data.forEach(element => {
    //     this.stackDatas.push(element.langTechName)
    //   });
    //   console.log(this.data);
    // } catch (error) {
    //   console.error("Failed to fetch data:", error);
    // }
  },
  watch: {
    '$route.params.id': {
      immediate: true,
      handler(newVal) {
        this.projectID = newVal;
      }
    }
  },

  methods: {
    async fetchData() {
      // const initialData = await fetch(`http://localhost:8080/projects/${id}`);
      try{
        console.log(this.projectID);
        const response = await axios.get('http://localhost:8080/projects/'+ this.projectID)
        this.data = response.data;
      } catch {
        console.error('Erreur lors de la récupération de projet:', error)
      }
      this.inputName= this.data.oneProject.name;
      console.log(this.inputName);
      // this.nameInitial= this.data.projectName;
      this.inputUIId= this.data.oneProject.uniqueInternalId;
      let initialDate = this.data.oneProject.startDate;
      let date = new Date(initialDate);
      this.inputDate = date.toISOString().substring(0, 10);
      
      this.inputDesc= this.data.oneProject.description;

      this.formData.langTechNames = this.data.oneLangTechProj.map(element => element.name);
    console.log(this.formData.langTechNames);
      this.stacks = this.formData.langTechNames;
      // this.stackDatasTemps = this.data.oneLangTechProj.forEach(element => {
      //        this.stacks.push(element.name)
      //  });
      // console.log(this.stacks);


    },
    addLangTech(langTech) {
      //this.formData.langTechNames.push(langTech);
      if(langTech.trim() !== "" && !this.formData.langTechNames.includes(langTech.trim())) {
        this.formData.langTechNames.push(langTech.trim());
        console.log(this.formData.langTechNames)
      }
    },
    rmLangTech(langTech) {
      this.formData.langTechNames = this.formData.langTechNames.filter((s) => s !== langTech)
    },
    //creer fucntion ici
    // addStackChip() {
    //   if(this.inputStack.trim() != "") {
    //     this.stacks.push(this.inputStack.trim());
    //     console.log(this.stacks);
    //   }
    //   this.inputStack = ''
    // },
    // removeStackChip(chip) {
    //   this.stacks = this.stacks.filter((s) => s !== chip);
    //   console.log(this.stacks);
    // },

    getFormValues(){
      this.formData.name= this.inputName,
      console.log(this.formData.name);
      this.formData.uniqueInternalId= this.inputUIId,
      this.formData.startDate= this.inputDate,
      this.formData.description= this.inputDesc,
      console.log(this.formData.description);
      // this.formData.langTechNames= this.stacks;
      // console.log(this.stacks);

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

      // in fetch on n'a pas besoin de convertir en json ni de mettre header, il le fait automatiquement 
      // try-catch to handle potential client/server communication errors
      try {
        const response = await axios.put('http://localhost:8080/projects/1', data);
        if (response.status === 202 || response.status === 200) { // Expected success status code
          alert('Your update is done with success');
        } else { // Any other status code
          alert('Error: ' +response.status);
        }
      } catch (err) {
        alert('An unexpected error has occured!');
        console.error(err);
      }

      //put input value null
      this.inputName ='';
      this.inputUIId ='';
      this.inputDate='';
      this.inputDesc='';
      this.stacks= [];

      this.v$.$reset();
    }
  },

  components: {
    LangTechSelector
  }
}
</script>
<template>
  <h1 class="text-center mt-3">{{ $t('updateProject.pageTitle') }}</h1>
  <div class="container bg-light">
  <form  id="project-form" @submit.prevent="getFormValues" class="d-flex flex-column mx-3">
    <div class="mb-3">
      <label for="projectName" class="fs-5 form-label">{{ $t('formProject.name') }}<span class="text-danger">*</span></label>
      <input type="text" v-model="inputName" class="form-control" id="projectName" maxlength="200" name="name">
      <div v-if="v$.inputName.$error" class="text-danger">{{ $t('formProject.name') }} {{ $t('formProject.validation') }}</div>
    </div>

    <div class="mb-3">
      <label for="projectId" class="fs-5 form-label">{{ $t('formProject.id') }}<span class="text-danger">*</span></label>
      <input type="text" v-model="inputUIId" class="form-control" id="projectId" maxlength="100" name="projectId">
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
        :stacksToProps="stacks"
      />
    </div>
    <div class="d-flex justify-content-center m-4">
    <button class="btn btn-primary">{{ $t('updateProject.updateButton') }}</button></div>
  </form>
</div>
</template>

<style scoped></style>
