package Lv2;

public class Main {
    public static void main(String[] args) {
        NumBallGameDisplay display = new NumBallGameDisplay();
        display.displayHint();
        Lv2.NumBallGame numball  = new NumBallGame();
        System.out.println("[ 숫자 야구 게임을 시작합니다 ]");
        numball.play();
    }
}
