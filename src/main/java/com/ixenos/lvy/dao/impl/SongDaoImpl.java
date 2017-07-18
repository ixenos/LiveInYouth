package com.ixenos.lvy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ixenos.lvy.bean.Song;
import com.ixenos.lvy.dao.SongDao;
import com.ixenos.lvy.util.JdbcUtil;

/**
 * 歌曲表的DAO操作
 * 
 * @author ixenos
 *
 */
public class SongDaoImpl implements SongDao {

	/**
	 * 通过song表Id获取song对象
	 * 
	 * @param songId
	 *            song表id
	 * @return Song 歌曲表对象
	 */
	@Override
	public Song getSongById(int songId) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		Song song = null;
		String sql = "SELECT * FROM SONG WHERE SONG_ID = ?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, songId);
			rs = preStmt.executeQuery();
			while (rs.next()) {
				song = new Song();
				song.setSongId(songId);
				song.setAlbumId(rs.getInt("ALBUM_ID"));
				song.setAlbumName(rs.getString("ALBUM_NAME"));
				song.setArtistId(rs.getInt("ARTIST_ID"));
				song.setArtistName(rs.getString("ARTIST_NAME"));
				song.setCopyright(rs.getInt("COPYRIGHT"));
				song.setMediaType(rs.getInt("MEDIA_TYPE"));
				song.setSoleCopyright(rs.getInt("SOLE_COPYRIGHT"));
				song.setSongName(rs.getString("SONG_NAME"));
				song.setSongSrc(rs.getString("SONG_SRC"));
				song.setThumpUpCount(rs.getInt("THUMP_UP_COUNT"));
				song.setClickCount(rs.getInt("CLICK_COUNT"));
				song.setSongListViaCount(rs.getInt("SONG_LIST_VIA_COUNT"));
				song.setCommentCount(rs.getInt("COMMENT_COUNT"));
				song.setSongListIdSet(rs.getString("SONG_LIST_ID_SET"));
				System.out.println(song.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, preStmt, conn);
		}
		return song;
	}

	/**
	 * 通过song表Id获取songSrc
	 * 
	 * @param songId
	 * @return
	 */
	@Override
	public String getSrcById(int songId) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String sql = "SELECT SONG_SRC FROM SONG WHERE SONG_ID = ?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, songId);
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
	
	/**
	 * 通过songId获取歌曲对应其他表的ID
	 * 
	 * @param songId song表id
	 * @param otherIdName	其他表
	 * @return
	 */
	@Override
	public int getOtherIdById(int songId, String otherIdName) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		//注意otherIdName左右要有空格
		String sql = "SELECT " + otherIdName + " FROM SONG WHERE SONG_ID=?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, songId);
			rs = preStmt.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 通过songId获取song名字
	 * @param songId
	 * @return
	 */
	@Override
	public String getNameById(int songId) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String sql = "SELECT SONG_NAME FROM SONG WHERE SONG_ID = ?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, songId);
			rs = preStmt.executeQuery();
			if(rs.next()){
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 通过song bean来更新song
	 * @param song
	 * @return
	 */
	@Override
	public boolean updateSongByBean(Song song) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		String sql = "UPDATE SONG SET SONG_NAME=?, MEDIA_TYPE=?, SONG_LIST_ID_SET=?, ARTIST_ID=?, ARTIST_NAME=?, ALBUM_ID=?, ALBUM_NAME=?, SONG_SRC=?, COPYRIGHT=?, SOLE_COPYRIGHT=?, THUMP_UP_COUNT=?, CLICK_COUNT=?,SONG_LIST_VIA_COUNT=?,COMMENT_COUNT=? WHERE SONG_ID=?";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, song.getSongName());
			preStmt.setInt(2, song.getMediaType());
			preStmt.setString(3, song.getSongListIdSet());
			preStmt.setInt(4, song.getArtistId());
			preStmt.setString(5, song.getArtistName());
			preStmt.setInt(6, song.getAlbumId());
			preStmt.setString(7, song.getAlbumName());
			preStmt.setString(8, song.getSongSrc());
			preStmt.setInt(9, song.getCopyright());
			preStmt.setInt(10, song.getSoleCopyright());
			preStmt.setInt(11, song.getThumpUpCount());
			preStmt.setInt(12, song.getClickCount());
			preStmt.setInt(13, song.getSongListViaCount());
			preStmt.setInt(14, song.getCommentCount());
			preStmt.setInt(15, song.getSongId());
			int i = preStmt.executeUpdate();
			if (i != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(preStmt, conn);
		}
		return false;
	}
	
	
	/**
	 * 创建新的歌曲
	 * @return 返回自增长生成的id
	 */
	@Override
	public int createSong() {
		Connection conn = null;
		PreparedStatement preStmt = null;
		PreparedStatement tmpStmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO SONG (SONG_ID) VALUES (?)";
		String sql2 = "SELECT SONG_ID FROM SONG WHERE SONG_NAME IS NULL";
		try {
			conn = JdbcUtil.getConnection();
			preStmt = conn.prepareStatement(sql);
			preStmt.setInt(1, 0);//set0时id默认自增
			int i = preStmt.executeUpdate();
			if(i!=0){
				tmpStmt = conn.prepareStatement(sql2);
				rs = tmpStmt.executeQuery();
				if(rs.next()){
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(tmpStmt != null){
				try {
					tmpStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			JdbcUtil.close(rs, preStmt, conn);
		}
		return -1;
	}
	
	
	
}
