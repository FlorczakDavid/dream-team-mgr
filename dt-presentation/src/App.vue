<template>
  <header>
    <Header />
  </header>
  <main>
    <!-- <FormProject /> -->
    <UpdateProject />
  </main>
</template>

<script>
import FormProject from './components/FormProject.vue';
import Header from './components/Header.vue';
import UpdateProject from './components/UpdateProject.vue';

export default {
  name: 'App',
  components: {
    Header,
    FormProject,
    UpdateProject
  }
};
</script>

<style scoped>

</style>
