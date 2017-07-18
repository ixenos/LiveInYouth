package com.ixenos.lvy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ixenos.lvy.dao.AlbumDao;
import com.ixenos.lvy.util.JdbcUtil;

/**
 * ALBUM表的DAO
 * 
 * @author ixenos
 *
 */
public class AlbumDaoImpl implements AlbumDao {

	/**
	 * 通过albumId从album表中取专辑图片链接
	 * 
	 * @param albumId album表id
	 * @return albumImgSrc 专辑图片链接
	 */
	@Override
	public String getAlbumImgSrcById(int albumId) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String sql = "SELECT ALBUM_IMG_SRC FROM ALBUM WHERE ALBUM_ID = ?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, albumId);
			rs = preStmt.executeQuery();
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, preStmt, conn);
		}
		return null;
	}

}
