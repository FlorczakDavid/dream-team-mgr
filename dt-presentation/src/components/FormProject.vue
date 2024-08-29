<template>
  <h1 class="text-center mt-3">Add your project to Dream Team Platforme</h1>
  <div class="container bg-light">
  <form  id="project-form" @submit.prevent="getFormValues" class="card d-flex flex-column mx-3">
    <div class="mb-3">
      <label for="projectName" class="form-label">Project Name <span>*</span>:</label>
      <input type="text" required class="form-control" id="projectName" maxlength="200"
        placeholder="max 200 characters" name="name">
    </div>

    <div class="mb-3">
      <label for="projectId" class="form-label">Project Id <span>*</span>:</label>
      <input type="text" required class="form-control" id="projectId" maxlength="100" placeholder="max 100 characters" name="projectId">
    </div>

    <div class="mb-3">
      <label for="releaseDate" class="form-label">Release Date <span>*</span>:</label>
      <input type="date" required class="form-control" id="releaseDate" name="releaseDate">
    </div>

    <div class="mb-3">
      <label for="description" class="form-label">Description:</label>
      <textarea class="form-control" id="description" maxlength="5000" rows="3" name="desc"></textarea>
      <span class="pull-right label label-default" id="count_message"></span>
    </div>

    <div class="mb-3">
      <label for="stackName" class="form-label mb-2 mt-4">Languages and technologies</label>
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
    <div class="d-flex justify-content-center mt-3">
    <button class="btn btn-primary">SEND</button></div>
  </form>
</div>
</template>

<script>
// import { send } from 'vite';

export default {
  name: '',
  data() {
    return {
      inputStack: '',
      input: '',
      stacks: [],
      stackDatas: [],
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
      this.stacks.push(this.inputStack);
      this.inputStack = ''
    },
    removeStackChip(chip) {
      this.stacks = this.stacks.filter((s) => s !== chip)
    },

    getFormValues(submitEvent){
      this.formData.projectName= submitEvent.target.elements.name.value,
      this.formData.projectUniqueInternalId= submitEvent.target.elements.projectId.value,
      this.formData.projectStartDate= submitEvent.target.elements.releaseDate.value,
      this.formData.projectDescription= submitEvent.target.elements.desc.value,
      this.formData.langTechNames= this.stacks
      this.send(this.formData)
    },

    async send(data) {
      // const form = document.querySelector('#project-form');
      // //   // Form's fields as key/value pairs
      // const formData = new FormData(form);
      // // Convert form data as JS object
      // console.log(formData);
      // const object = {};
      // formData.forEach((value, key) => object[key] = value);
      // Convert JS object as JSON to send in request body
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
        // if (response.status === 202) { // Expected success status code
        //   form.reset();
        //   alert('Wow you are lucky mate, everything is okay!');
        // } else { // Any other status code
        //   alert('A client or server error has occured!');
        // }
      } catch (err) {
        alert('An unexpected error has occured!');
        console.error(err);
      }

      // form.addEventListener('submit', async (event) => {
      //   event.preventDefault();
      //   await send();
      // });
    }
  }
}
</script>

<style scoped></style>
