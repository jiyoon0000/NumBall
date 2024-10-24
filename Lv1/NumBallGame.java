package Lv1;//import java.util.List;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Random;
//import java.util.Scanner;
import java.util.*;

import static sun.security.util.KeyUtil.validate;

public class NumBallGame {
    private List<Integer> answer; //정답 숫자가 담길 정수형 리스트

    //객체 생성 시 정답을 생성하도록 만듬
    public NumBallGame(){
        createAnswer();
    }

    public void createAnswer() {
        answer = new ArrayList<>();
        //가장 빠르고 순서를 예측할 수 없는 hashset을 사용해 numbers 집합을 만듬
        Set<Integer> numbers = new HashSet<>();
        Random random = new Random();

        //set을 사용하여 중복된 값을 걸러낸 뒤에 난수 생성, 3개를 뽑기 위해 while 문으로 조건을 줌
        while (numbers.size() < 3){
            //random.nextInt(9) : 0,1,2,3,4,5,6,7,8
            //컴퓨터는 0부터 세기 때문에 9개를 하면 0부터 8까지의 수가 나오는데 여기에 1을 더해 0은 포함하지 않고 1~9까지의 수를 뽑음
            int num = random.nextInt(9) + 1;
            numbers.add(num); //numbers 셋에 num이라는 숫자를 추가
        }
        //ArrayList에 다른 리스트의 값을 전부 붙이기 위한 메서드
        //numbers의 데이터를 answer에 붙인다.
        //.add는 순서를 가지고 데이터가 붙기 때문에 랜덤으로 섞어야하는 조건에 일치하지 않음
        answer.add(numbers);
        Collections.Shuffle(answer);
    }
    //answer의 값을 가져오기 위해 getter 사용하여 외부에서 접근할 수 있도록 함
    public List<Integer> getAnswer(){
        return answer;
    }

    public String guessAnswer() {
        Scanner sc = new Scanner(System.in);
        String input;
        //게임 시작
        System.out.println("[ 숫자 야구 게임을 시작합니다 ]");

        while (true) {
            System.out.print("숫자를 입력하세요. (조건 : 중복이 없는 세자리 수)");
            input = sc.next();

            //조건을 만족하면 조건문을 빠져나오고 조건을 만족하지 않으면 알림문구를 띄우고 다시 입력하도록 설정
            //validate() -> 유효성 검사
            if(validateInput(input)){
                break; //입력값이 유효하면 조건문을 빠져나온다.
            }else{ //입력값이 유효하지 않으면 알림문구가 나오고, 입력을 제대로 해야지 조건문을 빠져나올수있다.
                System.out.print("올바르지 않은 입력값입니다.");
            }
            return input;
        }
    }

}

