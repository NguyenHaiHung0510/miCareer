/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ats.dao;

import java.sql.SQLException;

/**
 * Interface cho các DAO có CRUD đầy đủ (Create, Read, Update, Delete). Kế thừa
 * từ ReadOnlyDAO.
 *
 * @param <T> Kiểu dữ liệu của Model
 * @param <K> Kiểu dữ liệu của Khóa chính
 */
public interface CrudDAO<T, K> extends ReadOnlyDAO<T, K> {

    /**
     * Thêm một đối tượng mới vào CSDL ( chèn một bản ghi )
     *
     * @param entity Đối tượng T cần thêm.
     * @return id ( kiểu dữ liệu K ) nếu thành công, null nếu thất bại
     * @throws SQLException Nếu có lỗi SQL.
     */
    K insert(T entity) throws SQLException;

    /**
     * Cập nhật một đối tượng.
     *
     * @param entity Đối tượng T cần cập nhật.
     * @return true nếu cập nhật thành công, false nếu thất bại.
     * @throws SQLException Nếu có lỗi SQL.
     */
    boolean update(T entity) throws SQLException;

    /**
     * Xóa một đối tượng bằng Khóa chính (datatype K).
     *
     * @param id Khóa chính.
     * @return true nếu xóa thành công, false nếu thất bại.
     * @throws SQLException Nếu có lỗi SQL.
     */
    boolean delete(K id) throws SQLException;
}
