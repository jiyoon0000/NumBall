package Lv2;

import java.util.Scanner;

public class NumBallGameDisplay {
    public void displayHint() {

        //scanner로 값 입력할 수 있게 하기
        Scanner sc = new Scanner(System.in);

        while (true) {
            //try-catch 예외처리
            //입력값이 1,3이 아닌 경우 조건에 맞게 입력하라는 메세지를 띄움
            try {
                System.out.println("1. 게임 시작하기");
                System.out.println("3. 종료하기");
                System.out.println("선택한 번호 : ");

                String select = sc.next();

                //입력이 1인 경우 게임시작, 3인 경우 게임 종료
                if (select.equals("1")) {
                    NumBallGame numball = new NumBallGame();
                    numball.play();
                } else if (select.equals("3")) {
                    System.out.println("게임을 종료합니다.");
                    break;
                } else {
                    throw new IllegalAccessException("조건에 맞게 입력해주세요.");
                }
            }catch (IllegalAccessException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
