import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.collect.Lists;

/**
 * 被声明为abstract的类称为抽象类,不一定需要抽象方法，
 * 但是有抽象方法的类必须申明为抽象类，抽象类可以继承抽象类，可以实现接口
 * 接口只能实现implements接口，但是不能继承extends接口
 * 抽象类和接口的抽象方法必须由子类来实现 本身只起到定义的作用
 * @author zhangcb
 *
 */
public abstract class MatchTask extends FootballTask implements SpiderBascketball{

	@Override
	public void httpGet(String url) {
		List list1 = Lists.newArrayList();
		list1.add("aaa");
		list1.add("bbb");
		list1.add("ccc");
		
		List list2 = Lists.newArrayList();
		list2.add("111");
		list2.add("bbb");
		list2.add("cc2");
		
		System.out.println(list1.retainAll(list2));
	}

	@Override
	public void httpPost(String url, Map<String, String> param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spiderBasketBall(String url) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
//		List list1 = new ArrayList(10);
//		list1.add("aaa");
//		list1.add("bbb");
//		list1.add("ccc");
//		System.out.println(list1.hashCode());
//		List list2 = Lists.newArrayList();
//		list2.add("111");
//		list2.add("33");
//		list2.add("cc2");
//		list1.retainAll(list2);
//		System.out.println(list1);
//		System.out.println(list2);
//		
//		LinkedList list3 = new LinkedList<>();
//		list3.add("qwe");
//		list3.add("33r");
//		list3.add("rtd");
//		System.out.println(list3.getFirst());
//		System.out.println(list3.getLast());
//		System.out.println(list3.pop());
//		list3.addFirst(2342);
//		list3.add(212);
//		list3.push(1111);
//		System.out.println(list3);
		Map<String,String> map = new ConcurrentHashMap();
        map.put("a","123");
        map.put("b","456");
	}
}
