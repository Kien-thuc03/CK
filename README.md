// tao mot ban con books authors voi 2 thuoc tinh la ISBN va author
    @ElementCollection
    @JoinTable(name = "books_authors", joinColumns = @JoinColumn(name = "ISBN"))
    @Column(name = "author", nullable = false)
    private Set<String> authors;

//dat o lop cha de tao moi quan he 1 1 voi lop con trong csdl
    @Inheritance(strategy = InheritanceType.JOINED)

import entity.Food;
import entity.Item;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @description
 * @author: nktng,
 * @date:23/05/2024,
 */
public class Main {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080); Scanner scanner = new Scanner(System.in)) {
            System.out.println("Connected to server");
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            while (true) {
                System.out.println("1. Thêm một món ăn mới\n" +
                        "2. Liệt kê danh sách mặt hàng là món đặt biệt của nhà hàng mà có sử dụng nguyên\n" +
                        "liệu được nhập từ nhà cung cấp\n" +
                        "3. Tính giá gốc của từng món ăn sau khi chế biết thành phẩm\n" +
                        "4. Thoát");
                System.out.print("Chọn: ");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    dos.writeInt(1);
                    System.out.print("Nhập id: ");
                    String id = scanner.next();
                    dos.writeUTF(id);
                    dos.flush();
                    System.out.print("Nhập tên: ");
                    String name = scanner.next();
                    dos.writeUTF(name);
                    dos.flush();
                    boolean result = dis.readBoolean();
                    if (result) {
                        System.out.println("Thêm thành công");
                    } else {
                        System.out.println("Thêm thất bại");
                    }
                } else if (choice == 2) {
                    dos.writeInt(2);
                    System.out.print("Nhập tên nhà cung cấp: ");
                    String supplierName = scanner.next();
                    dos.writeUTF(supplierName);
                    dos.flush();
                    List<Item> list = (List<Item>) ois.readObject();
                    list.forEach(System.out::println);
                } else if (choice == 3) {
                    dos.writeInt(3);
                    Map<Food, Double> map = (Map<Food, Double>) ois.readObject();
                    map.forEach((food, cost) -> System.out.println(food + " - " + cost));
                } else if (choice == 4) {
                    dos.writeInt(4);
                    System.exit(0);
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
