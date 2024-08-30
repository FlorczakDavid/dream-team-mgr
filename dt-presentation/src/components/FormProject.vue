<template>
  <h1 class="text-center mt-3">Add your project to Dream Team Platforme</h1>
  <div class="container bg-light">
  <form  id="project-form" @submit.prevent="getFormValues" class="d-flex flex-column mx-3">
    <div class="mb-3">
      <label for="projectName" class="fs-5 form-label">Project Name <span class="text-danger">*</span></label>
      <input type="text" v-model="inputName" class="form-control" id="projectName" maxlength="200"
        placeholder="max 200 characters" name="name">
      <!-- <span v-if="!$v.name.required">Project Name is mandatory.</span> -->
      <pre>{{ $v }}</pre>
      <!-- <p
  v-for="(error, index) of $v.$errors"
  :key="index"
>
<strong>{{ error.$validator }}</strong>
<small> on property</small>
<strong>{{ error.$property }}</strong>
<small> says:</small>
<strong>{{ error.$message }}</strong>
</p> -->
    </div>

    <div class="mb-3">
      <label for="projectId" class="fs-5 form-label">Project Id <span class="text-danger">*</span></label>
      <input type="text" v-model="inputPId" class="form-control" id="projectId" maxlength="100" placeholder="max 100 characters" name="projectId">
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
      <label for="stackName" class="fs-5 form-label mb-2 mt-4">Languages and technologies</label>
      <div>
        <div class="card-body d-flex flex-column">
          <div class="input-group form-label mb-2 mt-4">
            <div>
              <span v-for="(stack, index) in stacks" :key="index" class="badge bg-primary me-2">
                {{ stack }}
                <button type="button" class="btn-close btn-close-white ms-2" aria-label="Close"
                  @click="removeStackChip(stack)"></button>
              </span>
            </div>
          </div>

          <div class="input-group form-label mb-2">
            <input v-model="inputStack" class="form-control" list="datalistStack" id="stackName" />
            <button type="button" class="btn btn-outline-secondary ms-2 rounded-circle" @click="addStackChip" 
            style="border-radius: 50%;">+</button>
          </div>
          <datalist id="datalistStack">
            <option v-for="(stackData, index) in stackDatas" :key="index" :value="stackData"></option>
          </datalist>
        </div>
      </div>
    </div>
    <div class="d-flex justify-content-center m-4">
    <button class="btn btn-primary">SEND</button></div>
  </form>
</div>
</template>

<script>
// import { send } from 'vite';
import { useVuelidate } from '@vuelidate/core'
import { helpers, required, maxLength} from '@vuelidate/validators'
import {requiredMessage, maxLengthMessage} from '../plugin/validatorMessage'

export default {
  name: '',
  setup () {
    return { v$: useVuelidate() }
  },
  data() {
    return {
      
      inputStack: '',
      input: '',
      stacks: [],
      stackDatas: [],
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
  async mounted() {

    try {
      const response = await fetch("http://localhost:8080/langTechs");
      this.data = await response.json();
      this.stackDatasTemps = this.data.forEach(element => {
        this.stackDatas.push(element.langTechName)
      });
      console.log(this.data);
    } catch (error) {
      console.error("Failed to fetch data:", error);
    }
  },

  methods: {
    //creer fucntion ici
    addStackChip() {
      if(this.inputStack.trim() != "") {
        this.stacks.push(this.inputStack.trim());
      }
      this.inputStack = ''
    },
    removeStackChip(chip) {
      this.stacks = this.stacks.filter((s) => s !== chip)
    },

    getFormValues(){
      this.formData.projectName= this.inputName,
      this.formData.projectUniqueInternalId= this.inputPId,
      this.formData.projectStartDate= this.inputDate,
      this.formData.projectDescription= this.inputDesc,
      this.formData.langTechNames= this.stacks

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
        method: 'POST',
        headers: new Headers({ 'content-type': 'application/json' }),
        body: json
      }
      // try-catch to handle potential client/server communication errors
      try {
        const response = await fetch('http://localhost:8080/projects/create', options);
        if (response.status === 202) { // Expected success status code
          alert('Wow you are lucky mate, everything is okay!');
        } else { // Any other status code
          alert('A client or server error has occured!');
        }
      } catch (err) {
        alert('An unexpected error has occured!');
        console.error(err);
      }
    }
  }
}
</script>

<style scoped></style>
