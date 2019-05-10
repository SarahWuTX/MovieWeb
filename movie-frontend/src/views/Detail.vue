<template>
  <div class="detail">
    <!-- <h5>this is detail of movie : {{this.$route.params.id}}</h5> -->
    <!-- <h1>{{movie.poster}}</h1> -->
    <el-container>
      <el-aside style="margin: 0 50px">
        <poster :imgUrl="movie.poster"></poster>
      </el-aside>
      <el-main>
        <h2 style="text-align: left">{{movie.title}} ({{movie.year}})</h2>
        <div class="info-item">
          <div id="rating-box">
            <div class="rating">
              <div id="score">{{movie.rating.average}}</div>
              <el-rate v-model="avg" disabled :score-template="movie.rating.average"></el-rate>
              <p>已有{{movie.rating.rating_people}}人打分</p>
            </div>
            <div class="rating">
              <div v-for="i in 5" :key="i" style="height: 20px;">
                <el-rate :value="6-i" disabled text-color="#F7BA2A" :score-template="6-i"></el-rate>
              </div>
            </div>
            <div class="rating">
              <div v-for="i in 5" :key="i" style="height: 20px;">
                <el-progress :percentage="movie.rating.stars[i-1]" color="#F7BA2A"></el-progress>
              </div>
            </div>
          </div>
        </div>
        <div style="display: flex;">
          <div>
            <info-item :title="'导演'" :item="movie.directors" :hasId="true"></info-item>
            <info-item :title="'演员'" :item="movie.casts" :hasId="true"></info-item>
            <info-item :title="'编剧'" :item="movie.writers" :hasId="true"></info-item>
            <info-item :title="'类型'" :item="movie.genres" :hasId="false"></info-item>
            <info-item :title="'国家'" :item="movie.countries" :hasId="false"></info-item>
            <info-item :title="'语言'" :item="movie.languages" :hasId="false"></info-item>
            <info-item :title="'时长'" :item="[movie.duration]" :hasId="false"></info-item>
            <info-item :title="'上映日期'" :item="movie.pubdate" :hasId="false"></info-item>
          </div>
          <div class="summary">
            <h3>简介</h3>
            <div>{{movie.summary}}</div>
          </div>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import Poster from "../components/Poster";
import InfoItem from "../components/InfoItem";

export default {
  name: "detail",
  components: {
    Poster,
    InfoItem
  },
  data() {
    return {
      rating_level: [5, 4, 3, 2, 1],
      movie: {}
    };
  },
  mounted() {
    var self = this;
    this.$axios
      .get("/movie/get", {
        params: {
          id: self.$route.params.id
        }
      })
      .then(response => {
        console.log(response.data);
        self.movie = response.data;
      })
      .catch(error => {
        self.$message.error("获取数据失败\n" + error);
      });
  },
  computed: {
    avg() {
      return (this.movie.rating.average / 2).toFixed(1);
    }
  }
};
</script>

<style scoped>
.detail {
  padding: 0 0 0 100px;
}

#rating-box {
  width: 70%;
  display: flex;
  box-shadow: 0px 3px 5px lightgray;
  padding: 15px;
  margin: 5px 0;
}

#score {
  font-size: 35px;
  color: #f7ba2a;
}

.rating {
  margin: 0 10px;
  width: 300px;
}

li {
  list-style: none;
}

.el-main {
  padding: 0;
}

.summary h3 {
  text-align: center;
}

.summary {
  margin: 0 20px;
  width: 400px;
  background-color: whitesmoke;
  text-align: left;
  font-size: 15px;
  padding: 0 30px;
}
</style>
