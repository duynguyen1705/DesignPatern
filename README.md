Đề bài :

Design pattern là một kỹ thuật trong lập trình hướng đối tượng, nó rất quan trọng khi giải quyết vấn đề của nhiều bài toán khác nhau. Design pattern là sự đúc kết kinh nghiệm để linh hoạt trong quá trình sử dụng về sau, yêu cầu mỗi lập trình viên muốn giỏi đều phải biết.

Về cơ bản, có 3 nhóm design pattern:

    Design Pattern

    Structural Design Pattern
    Behavioral Design Pattern

Singleton

Singleton pattern (thuộc Creational) đảm bảo chỉ duy nhất môt new instance được tạo ra cho 1 lớp và nó sẽ cung cấp cho bạn một method để truy cập đến đối tượng duy nhất đó. Dù cho việc thực hiện cài đặt Singleton bằng cách nào đi nữa cũng đều dựa vào nguyên tắc dưới đây.

    private constructor để hạn chế khởi tạo đối tượng từ bên ngoài

    đặt private static variable cho đối tượng được khởi tạo, đảm bảo biến chỉ được khởi tạo trong chính lớp này.
    có một method public để return instance đã được khởi tạo ở trên.

Code cài đặt cho Singleton đơn giản như dưới đây:

public class Singleton {

    private static Singleton singleton = new Singleton( );

    // Private Constructor để không cho phép 
    // bất cứ lớp nào khác khởi tạo
    private Singleton(){ }

    // Phương thức static 'instance'
    public static Singleton getInstance( ) {
        return singleton;
    }
}

Composite

Composite pattern (thuộc Structural) cho phép tương tác với tất cả các đối tượng tương tự nhau giống như là các đối tượng đơn hoặc collections. Ví dụ: Đối tượng File sẽ là 1 đối tượng đơn nếu bên trong nó không có file nào khác, nhưng đối tượng file (folder) sẽ được đối xử giống như 1 collections nếu bên trong nó lại có những File khác.

Trong ví dụ thể hiện ở biểu đồ lớp dưới đây, lớp Employee có 1 thuộc tính để lưu các cấp dưới của mình subordinates là 1 List<> các đối tượng Employee.

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private String dept;
    private int salary;
    private List<Employee> subordinates;

    // constructor
    public Employee(String name,String dept, int sal) {
        this.name = name;
        this.dept = dept;
        this.salary = sal;
        subordinates = new ArrayList<Employee>();
    }

    public void add(Employee e) {
        subordinates.add(e);
    }

    public void remove(Employee e) {
        subordinates.remove(e);
    }

    public List<Employee> getSubordinates(){
        return subordinates;
    }

    public String toString(){
        return ("Employee :[ Name : " + name + ", dept : " + dept + ", salary :" + salary+" ]");
    }
}

public class CompositePatternDemo {
    public static void main(String[] args) {

        Employee CEO = new Employee("John","CEO", 30000);

        Employee headSales = new Employee("Robert","Head Sales", 20000);

        Employee headMarketing = new Employee("Michel","Head Marketing", 20000);

        Employee clerk1 = new Employee("Laura","Marketing", 10000);
        Employee clerk2 = new Employee("Bob","Marketing", 10000);

        Employee salesExecutive1 = new Employee("Richard","Sales", 10000);
        Employee salesExecutive2 = new Employee("Rob","Sales", 10000);

        CEO.add(headSales);
        CEO.add(headMarketing);

        headSales.add(salesExecutive1);
        headSales.add(salesExecutive2);

        headMarketing.add(clerk1);
        headMarketing.add(clerk2);

        //print all employees of the organization
        System.out.println(CEO);

        for (Employee headEmployee : CEO.getSubordinates()) {
            System.out.println(headEmployee);

            for (Employee employee : headEmployee.getSubordinates()) {
                System.out.println(employee);
            }
        }
    }
}

Kết quả in ra như sau:

Employee :[ Name : John, dept : CEO, salary :30000 ]
Employee :[ Name : Robert, dept : Head Sales, salary :20000 ]
Employee :[ Name : Richard, dept : Sales, salary :10000 ]
Employee :[ Name : Rob, dept : Sales, salary :10000 ]
Employee :[ Name : Michel, dept : Head Marketing, salary :20000 ]
Employee :[ Name : Laura, dept : Marketing, salary :10000 ]
Employee :[ Name : Bob, dept : Marketing, salary :10000 ]

Strategy

Khi áp dụng strategy pattern (thuộc Behavioral) thì các hành vi hoặc giải thuật của một class có thể thay đổi ở runtime. Strategy pattern được sử dụng khi có nhiều lớp chỉ khác nhau về hành vi, khi cần các biến thể khác nhau của thuật toán. Strategy pattern định nghĩa mỗi hành vi trong lớp riêng, loại bỏ sự cần thiết cho các câu lệnh có điều kiện. Nó giúp dễ dàng mở rộng và kết hợp hành vi mới mà không thay đổi ứng dụng.

public interface Strategy {
    int doOperation(int num1, int num2);
}

public class OperationAdd implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}

public class OperationSubstract implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}

public class StrategyPatternDemo {
    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5)); // 15

        context = new Context(new OperationSubstract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5)); // 5
    }
}

Yêu cầu:

Câu 1. Hãy tìm mẫu thiết kế thích hợp với cấu trúc dữ liệu cho bài toán phả hệ như sau:

    Từng cá nhân cần lưu các thông tin gồm: ngày tháng năm sinh, giới tính, v.v.

    Một cá nhân có thể có một nhiều con hoặc không có con
    Một cá nhân có thể kết hôn hoặc không

Ví dụ: James kết hôn với Hana sinh ra hai người con là Ryan và Kai. Ryan không lấy vợ. Kai lấy Jennifer sinh ra bốn người con gồm hai nam, hai nữ. Bốn người con này tiếp tục kết hôn và sinh con đẻ cái.

Dựa trên mẫu thiết kế vừa tạo, hãy:

Tìm tất cả các cá nhân không kết hôn trong danh sách phả hệ

    Tìm tất cả các cặp vợ chồng có hai con trong danh sách phả hệ

    Tìm tất cả các thế hệ mới nhất trong danh sách phả hệ
    Viết phương thức main kiểm tra

Gợi ý: Sử dụng mẫu thiết kế Composite vì mẫu này thích hợp để lưu danh sách dạng cây

Câu 2. Cho một mảng các số nguyên có thể được sắp xếp tăng dần, hoặc giảm dần sử dụng thuật toán sắp xếp nổi bọt, hoặc sắp xếp chọn. Sau này, James mong muốn áp dụng thêm các thuật toán sắp xếp khác (hiện tại James chưa muốn cài đặt). Hãy giúp James xây dựng mẫu thiết kế thích hợp nhất. Viết phương thức main để chạy thử chương trình.

Gợi ý: Sử dụng mẫu thiết kế StrategyĐề bài :

Design pattern là một kỹ thuật trong lập trình hướng đối tượng, nó rất quan trọng khi giải quyết vấn đề của nhiều bài toán khác nhau. Design pattern là sự đúc kết kinh nghiệm để linh hoạt trong quá trình sử dụng về sau, yêu cầu mỗi lập trình viên muốn giỏi đều phải biết.

Về cơ bản, có 3 nhóm design pattern:

    Design Pattern

    Structural Design Pattern
    Behavioral Design Pattern

Singleton

Singleton pattern (thuộc Creational) đảm bảo chỉ duy nhất môt new instance được tạo ra cho 1 lớp và nó sẽ cung cấp cho bạn một method để truy cập đến đối tượng duy nhất đó. Dù cho việc thực hiện cài đặt Singleton bằng cách nào đi nữa cũng đều dựa vào nguyên tắc dưới đây.

    private constructor để hạn chế khởi tạo đối tượng từ bên ngoài

    đặt private static variable cho đối tượng được khởi tạo, đảm bảo biến chỉ được khởi tạo trong chính lớp này.
    có một method public để return instance đã được khởi tạo ở trên.

Code cài đặt cho Singleton đơn giản như dưới đây:

public class Singleton {

    private static Singleton singleton = new Singleton( );

    // Private Constructor để không cho phép 
    // bất cứ lớp nào khác khởi tạo
    private Singleton(){ }

    // Phương thức static 'instance'
    public static Singleton getInstance( ) {
        return singleton;
    }
}

Composite

Composite pattern (thuộc Structural) cho phép tương tác với tất cả các đối tượng tương tự nhau giống như là các đối tượng đơn hoặc collections. Ví dụ: Đối tượng File sẽ là 1 đối tượng đơn nếu bên trong nó không có file nào khác, nhưng đối tượng file (folder) sẽ được đối xử giống như 1 collections nếu bên trong nó lại có những File khác.

Trong ví dụ thể hiện ở biểu đồ lớp dưới đây, lớp Employee có 1 thuộc tính để lưu các cấp dưới của mình subordinates là 1 List<> các đối tượng Employee.

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private String dept;
    private int salary;
    private List<Employee> subordinates;

    // constructor
    public Employee(String name,String dept, int sal) {
        this.name = name;
        this.dept = dept;
        this.salary = sal;
        subordinates = new ArrayList<Employee>();
    }

    public void add(Employee e) {
        subordinates.add(e);
    }

    public void remove(Employee e) {
        subordinates.remove(e);
    }

    public List<Employee> getSubordinates(){
        return subordinates;
    }

    public String toString(){
        return ("Employee :[ Name : " + name + ", dept : " + dept + ", salary :" + salary+" ]");
    }
}

public class CompositePatternDemo {
    public static void main(String[] args) {

        Employee CEO = new Employee("John","CEO", 30000);

        Employee headSales = new Employee("Robert","Head Sales", 20000);

        Employee headMarketing = new Employee("Michel","Head Marketing", 20000);

        Employee clerk1 = new Employee("Laura","Marketing", 10000);
        Employee clerk2 = new Employee("Bob","Marketing", 10000);

        Employee salesExecutive1 = new Employee("Richard","Sales", 10000);
        Employee salesExecutive2 = new Employee("Rob","Sales", 10000);

        CEO.add(headSales);
        CEO.add(headMarketing);

        headSales.add(salesExecutive1);
        headSales.add(salesExecutive2);

        headMarketing.add(clerk1);
        headMarketing.add(clerk2);

        //print all employees of the organization
        System.out.println(CEO);

        for (Employee headEmployee : CEO.getSubordinates()) {
            System.out.println(headEmployee);

            for (Employee employee : headEmployee.getSubordinates()) {
                System.out.println(employee);
            }
        }
    }
}

Kết quả in ra như sau:

Employee :[ Name : John, dept : CEO, salary :30000 ]
Employee :[ Name : Robert, dept : Head Sales, salary :20000 ]
Employee :[ Name : Richard, dept : Sales, salary :10000 ]
Employee :[ Name : Rob, dept : Sales, salary :10000 ]
Employee :[ Name : Michel, dept : Head Marketing, salary :20000 ]
Employee :[ Name : Laura, dept : Marketing, salary :10000 ]
Employee :[ Name : Bob, dept : Marketing, salary :10000 ]

Strategy

Khi áp dụng strategy pattern (thuộc Behavioral) thì các hành vi hoặc giải thuật của một class có thể thay đổi ở runtime. Strategy pattern được sử dụng khi có nhiều lớp chỉ khác nhau về hành vi, khi cần các biến thể khác nhau của thuật toán. Strategy pattern định nghĩa mỗi hành vi trong lớp riêng, loại bỏ sự cần thiết cho các câu lệnh có điều kiện. Nó giúp dễ dàng mở rộng và kết hợp hành vi mới mà không thay đổi ứng dụng.

public interface Strategy {
    int doOperation(int num1, int num2);
}

public class OperationAdd implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}

public class OperationSubstract implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}

public class StrategyPatternDemo {
    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5)); // 15

        context = new Context(new OperationSubstract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5)); // 5
    }
}

Yêu cầu:

Câu 1. Hãy tìm mẫu thiết kế thích hợp với cấu trúc dữ liệu cho bài toán phả hệ như sau:

    Từng cá nhân cần lưu các thông tin gồm: ngày tháng năm sinh, giới tính, v.v.

    Một cá nhân có thể có một nhiều con hoặc không có con
    Một cá nhân có thể kết hôn hoặc không

Ví dụ: James kết hôn với Hana sinh ra hai người con là Ryan và Kai. Ryan không lấy vợ. Kai lấy Jennifer sinh ra bốn người con gồm hai nam, hai nữ. Bốn người con này tiếp tục kết hôn và sinh con đẻ cái.

Dựa trên mẫu thiết kế vừa tạo, hãy:

Tìm tất cả các cá nhân không kết hôn trong danh sách phả hệ

    Tìm tất cả các cặp vợ chồng có hai con trong danh sách phả hệ

    Tìm tất cả các thế hệ mới nhất trong danh sách phả hệ
    Viết phương thức main kiểm tra

Gợi ý: Sử dụng mẫu thiết kế Composite vì mẫu này thích hợp để lưu danh sách dạng cây

Câu 2. Cho một mảng các số nguyên có thể được sắp xếp tăng dần, hoặc giảm dần sử dụng thuật toán sắp xếp nổi bọt, hoặc sắp xếp chọn. Sau này, James mong muốn áp dụng thêm các thuật toán sắp xếp khác (hiện tại James chưa muốn cài đặt). Hãy giúp James xây dựng mẫu thiết kế thích hợp nhất. Viết phương thức main để chạy thử chương trình.

Gợi ý: Sử dụng mẫu thiết kế Strategy
