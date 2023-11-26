<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="mini" inline v-show="showSearch" label-width="5em">
      
      <el-form-item label="title" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="Please input title"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
  
  
      <div style="clear:both; margin-left: 1em;">
      <el-form-item label-width="0px">
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">Search</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">Reset</el-button>
      </el-form-item>
      </div>
    </el-form>

    <el-table v-loading="loading" :data="booksList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="bookID" align="center" prop="bookID" />
      <el-table-column label="title" align="center" prop="title" />      
      <el-table-column label="Operation" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleReturnBook(scope.row)"
          >return</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <Pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改lib_books对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="title" prop="title">
          <el-input v-model="form.title" placeholder="Please input title" />
        </el-form-item>
        <el-form-item label="author" prop="author">
          <el-input v-model="form.author" placeholder="Please input author" />
        </el-form-item>
        <el-form-item label="theDate" prop="theDate">
          <el-date-picker clearable
            v-model="form.theDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="Please choose theDate">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="publisher" prop="publisher">
          <el-input v-model="form.publisher" placeholder="Please input publisher" />
        </el-form-item>
        <el-form-item label="inventoryQuantity" prop="inventoryQuantity">
          <el-input v-model="form.inventoryQuantity" placeholder="Please input inventoryQuantity" />
        </el-form-item>
        <el-form-item label="lendingQuantity" prop="lendingQuantity">
          <el-input v-model="form.lendingQuantity" placeholder="Please input lendingQuantity" />
        </el-form-item>
        <el-form-item label="position" prop="position">
          <el-input v-model="form.position" placeholder="Please input position" />
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>

import {listMyBooks, returnABook} from "@/api/books"
import { parseTime } from "@/assets/utils/ruoyi"
import Pagination from '@/components/Pagination/index'
import RightToolbar from '@/components/RightToolbar/index'
export default {
  title: "ReturnBooks",
  components: {
    Pagination,
    RightToolbar
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // lib_books表格数据
      booksList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层title
      open: false,
      // position时间范围
      daterangeThedate: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        ISBN: null,
        title: null,
        author: null,
        theDate: null,
        publisher: null,
        inventoryQuantity: null,
        lendingQuantity: null,
        position: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        ISBN: [
          { required: true, message: "ISBN can't be empty", trigger: "blur" }
        ],
        title: [
          { required: true, message: "title can't be empty", trigger: "blur" }
        ],
        author: [
          { required: true, message: "author can't be empty", trigger: "blur" }
        ],
        theDate: [
          { required: true, message: "theDate can't be empty", trigger: "blur" }
        ],
        publisher: [
          { required: true, message: "publisher can't be empty", trigger: "blur" }
        ],
        inventoryQuantity: [
          { required: true, message: "inventoryQuantity can't be empty", trigger: "blur" }
        ],
        position: [
          { required: true, message: "position can't be empty", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    handleReturnBook(row){
      let params = {}
      params.name = localStorage.name
      params.bookID = row.bookID;
      returnABook(params).then(response=>{
        if(response.data.code==200){
          this.$message("Return the book successfully");
          this.getList()
        }else{
          this.$message.error("Failed to return the book");
        } 
      })
    },

    parseTime(time, pattern)  {
      let timestr = parseTime(time, pattern)
      console.log('parseTime:', time, 'to', timestr)
      return timestr 
    },
    /** 查询lib_books列表 */
    getList() {
      this.loading = true;
      let queryParams = {}
      queryParams.userName = localStorage.name
      queryParams.title = this.queryParams.title
      console.log("输入的数据是", queryParams)

      listMyBooks(queryParams).then(response => {
        if (response.data.code==200){
          this.booksList = response.data.data;
          //this.total = response.data.data.length;          
          console.log('list my borrowed books:', response.data.data,'\ntotal:', this.total)
        } else {
          this.$message('Failed to list my borrowed books.')
        }
        
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        ISBN: null,
        title: null,
        author: null,
        theDate: null,
        publisher: null,
        inventoryQuantity: null,
        lendingQuantity: null,
        position: null
      };
      // this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeThedate = [];
      // this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.ISBN)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
  }
};
</script>

<style scoped>
  .el-form-item{
    width:24%;
    float:left;
  }
</style>