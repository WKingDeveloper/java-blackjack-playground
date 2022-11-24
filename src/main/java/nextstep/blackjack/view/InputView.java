package nextstep.blackjack.view;

import nextstep.blackjack.model.player.Users;

import java.util.Scanner;

public class InputView {

    private final static Scanner scanner = new Scanner(System.in);

    public String init() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return scanner.nextLine();
    }

    public String inputBatMoney(String name) {
        System.out.println(name +"의 배팅 금액은?");
        return scanner.nextLine();
    }

    public boolean inputAddUserCard(String name) {
        System.out.println(name +"는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String answer = scanner.nextLine();
        return answer.equals("y");
    }
}
