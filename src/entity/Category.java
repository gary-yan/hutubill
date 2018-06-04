package entity;

public class Category {
	public int id;
	public String name;
	
	public int recordNumber; //数据库中没有 由计算得出
	
	public int getRecordNumber() {
		return recordNumber;
	}
	public void setRecordNumber(int recordNumer) {
		this.recordNumber = recordNumber;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id= id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return name;
	}//这里提供了一个toString方法，在后续JComboBox中显示的时候，会调用次方法，用于显示分类名称
}
