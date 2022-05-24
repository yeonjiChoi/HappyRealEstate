<template>
  <b-media
    tag="li"
    @click="moveToNews"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <h5 v-html="news.title" class="mt-0 mb-1"></h5>
    <p v-html="news.description" class="mb-0"></p>
    <p class="mb-0">
      {{ news.pubDate | date }}
    </p>
  </b-media>
</template>

<script>
export default {
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    news: Object,
  },
  methods: {
    moveToNews() {
      window.open(this.news.originallink, "_blank");
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
  filters: {
    date(value) {
      return value.slice(0, 16);
    },
  },
};
</script>

<style>
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
