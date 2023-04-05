import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// 직렬화와 역직렬화 예제 실습.
public class SerializableEx {

	public static void main(String[] args) throws Exception {
		// Person 객체에 데이터를 넣어준다.
		Person p = new Person("milcho", "milcho0604@gmail.com", 202014036, 12345678);

		// serialization(직렬화)
		File f = new File("./person.txt");
		try (FileOutputStream fos = new FileOutputStream(f); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(p);
			// flush는 필수로 적어줘야 버퍼에 있는 모든 데이터를 모아서 처리하기 때문에 항상 적어주는 것이 버퍼 효율에 좋다.
			oos.flush();
		}

		// deserialization(역직렬화)
		Person result = null;
		try (FileInputStream fis = new FileInputStream(f); ObjectInputStream ois = new ObjectInputStream(fis)) {
			result = (Person) ois.readObject();
		}

		System.out.println(result.toString());
	}

	// Person객체를 만들어 객체 안에 데이터를 넣고 이 객체의 데이터를 직렬화하여 저장하고 bytes로 역직렬화로 다시 객체로 만든다.
	public static class Person implements Serializable {

		private static final long serialVersionUID = 1L;
		private String name;
		private String email;
		private int id;
		private int phoneNumber;

		Person(String name, String email, int id, int phoneNumber) {
			this.name = name;
			this.email = email;
			this.id = id;
			this.phoneNumber = phoneNumber;
		}

		@Override
		public String toString() {
		    return "Person [\n" +
		            "    name= " + name + ",\n" +
		            "    email= " + email + ",\n" +
		            "    id= " + id + ",\n" +
		            "    phoneNumber= " + phoneNumber + "\n" +
		            "]";
		}
	}
}
