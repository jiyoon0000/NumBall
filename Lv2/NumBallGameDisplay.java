package Lv2;

import java.util.Scanner;

public class NumBallGameDisplay {
    public void displayHint(){
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("1. 게임 시작하기");
            System.out.println("3. 종료하기");
            System.out.println("선택한 번호 : ");

            String select = sc.next();

            if (select.equals("1")){
                NumBallGame numball = new NumBallGame();
                numball.play();
            } else if(select.equals("3")){
                System.out.println("게임을 종료합니다.");
                break;
            }else {
                System.out.println("다시 입력해 주세요.");
            }
        }
    }
}
