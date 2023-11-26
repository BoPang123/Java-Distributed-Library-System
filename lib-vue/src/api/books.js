
import axios from 'axios'

const url= '/books';

const http = axios;
// 查询lib_books列表
export function listBooks(query) {
  return http({
    'url': url,
    'method': 'get',
    'params': query
  })
}

// 查询lib_books详细
export function getBooks(isbn) {
  return http({
    url: url.modify + '/' + isbn,
    method: 'get'
  })
}

// 新增lib_books
export function addBooks(data) {
  return http({
    url: '/books',
    method: 'post',
    data: data
  })
}

// 修改lib_books
export function updateBooks(data) {
  return http({
    url: '/books',
    method: 'put',
    data: data
  })
}

// 删除lib_books
export function delBooks(isbn) {
  return http({
    url: '/books/' + isbn,
    method: 'delete'
  })
}

export function borrow(ISBN, userName) {
  return http({
    url: '/books/borrow',
    method: 'get',
    params: {
      'ISBN': ISBN,
      'userName': userName
    }
  })
}

export function listMyBooks(params) {
  return http({
    url: '/books/borrowed',
    method: 'get',
    params: params
   })
}

export function returnABook(params) {
  return http({
    url: '/books/return',
    method: 'get',
    params: params
   })
}
