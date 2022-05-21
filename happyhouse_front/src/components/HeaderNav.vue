<template>
  <div>
    <header class="navbar navbar-light bg-light">
      <div class="container">
        <router-link to="/">
          <img src="@/assets/happyhouse.png" alt="logo" class="navbar-brand" />
        </router-link>
        <div>
          <ul class="nav mr-auto">
            <li class="nav-item">
              <router-link :to="{ name: 'notice' }" class="link"
                >공지사항</router-link
              >
            </li>
            <li class="nav-item">
              <router-link :to="{ name: 'QnA' }">QnA</router-link>
            </li>
            <li class="nav-item">
              <router-link :to="{ name: 'House' }"> 매물 정보 </router-link>
            </li>
            <li class="nav-item">
              <router-link :to="{ name: 'InterestHouse' }"
                >관심 매물</router-link
              >
            </li>
            <li class="nav-item">
              <router-link :to="{ name: 'InterestArea' }"
                >관심 지역</router-link
              >
            </li>
          </ul>
        </div>
      </div>
    </header>
  </div>
</template>

<script>
export default {};
</script>

<style>
.navbar-brand {
  height: 50px;
  width: 50px;
}
.nav-item {
  color: black;
  padding: 5px;
}
</style>
