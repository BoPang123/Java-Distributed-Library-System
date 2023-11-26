<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="mini" inline v-show="showSearch" label-width="5em">
      <el-form-item label="ISBN" prop="ISBN">
        <el-input
          v-model="queryParams.ISBN"
          placeholder="Please input ISBN"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="title" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="Please input title"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="author" prop="author">
        <el-input
          v-model="queryParams.author"
          placeholder="Please input author"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="publisher" prop="publisher" :span="8">
        <el-input
          v-model="queryParams.publisher"
          placeholder="Please input publisher"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="inventory" prop="inventoryQuantity" :span="8">
        <el-input
          v-model="queryParams.inventoryQuantity"
          placeholder="Please input inventoryQuantity"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="lending" prop="lendingQuantity" v-if="false">
        <el-input
          v-model="queryParams.lendingQuantity"
          placeholder="Please input lendingQuantity"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="position" prop="position">
        <el-input
          v-model="queryParams.position"
          placeholder="Please input position"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="theDate" style="width:auto;"  v-if="false">
        <el-date-picker
          v-model="daterangeThedate"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="Start date"
          end-placeholder="End date"
        ></el-date-picker>
      </el-form-item>
      <div style="clear:both; margin-left: 1em;">
      <el-form-item label-width="0px">
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">Search</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">Reset</el-button>
      </el-form-item>
      </div>
    </el-form>

    <el-row :gutter="10" class="mb8" style="clear:both">
      
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="booksList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ISBN" align="center" prop="ISBN" :width="130"/>
      <el-table-column label="title" align="center" prop="title" :width="180"/>
      <el-table-column label="author" align="center" prop="author" :width="150"/>
      <el-table-column label="theDate" align="center" prop="theDate" :width="140">
        <template slot-scope="scope">
          <!-- span>{{ parseTime(scope.row.theDate, '{y}-{m}-{d}') }}</span-->
          <span>{{scope.row.theDate}}</span>
        </template>
      </el-table-column>
      <el-table-column label="publisher" align="center" prop="publisher" :width="150"/>
      <el-table-column label="inventoryQuantity" align="center" prop="inventoryQuantity" />
      <el-table-column label="lendingQuantity" align="center" prop="lendingQuantity" />
      <el-table-column label="position" align="center" prop="position" />
      <el-table-column label="Operation" align="center" class-title="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleborrow(scope.row)"
          >Borrow</el-button>
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
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">Determine</el-button>
        <el-button @click="cancel">Cancel</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

import { listBooks, getBooks, delBooks, addBooks, updateBooks, borrow } from "@/api/books"
import { parseTime } from "@/assets/utils/ruoyi"
import Pagination from '@/components/Pagination/index'
import RightToolbar from '@/components/RightToolbar/index'
import subList from '@/assets/utils/common.js'

export default {
  title: "Books",
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
      // 是否显示弹出层
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
    handleborrow(row){
      let name = localStorage.name
      console.log("这个时候的name是", name)
      console.log("ISBN:", row.ISBN, "name:", name)
      borrow(row.ISBN, name).then(response=>{
        console.log("response:", response)
        if(response.data.code==200){
          this.$message("Borrow the book successfully");
          this.getList()
        }else{
          this.$message.error("Failed to borrow the book");
        }
      })
    },

    parseTime(time, pattern)  {
      let timestr = parseTime(time, pattern)
      // console.log('parseTime:', time, 'to', timestr)
      return timestr 
    },
    /** 查询lib_books列表 */
    getList() {
      this.loading = true;
      //this.queryParams.params = {};
      if (null != this.daterangeThedate && '' != this.daterangeThedate) {
        this.queryParams.params["beginThedate"] = this.daterangeThedate[0];
        this.queryParams.params["endThedate"] = this.daterangeThedate[1];
      }
      console.log('this.queryParams:', this.queryParams)

      listBooks(this.queryParams).then(response => {
        console.log('response:', response,'\ntotal:', this.total)       
        if(response.data.code==200) {
          console.log('response.data:', response.data)

          this.booksList = subList( response.data.data, (this.queryParams.pageNum-1)*this.queryParams.pageSize, this.queryParams.pageNum*this.queryParams.pageSize)
          this.total = response.data.data.length;
          this.loading = false;
        }else {
          this.message.error(response.data.data)
        }
 
      }).catch(e=>{
        this.loading = false;
        this.$message.error('did not get response from server.')
        console.log('response:', e)
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
      this.getList(this.queryParams);
      console.log("搜索的数据是", this.queryParams)
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加lib_books";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const ISBN = row.ISBN || this.ids
      getBooks(ISBN).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改lib_books";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.ISBN != null) {
            updateBooks(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBooks(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const isbns = row.ISBN || this.ids;
      this.$modal.confirm('是否确认删除lib_books编号为"' + isbns + '"的数据项？').then(function() {
        return delBooks(isbns);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('library/books/export', {
        ...this.queryParams
      }, `books_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

<style scoped>
  .el-form-item{
    width:32%;
    float:left;
  }
</style>