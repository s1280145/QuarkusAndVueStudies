import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import IndexItem from "@/views/IndexItem";
import CreateItem from "@/views/CreateItem";
import EditItem from "@/views/EditItem";

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/index',
    name: 'index',
    component: IndexItem
  },
  {
    path: '/create',
    name: 'create',
    component: CreateItem
  },
  {
    path: '/edit/:id',
    name: 'edit',
    component: EditItem
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
