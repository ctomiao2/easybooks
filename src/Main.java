import com.EasyBook.Client.*;
import com.EasyBook.Common.Response;
import com.EasyBook.Common.UnitTestBase;

public class Main {

	private static void UnitTest() {
		Response.test();
		UnitTestBase.runAllTestCases();
	}

	public static void main(String[] args) {
		new MainFrame();
		//UnitTest();
	}
}