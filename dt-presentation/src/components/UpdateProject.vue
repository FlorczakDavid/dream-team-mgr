<template>
  <h1 class="text-center mt-3">Modify your project</h1>
  <div class="container bg-light">
  <form  id="project-form" @submit.prevent="getFormValues" class="d-flex flex-column mx-3">
    <div class="mb-3">
      <label for="projectName" class="fs-5 form-label">Project Name <span class="text-danger">*</span></label>
      <input type="text" v-model="inputName" class="form-control" id="projectName" maxlength="200" name="name">
      <!-- <pre>{{ $v }}</pre> -->
    </div>

    <div class="mb-3">
      <label for="projectId" class="fs-5 form-label">Project Id <span class="text-danger">*</span></label>
      <input type="text" v-model="inputPId" class="form-control" id="projectId" maxlength="100" name="projectId">
    </div>

    <div class="mb-3">
      <label for="releaseDate" class="fs-5 form-label">Release Date <span class="text-danger">*</span></label>
      <input type="date" v-model="inputDate" class="form-control" id="releaseDate" name="releaseDate">
    </div>

    <div class="mb-3">
      <label for="description" class="fs-5 form-label">Description</label>
      <textarea class="form-control" v-model="inputDesc" id="description" maxlength="5000" rows="3" name="desc"></textarea>
      <span class="pull-right label label-default" id="count_message"></span>
    </div>

    <div class="mb-3">
      <LangTechSelector
        @add-langTech="addLangTech"
        @rm-langTech="rmLangTech"
        :stacksToProps="stacks"
      />
    </div>
    <div class="d-flex justify-content-center m-4">
    <button class="btn btn-primary">UPDATE</button></div>
  </form>
</div>
</template>

<script>
import { useVuelidate } from '@vuelidate/core'
import { helpers, required, maxLength} from '@vuelidate/validators'
import {requiredMessage, maxLengthMessage} from '../plugin/validatorMessage'

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
      inputPId:'',
      inputDate:'',
      inputDesc:'',
      formData: {
        projectName: '',
        projectUniqueInternalId:'',
        projectStartDate:'',
        projectDescription:'',
        langTechNames:[]
      }

    }
  },

  created() {
    // URLからIDを取得してデータをロード
    // this.id = this.$route.params.id;
    this.fetchData();
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

  methods: {
    async fetchData() {
      // const initialData = await fetch(`http://localhost:8080/projects/${id}`);
      const initialData = await fetch(`http://localhost:8080/projects/1`);

      this.data = await initialData.json();
      console.log(this.data);
      this.inputName= this.data.oneProject.projectName;
      console.log(this.inputName);
      // this.nameInitial= this.data.projectName;
      this.inputPId= this.data.oneProject.projectUniqueInternalId;
      let initialDate = this.data.oneProject.projectStartDate;
      let date = new Date(initialDate);
      this.inputDate = date.toISOString().substring(0, 10);
      
      this.inputDesc= this.data.oneProject.projectDescription;
      this.stackDatasTemps = this.data.oneLangTechProj.forEach(element => {
             this.stacks.push(element.langTechName)
       });
      console.log(this.stacks);


    },
    addLangTech(langTech) {
      this.formData.langTechNames.push(langTech);
      if(langTech.trim() != "") {
        this.formData.langTechNames.push(langTech.trim());
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
      this.formData.projectName= this.inputName,
      console.log(this.formData.projectName);
      this.formData.projectUniqueInternalId= this.inputPId,
      this.formData.projectStartDate= this.inputDate,
      this.formData.projectDescription= this.inputDesc,
      console.log(this.formData.projectDescription);
      // this.formData.langTechNames= this.stacks;
      // console.log(this.stacks);

      const validated = this.validate();
      this.sendvalidForm(validated);
    },

    validate() {
    let validated = true;
    // HTML form inputs default type = string (empty)
    // trim inputs ("best practice")
    const pName = this.formData.projectName.trim();
    const pId = this.formData.projectUniqueInternalId.trim();
    const sDate = this.formData.projectStartDate.trim();
    const desc = this.formData.projectDescription.trim();
    // set to false as soon as an input violates a validation constraint
    if (pName.length === 0 || pName.length > 200) {
        validated = false;
    } else if (pId.length === 0 || pId.length > 100) {
        validated = false;
    } else if (sDate.length === 0) {
        validated = false;
    } else if (desc.length > 5000) {
        validated = false;
    }
    return validated;
},

    async sendvalidForm(validated){
      if (validated) {
        await this.send(this.formData);
    } else {
        alert('Validation errors, please check your inputs!');
    }
    },

    async send(data) {
      
      //put input value null
      this.inputName ='';
      this.inputPId ='';
      this.inputDate='';
      this.inputDesc='';
      this.stacks= [];



      const json = JSON.stringify(data);
      console.log(json);
      // JS object for request options
      // Needs to set method POST (GET is default)
      // Needs to specify request's body content type (expected by server)
      const options = {
        method: 'PUT',
        headers: new Headers({ 'content-type': 'application/json' }),
        body: json
      }
      // try-catch to handle potential client/server communication errors
      try {
        const response = await fetch('http://localhost:8080/projects/1', options);
        if (response.status === 202 || response.status === 200) { // Expected success status code
          alert('Your update is done with success');
        } else { // Any other status code
          alert('Error: ' +response.status);
        }
      } catch (err) {
        alert('An unexpected error has occured!');
        console.error(err);
      }
    }
  },

  components: {
    LangTechSelector
  }
}
</script>

<style scoped></style>
