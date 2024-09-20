import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[][] map = new int[101][101];
       // RobotM2 rob3 = new RobotM2(map);
       // System.out.println(rob3);
        String proga = readProga();
        RobotM2 rob4 = new RobotM2(map, 50, 50, "up");
        int result = rob4.runAndCount(proga);
        System.out.println(result);
    }

    private static String readProga() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите программу робота, содержащую буквы S, L, R без пробелов ");
        String s = scanner.next();
        return s;
    }
}

class RobotM2 extends Robot{
    int stepCounter=0;
    int[][] map;
    public RobotM2(int[][] map){
        this.map = map;
        leaveTrace();
    }

    public RobotM2(int[][] map, int x, int y, String direction){
        super(x, y, direction); // если он вообще есть, то вызов конструктора БАЗОВОГО класса идет первым
        this.map = map;
        leaveTrace();
    }

    public void leaveTrace(){
        if(getY() >=0 && getY() < map.length && getX()>=0 && getX() < map[0].length)
            map[getX()][getY()]=1;
        else
            System.out.println("Выход за границы карты. Здесь живут драконы");
    }

    public int runAndCount(String prg){
        for (char c: prg.toCharArray()){
            switch (c){
                case 'S': stepCounter++;
                          stepForward();
                          if(map[getX()][getY()] == 0)  //небезопасно!!!
                              leaveTrace();
                          else
                              return stepCounter;
                          break;
                case 'L': turnLeft(); break;
                case 'R': turnRight(); break;
            }
        }
        return -1;
    }
}


class Robot {
    private int x = 0, y = 0;   //private - запрет на "телепортацию"
    private String direction = "right"; //"left", "up", "down"

    public Robot() {
    }        //конструктор без параметров
    //он итак есть неявно, если нет других конструкторов


    public Robot(int x, int y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void stepForward() {
        System.out.println("шаг вперед");
        switch (direction) {
            case "up":   y++; break;
            case "down": y--; break;
            case "right":x++; break;
            case "left": x--; break;
        }
    }

    public void turnLeft() {
        System.out.println("поворот налево");
        switch (direction) {
            case "right":
                direction = "up";
                break;
            case "left":
                direction = "down";
                break;
            case "up":
                direction = "left";
                break;
            case "down":
                direction = "right";
                break;
        }
    }

    public void turnRight() {
        System.out.println("поворот направо");
        switch (direction) {
            case "right":
                direction = "down";
                break;
            case "left":
                direction = "up";
                break;
            case "up":
                direction = "right";
                break;
            case "down":
                direction = "left";
                break;
        }
    }

    public void runProga(String proga){
        for (char c: proga.toCharArray()    ) {
            switch (c){
                case 'S': stepForward(); break;
                case 'L': turnLeft(); break;
                case 'R': turnRight(); break;
                default:
                    System.out.println("неизвестная команда");
            }
        }
    }

    public String getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString(){
        return "Robot {"+x+" "+y+" "+direction+"}";
    }
}