<template>
  <div class="rank">
    <el-table :data="movies" stripe style="width: 100%;">
      <el-table-column prop="poster" label="海报" width="250%">
        <template slot-scope="scope">
          <img :src="scope.row.poster" height="150px">
        </template>
      </el-table-column>
      <el-table-column prop="title" label="电影" width="400%"></el-table-column>
      <el-table-column prop="year" label="年份" width="150%"></el-table-column>
      <el-table-column prop="rating.average" label="评分" width="150%"></el-table-column>

      <el-table-column>
        <template slot="header" slot-scope="scope">
          <input v-model="search" placeholder="输入关键字搜索" @keyup.enter="handleSearch">
          <el-button icon="el-icon-search" circle size="mini" @click="handleSearch"></el-button>
        </template>
        <template slot-scope="scope">
          <el-button size="mini" @click="handleLookdetail(scope.$index, scope.row)">查看详情</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination">
      <el-pagination
        layout="total, prev, pager, next, jumper"
        :total="movieCount"
        :page-size="10"
        :current-page="currentPage"
        @current-change="handlePageChange"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "rank",
  data() {
    return {
      searchData: [],
      search: "",
      currentPage: 1,
      fullData: true,
      movies: [],
      movieCount: 0
    };
  },
  mounted() {
    var self = this;
    this.$axios
      .get("/movie/getMovieCount")
      .then(response => {
        self.movieCount = response.data;
      })
      .catch(() => {
        self.movieCount = 0;
      });
    this.getAll();
  },
  methods: {
    handleLookdetail(index, row) {
      console.log(row);
      this.$router.push({ name: "detail", params: { id: row.id } });
    },
    getAll() {
      var self = this;
      if (this.fullData) {
        this.$axios
          .get("/movie/getAll", {
            params: {
              page: self.currentPage - 1
            }
          })
          .then(response => {
            self.movies = response.data;
          })
          .catch(error => {
            self.$message.error("刷新数据失败\n" + error);
          });
      }
    },
    getSearch() {
      var self = this;
      this.$axios
        .get("/movie/search", {
          params: {
            keyword: self.search,
            page: self.currentPage - 1
          }
        })
        .then(response => {
          self.movies = response.data.movies;
          self.movieCount = response.data.count;
        })
        .catch(error => {
          self.$message.error("获取结果失败\n" + error);
        });
    },
    handlePageChange(page) {
      this.currentPage = page;
      if (this.fullData) {
        this.getAll();
      } else {
        this.getSearch();
      }
    },
    handleSearch() {
      this.currentPage = 1;
      if (this.search == "") {
        this.fullData = true;
        location.reload();
        return;
      }
      this.fullData = false;
      this.getSearch();
    }
  }
};
</script>

<style>
input {
  border-radius: 25px;
  height: 20px;
  padding: 3px;
  border: none;
  outline: none;
  background-color: whitesmoke;
}
input:hover {
  background-color: #40a0ff13;
}
</style>
