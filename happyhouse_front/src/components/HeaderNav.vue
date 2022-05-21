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
            <li class="nav-item">매물 정보</li>
            <li class="nav-item">관심매물</li>
            <li class="nav-item">관심지역</li>
            <li class="nav-item">LOGIN</li>
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
