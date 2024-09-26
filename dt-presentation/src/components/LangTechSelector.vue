<template>
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
                <input v-model="inputStack" class="form-control" list="datalistStack" id="stackName" autocomplete="off" v-on:input="fetchLangTech"/>
                <button type="button" class="btn btn-outline-secondary ms-2 rounded-circle" @click="addStackChip" 
                style="border-radius: 50%;">+</button>
            </div>
            <datalist id="datalistStack">
                <option v-for="(stackData, index) in stackDatas" :key="index" :value="stackData"></option>
            </datalist>
        </div>
    </div>
</template>

<script>

export default {
    data() {
        return {
            inputStack: '',
            stacks: [],
            stackDatas: []
        }
    },
    props:{
  stacksToProps: {
    type: Array,
    default: []
  }
},

    mounted() {
        // this.getAllLangTechs()
        this.stacks= this.stacksToProps;
    },
    emits: ['add-langTech','rm-langTech'],
    methods: {
        async getAllLangTechs() {
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

        async fetchLangTech() {// fetch top 5 alphabetical on each input
            if(this.inputStack == '') { return };
            try {
            const response = await fetch("http://localhost:8080/langTechs/"+this.inputStack);
            this.data = await response.json();
            this.stackDatas = [];
            this.stackDatasTemps = this.data.forEach(element => {
                this.stackDatas.push(element.langTechName)
            });
                console.log(this.data);
            } catch (error) {
                console.error("Failed to fetch data:", error);
            }
        },

        addStackChip() {
            this.$emit('add-langTech', this.inputStack)
            if(this.inputStack.trim() != "") {
                this.stacks.push(this.inputStack.trim());
            }
            this.inputStack = ''
        },

        removeStackChip(chip) {
            this.$emit('rm-langTech', chip)
            this.stacks = this.stacks.filter((s) => s !== chip)
        }
    }
}
</script>

<style scoped></style>
