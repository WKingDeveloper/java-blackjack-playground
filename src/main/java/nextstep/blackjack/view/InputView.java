package nextstep.blackjack.view;

import java.util.Scanner;

public class InputView {

    private final static Scanner scanner = new Scanner(System.in);

    public static String init() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return scanner.nextLine();
    }

    public static String inputBatMoney(String name) {
        System.out.println(name +"의 배팅 금액은?");
        return scanner.nextLine();
    }
}
