/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ats.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface cho các DAO chỉ có thao tác đọc
 *
 * @param <T> Kiểu dữ liệu của Model
 * @param <K> Kiểu dữ liệu của Khóa chính
 */
public interface ReadOnlyDAO<T, K> {

    /**
     * Lấy tất cả các đối tượng T.
     *
     * @return Danh sách các đối tượng.
     * @throws SQLException Nếu có lỗi SQL.
     */
    List<T> getAll() throws SQLException;

    /**
     * Tìm một đối tượng T bằng Khóa chính (K).
     *
     * @param id Khóa chính.
     * @return Đối tượng T nếu tìm thấy, ngược lại trả về null.
     * @throws SQLException Nếu có lỗi SQL.
     */
    T getById(K id) throws SQLException;
}
