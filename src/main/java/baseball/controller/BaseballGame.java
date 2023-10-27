package baseball.controller;

import baseball.domain.AnswerChecker;
import baseball.domain.Computer;
import baseball.io.Input;
import baseball.io.OutPrint;
import baseball.type.ReStartFlag;
import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BaseballGame {

    private BaseballGame() {
    }

    /*
     * 게임을 동작시키는 기능
     * */
    public static void run() {
        OutPrint.initMsg();
        start();
    }

    /*
     * 게임을 동작하는 기능
     * */
    private static void start() {

        List<Integer> answer = Computer.newNumber();
        String result = "";

        while (!result.equals("3스트라이크")) {

            OutPrint.inputMsg();
            String input = Input.stringNum();

            // 입력받은 StringNum을 List<Integer>로 바꾸어 정답체크 결과출력
            List<Integer> userInput = stringToIntegerList(input);
            result = AnswerChecker.result(answer, userInput);

            OutPrint.checkResultMsg(result);
        }
        // 정답을 맞춘 후 게임을 지속할 지 여부 묻기
        OutPrint.correctMsg();
        continueGame();
    }

    /*
     * 비정상적인 입력으로 인해 예외를 발생시켜 종료시키는 기능
     * */
    public static void exit(String str) {

        OutPrint.errorMsg(str);
        throw new IllegalArgumentException(str);
    }

    /*
     * 게임 지속여부를 체크하여 처리하는 기능
     * */
    private static void continueGame() {

        OutPrint.continueMsg();
        String input = Input.reStartOption();

        if (ReStartFlag.isRestart(input)) {
            start();
        } else if (ReStartFlag.isEnd(input)) {
            OutPrint.expireMsg();
            Console.close();
        }
    }

    /*
     * String을 Integer List로 변환하는 기능
     * */
    private static List<Integer> stringToIntegerList(String strNum) {
        return Arrays.stream(strNum.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

}
