<template>
  <div>
    <h2>InterestArea</h2>
    <div class="container">
      <b-row>
        <b-col lg="3">
          <router-view></router-view>
        </b-col>
        <b-col>
          <basic-map></basic-map>
        </b-col>
      </b-row>
    </div>
    <footer-bar></footer-bar>
  </div>
</template>

<script>
import FooterBar from "@/components/FooterBar.vue";
import BasicMap from "@/components/BasicMap.vue";
export default {
  components: { FooterBar, BasicMap },
  comments: { FooterBar },
};
</script>

<style></style>
