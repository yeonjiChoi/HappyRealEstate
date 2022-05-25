<template>
  <!-- <b-media
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
  </b-media> -->
  <b-row class="mt-2">
    <b-card align="left" @click="moveToNews">
      <h5><b-card-text v-html="news.title"> </b-card-text></h5>
      <hr />
      <b-card-text v-html="news.description"> </b-card-text>
      <small class="text-left"> {{ news.pubDate | date }} </small>
    </b-card>
  </b-row>
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

<style scoped>
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
