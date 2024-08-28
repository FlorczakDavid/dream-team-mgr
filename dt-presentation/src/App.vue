<template>
  <header>
    <Header />
  </header>
  <main>
    <FormProject />
  </main>
</template>

<script>
import FormProject from './components/FormProject.vue';
import Header from './components/Header.vue';

export default {
  name: 'App',
  components: {
    Header,
    FormProject
  }
};
</script>

<style scoped>

</style>
