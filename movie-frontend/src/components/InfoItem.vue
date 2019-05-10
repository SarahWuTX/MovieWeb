<template>
  <div class="info-item">
    <div class="item-title">
      <strong>{{ title }}</strong>
    </div>
    <div class="item-content">
      <li v-for="ele in item">
        <p v-if="hasId">
          <a
            :href="'https://movie.douban.com/celebrity/'+ ele.id +'/'"
            target="_blank"
          >{{ ele.name }}</a>
        </p>
        <p v-else>{{ ele }}</p>
      </li>
    </div>
  </div>
</template>

<script>
export default {
  name: "info-item",
  props: {
    title: String,
    item: Array,
    hasId: Boolean
  }
};
</script>

<style>
.info-item {
  display: flex;
  margin: 5px;
}

li {
  list-style: none;
}

.item-content p {
  text-align: left;
  margin: 0 20px;
}
</style>
