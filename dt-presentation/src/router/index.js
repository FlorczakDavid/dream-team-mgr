import {createRouter, createWebHistory} from "vue-router";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/createProject",
            name: "Create Project",
            component: () => import("../components/FormProject.vue"),
        },
        {
            path: "/updateProject/:id",
            name: "Update Project",
            component: () => import("../components/UpdateProject.vue")
        }
    ],
});

export default router;