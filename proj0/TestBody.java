public class TestBody{
	public static void main(String[] args) {
		Body a1 = new Body(3,4,1,2,3,"null");
		Body a2 = new Body(4,5,2,3,3,"null");

		System.out.println(a1.calcForceExertedBy(a2));	
	}
}