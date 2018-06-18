package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Record;
import util.DBUtil;
import util.DateUtil;

public class RecordDAO {

	public int getTotal() {
		int total = 0;
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
			String sql = "select count(*) from record";
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

	public void add(Record record) {
		String sql = "insert into record values(null,?,?,?,?)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, record.spend);
			ps.setInt(2, record.cid);
			ps.setString(3, record.comment);
			ps.setDate(4, DateUtil.util2sql(record.date));
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();// 有时候，在用insert插入数据后，想获得刚插入记录的ID，可以利用JDBC的getGeneratedKeys获得INSERT插入后生成的主键ID。
			if (rs.next()) {
				int id = rs.getInt(1);
				record.id = id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void upate(Record record) {
		String sql = "update record set spend= ?,cid= ?, comment =?, date = ? where id =?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, record.spend);
			ps.setInt(2, record.cid);
			ps.setString(3, record.comment);
			ps.setDate(4, DateUtil.util2sql(record.date));
			ps.setInt(5, record.id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
			String sql = "delete from record where id = " + id;
			s.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Record get(int id) {
		Record record = null;// 初始化？
		try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {
			String sql = "select * from record where  id =" + id;
			ResultSet rs = s.executeQuery(sql);// executeQuery可以直接得到结果集execute不可以
			if (rs.next()) {
				record = new Record();
				int spend = rs.getInt("spend");// 把取到的数据库的值保存在spend中
				int cid = rs.getInt("cid");
				String comment = rs.getString("comment");
				Date date = rs.getDate("date");

				record.spend = spend;
				record.cid = cid;
				record.comment = comment;
				record.date = date;// 为什么这里不需要像update中一样转换util2sql
				record.id = id;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return record;
	}

	public List<Record> list() {
		return list(0, Short.MAX_VALUE);
	}

	// 获取某个时间范围内的消费记录信息
	public List<Record> list(int start, int count) {
		List<Record> records = new ArrayList<Record>();
		String sql = "select * from record order by id desc limit ?,? ";
		// order by id desc 按id大小降序排列 limit 开始位置 行数
		// limit 开始于start 从start开始取count条数据

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {

			ps.setInt(1, start);
			ps.setInt(2, count);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Record record = new Record();
				int id = rs.getInt("id");
				int spend = rs.getInt("spend");
				int cid = rs.getInt("cid");

				String comment = rs.getString("comment");
				Date date = rs.getDate("date");

				record.spend = spend;
				record.cid = cid;
				record.comment = comment;
				record.date = date;
				record.id = id;
				records.add(record);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return records;
	}

	public List<Record> list(int cid) {
		List<Record> records = new ArrayList<Record>();
		String sql = "select * from record where cid = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setInt(1, cid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Record record = new Record();
				int id = rs.getInt("id");
				int spend = rs.getInt("spend");
				String comment = rs.getString("comment");
				Date date = rs.getDate("date");

				record.spend = spend;
				record.cid = cid;
				record.comment = comment;
				record.date = date;
				record.id = id;
				records.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}

	// 获取今天的消费记录信息
	public List<Record> listToday() {
		return list(DateUtil.today());
	}

	// 获取某天的消费记录信息
	public List<Record> list(Date day) {
		List<Record> records = new ArrayList<Record>();
		String sql = "select * from record where date =?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setDate(1, DateUtil.util2sql(day));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Record record = new Record();
				int id = rs.getInt("id");
				int cid = rs.getInt("cid");
				int spend = rs.getInt("spend");
				String comment = rs.getString("comment");
				Date date = rs.getDate("date");

				record.spend = spend;
				record.cid = cid;
				record.comment = comment;
				record.date = date;
				record.id = id;
				records.add(record);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}

	// 获取本月的消费记录信息
	public List<Record> listThisMonth() {
		return list(DateUtil.monthBegin(), DateUtil.monthEnd());
	}

	// 获取某个时间范围内的消费记录信息
	public List<Record> list(Date start, Date end) {
		List<Record> records = new ArrayList<Record>();
		String sql = "select * from record where date >=? and date <=?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
			ps.setDate(1, DateUtil.util2sql(start));
			ps.setDate(2, DateUtil.util2sql(end));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Record record = new Record();
				int id = rs.getInt("id");
				int cid = rs.getInt("cid");
				int spend = rs.getInt("spend");
				String comment = rs.getString("comment");
				Date date = rs.getDate("date");

				record.spend = spend;
				record.cid = cid;
				record.comment = comment;
				record.date = date;
				record.id = id;
				records.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}
}
