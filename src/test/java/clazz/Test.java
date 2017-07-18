package clazz;

import com.ixenos.lvy.bean.Comment;

public class Test {
	public static void main(String[] args) {
		Comment[] comments = new Comment[3];
		if(comments[0] == null){
			System.out.println("woca");
		}
		comments[0] = new Comment();
		if(comments[0] != null){
			System.out.println("asdas");
		}
	}
}
