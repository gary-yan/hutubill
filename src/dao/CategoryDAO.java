
package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Category;
import util.DBUtil;

public class CategoryDAO {
	// 获取Category表中有多少行信息 统计总数
	public int getTotal() {
		int total = 0;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
			String sql = "select count(*) from category";
			// COUNT(*) 函数返回在给定的选择中被选的行数
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				total = rs.getInt(1);
			}
			System.out.println("total:" + total);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	//增加
	public void add(Category category) {
		String sql = "insert into category values(null,?)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setString(1, category.name);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				category.id = id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//更新
	public void update(Category category) {
		String sql = "update category set name= ? where id= ?";// 空格对sql语句是否有影响
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setString(1, category.name);
			ps.setInt(2, category.id);

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	//删除
	public void delete(int id) {

		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
			String sql = "delete from category where id = " + id;
			s.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//获取
	public Category get(int id) {
		Category category = null;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
			String sql = "select * from category where id =" + id;
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {
				category = new Category();
				String name = rs.getString(2);//?? 1代表id 2 代表name
				category.name = name;
				category.id = id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}
	//查询所有
	public List<Category> list() {
		return list(0, Short.MAX_VALUE);
	}
	//分页查询
	public List<Category> list(int start, int count) {
		List<Category> categorys = new ArrayList<Category>();
		String sql = "select * from category order by id desc limit ?,?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, start);
			ps.setInt(2, count);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				category.id = id;
			
				category.name = name;
				categorys.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorys;
	}

//	public Category getByKey(String key) {
//		  Category category = null;
//		  String sql = "select * from category where key_ = ?";
//		  try(Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);)
//		  {
//			  ps.setString(1, key);
//			  ResultSet rs = ps.executeQuery();
//			  
//			  if(rs.next()) {
//				  category = new Category();
//				  int id = rs.getInt("id");
//				  String value = rs.getString("value");
//				  category.key = key;
//				  category.value = value;
//				  category.id = id;
//			  }
//			  }catch (SQLException e) {
//				  e.printStackTrace();
//			  }
//			return category;
//}

}
