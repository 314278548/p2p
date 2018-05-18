import eon.p2p.base.domain.Logininfo;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    //    public static void main(String[] args) throws Exception {
//        ServerSocket server = new ServerSocket(5000);
//        Socket accept = server.accept();
//        try (InputStream inputStream = accept.getInputStream();   Scanner scanner = new Scanner(inputStream);) {
//            while (scanner.hasNext()) {
//                String next = scanner.next();
//                System.out.println(next);
//            }
//        }
//    }
    int size;
    MyNode first;
    MyNode last;

    private static class MyNode<T> {
        MyNode next;
        T ele;
    }

    public <T> void addLast(T e) {
        MyNode myNode = new MyNode();
        myNode.ele = e;
        if (size == 0){
            first = myNode;
            last = myNode;
        }
        else{
            //新增节点的上一个节点为原来最后一个节点
            //最后一个节点的下一个接点为新家节点
            last.next = myNode;
            //新家节点为最后一个节点
            last = myNode;
        }
        size ++;
    }

    public static void main(String[] args){
        Client c = new Client();
        c.addLast(1);
        c.addLast(2);
        System.out.println(c.first.next.ele);
    }

}
