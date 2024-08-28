<template>
  <h1 class="text-center mt-3">Add your project to Dream Team Platforme</h1>
  <form class="card d-flex flex-column m-5 p-4">
    <div class="mb-3">
      <label for="exampleFormControlInput1" class="form-label">Project Name <span>*</span>:</label>
      <input type="text" class="form-control" id="exampleFormControlInput1" maxlength="200"
        placeholder="max 200 characters">
    </div>
    
    <div class="mb-3">
      <label for="exampleFormControlInput1" class="form-label">Project Id <span>*</span>:</label>
      <input type="text" class="form-control" id="exampleFormControlInput2" maxlength="100"
        placeholder="max 100 characters">
    </div>
    
    <div class="mb-3">
      <label for="exampleFormControlInput1" class="form-label">Release Date <span>*</span>:</label>
      <input type="date" class="form-control" id="exampleFormControlInput3">
    </div>
    
    <div class="mb-3">
      <label for="exampleFormControlTextarea1" class="form-label">Description:</label>
      <textarea class="form-control" id="exampleFormControlTextarea1" maxlength="5000" rows="3"></textarea>
      <span class="pull-right label label-default" id="count_message"></span>
    </div>

    <div class="mb-3">
      <label for="personalInfo" class="form-label mb-2 mt-4">Languages and technologies</label>
      <div class="card bg-light">
        <div class="card-body d-flex flex-column">
          <div class="input-group form-label mb-2 mt-4">
            <label for="exampleFormControlTextarea1" class="form-label">Stack list: </label>
            <div>{{ input }}</div>
            
            <!-- <input type="text" class="form-control" /> -->
          </div>

          <div class="input-group form-label mb-2 mt-4">
          <label for="exampleFormControlTextarea1" class="form-label">Stack name: </label>
          <input class="form-control" list="datalistTechnicalSkills" id="inputSkills" />
          <button type="button" class="btn btn-dark" @click="getValue()">Add</button>
          </div>
          <datalist id="datalistTechnicalSkills">
            <option value="JAVA"></option>
            <option value="JS"></option>
            <option value="Jenkins"></option>
          </datalist>
        </div>
      </div>
    </div>
    <button type="submit" class="btn btn-primary mb-3">CREATE</button>
  </form>
</template>

<script>
export default {
  name: '',
  data() {
    return {

    }
  },
  methods: {
    //creer fucntion ici
    getValue(){
      let input = document.getElementById('inputSkills').value;
      console.log(input);
    }
  }
}

</script>

<style scoped></style>
