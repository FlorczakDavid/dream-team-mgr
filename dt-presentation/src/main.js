//import './assets/main.css'
//import 'bootstrap/dist/css/bootstrap.css'

import { createApp } from 'vue'
import { createI18n } from 'vue-i18n'
import en from './i18N/en.json';
import fr from './i18N/fr.json';
import router from './router';

import App from './App.vue'

// Détection de la langue du navigateur
const browserLanguage = navigator.language.startsWith('fr') ? 'fr' : 'en';

// Configuration de Vue I18n
const i18n = createI18n({
  locale: browserLanguage,
  // locale: 'fr',
  fallbackLocale: 'en',
  messages: {
    en,
    fr
  }
});


createApp(App).use(router).use(i18n).mount('#app');
