package service;

import java.util.Collections;
import java.util.List;

import dao.CategoryDAO;
import dao.RecordDAO;
import entity.Category;
import entity.Record;

public class CategoryService {
	
	CategoryDAO categoryDao = new CategoryDAO();
	RecordDAO recordDao = new RecordDAO();
	
	public List<Category> list(){
		List<Category> cs = categoryDao.list();
		for(Category c : cs) {
			List<Record> rs = recordDao.list(c.id);
			c.recordNumber = rs.size(); //rs记录每一个id size反应出id的个数
		}
		Collections.sort(cs, (c1,c2)->c2.recordNumber-c1.recordNumber);//lambda表达式
		return cs;
	}//查询出所有的Category，然后根据每种分类，再把分类对应的消费记录总数查出来，并且设置在对应分类的recordNumber上
	//最后再根据recordNumber进行倒叙排列，让消费频率高的分类放在前面
	public void add(String name) {
		Category c = new Category();
		c.setName(name);
		categoryDao.add(c);
	}//增加一种分类
	public void update(int id, String name) {
		Category c = new Category();
		c.setName(name);
		c.setId(id);
		categoryDao.update(c);
	}//更新分类
	public void delete(int id) {
		categoryDao.delete(id);
	}//删除分类
}
