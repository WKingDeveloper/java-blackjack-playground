package nextstep.blackjack.view;

import nextstep.blackjack.model.player.User;

import java.util.Scanner;

public class InputView {

    Scanner scanner = new Scanner(System.in);
    public String init() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return scanner.nextLine().replace(" ","");
    }

    public String setBatMoneyView(String key) {
        System.out.println(key + "의 배팅 금액은?");
        return scanner.nextLine();
    }

    public boolean addCard(String name, User user) {
        System.out.println(name +"는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        if(scanner.nextLine().equals("y")) return true;
        return false;
    }
}
// pobi, jason, kjs, wkd