package server;

import dao.impl.FoodImpl;
import dao.impl.ItemImpl;
import entity.Food;
import entity.Item;
import lombok.SneakyThrows;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;

/**
 * @description
 * @author: nktng,
 * @date:23/05/2024,
 */
//Hiện thực chương trình dựa trên mô hình client server (dùng kỹ thuật socket)
public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started on port 8080");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());
                new Thread(new ServerThread(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ServerThread implements Runnable {
        private Socket socket = null;
        private ItemImpl itemImpl = null;
        private FoodImpl foodImpl = null;

        public ServerThread(Socket socket) {
            this.socket = socket;
            itemImpl = new ItemImpl();
            foodImpl = new FoodImpl();
        }

        @SneakyThrows
        @Override
        public void run() {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            while (true) {
                int msg = dis.readInt();

                switch (msg) {
                    case 1: {
                        String id = dis.readUTF();
                        String name = dis.readUTF();
                        Food food = new Food(id, name, 0, "", false, null, 0, 0);
                        boolean result = itemImpl.addFood(food);
                        dos.writeBoolean(result);
                        dos.flush();
                        break;
                    }
                    case 2: {
                        String supplierName = dis.readUTF();
                        List<Item> items = itemImpl.listSpecialItemsBySupplier(supplierName);
                        oos.writeObject(items);
                        break;
                    }
                    case 3: {
                        Map<Food, Double> foodAndCost = foodImpl.listFoodAndCost();
                        oos.writeObject(foodAndCost);
                        break;
                    }
                    case 4: {
                        itemImpl.close();
                        foodImpl.close();
                        dis.close();
                        dos.close();
                        ois.close();
                        oos.close();
                        socket.close();
                        return;
                    }
                    default:
                        break;
                }
            }
        }
    }
}
