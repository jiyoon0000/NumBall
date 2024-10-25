package Lv2;//import java.util.List;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Random;
//import java.util.Scanner;
import java.util.*;

public class NumBallGame {
    private List<Integer> answer; //정답 숫자가 담길 정수형 리스트

    //객체 생성 시 정답을 생성하도록 만듬
    public NumBallGame(){
        createAnswer();
    }

    private void createAnswer() {
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
        answer.addAll(numbers);
        Collections.shuffle(answer);
    }
    //answer의 값을 가져오기 위해 getter 사용하여 외부에서 접근할 수 있도록 함
    public List<Integer> getAnswer(){
        return answer;
    }

    public String guessAnswer() {
        Scanner sc = new Scanner(System.in);
        String input = "";
        //게임 시작
        while (true) {
            System.out.print("숫자를 입력하세요. (조건 : 중복이 없는 세자리 수)");
            input = sc.next();

            //try-catch 예외처리
            try {
                //입력값이 숫자인가?
                Integer.parseInt(input);

                //입력값이 세자리 숫자가 아니면 세자리 숫자를 입력하라는 메세지를 띄움
                if(input.length() != 3){
                    throw new IllegalAccessException("세 자리 숫자를 입력해주세요.");
                }
                //중복숫자나 0이 포함되었는지 확인
                //포함되어 있다면 올바르지 않은 입력값이라는 메세지를 띄움
                if(!validateInput(input)){
                    throw new IllegalAccessException("올바르지 않은 입력값입니다.");
                }
                //모든 조건을 만족할 시에 반복문을 빠져나감
                break;
            }catch (NumberFormatException e){
                System.out.println("잘못 입력하였습니다.");
            }catch (IllegalAccessException e){
                //유효성 검사 오류 메세지 출력
                System.out.println(e.getMessage());
            }

        }
        return input;
    }

    //입력값이 3자리 숫자가 맞는지 확인
    //만약 3자리 숫자가 아니거나 문자일 경우 false 값이 나오게 하기
    protected boolean validateInput(String input){
        //입력값이 3자리인지 확인
        if(input.length() != 3){
            return false;
        }
        //입력값이 문자인지, 각 문자는 숫자가 맞는지 확인
        for (int i=0; i < input.length(); i++){
            char c = input.charAt(i); //입력값이 문자일 경우, 현재 문자를 가져오기
            if (c < '1' || c > '9') {
                return false; // 이 문자가 1보다 작거나 9보다 클 경우 = 1~9가 아닐 경우 false
            }
        }
        //중복된 숫자가 있는지 확인
        for (int i=0; i <input.length(); i++) {
            //i와 j를 for문 반복을 통해 하나씩 일치하는지 확인
            //일치해서 중복이 있을 경우 false 반환
            for (int j = i+1; j < input.length(); j++) {
                if (input.charAt(i) == input.charAt(j)){
                    return false;
                }
            }
        }
        //위의 모든 조건이 맞고 숫자 3자리가 맞을 시 true 반환
        return true;
    }

    //게임 시작
    public void play(){
        //input에 추측한 정답을 넣고, 스트라이크와 볼 개수를 확인하여 스트라이크가 3개일시 정답
        //축하 문구를 띄우고 게임을 종료
        while(true){
            String input = guessAnswer();
            int strike = countStrike(input);
            int ball = countBall(input);

            if(strike == 3){
                System.out.println("축하합니다~ 정답입니다.");
                break;
            }else {
                System.out.println("스트라이크 개수 : " + strike + "/ 볼 개수 : " + ball);
            }
        }
    }

    //스트라이크의 갯수를 세기
    private int countStrike(String input){
        int strike = 0;
        for(int i=0; i<3 ; i++){
            //Ascii 값에서 0에 해당하는 값을 빼면 숫자(정수)니까 그 값을 가져옴
            int guessNumber = input.charAt(i) - '0';

            //입력된 숫자가 정확한 위치에 있고, 그 수가 정답에 있다면 strike 개수를 증가
            if (guessNumber == answer.get(i)){
                strike++;
            }
        }
        return strike;
    }

    private int countBall(String input){
        int ball = 0;
        for(int i=0; i<3 ; i++){
            //Ascii 값에서 0에 해당하는 값을 빼면 숫자(정수)니까 그 값을 가져옴
            int guessNumber = input.charAt(i) - '0';

            //입력된 숫자가 위치는 다르지만 정답에 있다면 ball 개수를 증가
            //contains : 문자열 포함 여부 확인
            if (guessNumber != answer.get(i) && answer.contains(guessNumber)){
                ball++;
            }
        }
        return ball;
    }

}

