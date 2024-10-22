<script>
import { RouterView } from 'vue-router';
import Header from './components/Header.vue';

export default {
  name: 'App',
  components: {
    Header
  }
};
</script>
<template>
  <header>
    <Header />
  </header>
  <main>
    <RouterView/>
  </main>
</template>

<style scoped>

</style>
