import java.util.UUID;

/**
 * 
 */

/**
 * @author zhangzhi
 *2019年9月12日
 */
public class TestMycms {
	public static void main(String[] args) {
		for(int i=0;i<5;i++) {
		String str = UUID.randomUUID().toString();
		System.out.println(str);
		}
	}
}
