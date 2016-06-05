package xyz.moexiang;


/**
 * Created by timeloveboy on 16/6/5.
 * 加上 implements  Comparable后，就可以实现list<Myclass> 类型的排序</>
 * 排序的实现，看http://www.blogjava.net/fastunit/archive/2008/04/08/191533.html
 */
public class Myclass implements  Comparable{

    public String getCute() {
        return cute;
    }

    @Override
    public int compareTo(Object o) {
        return id;
    }

    public void setCute(String cute) {
        this.cute = cute;
    }

    public Myclass(String cute) {
        this.cute = cute;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    String cute;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;
    Object data;
}
