### 좌표계산기 구현
---
## 기능 요구 사항
블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
* 플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다.
* 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
* 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다. 단, 카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다.
* 처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다. 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.
* 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다. 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.


---
## 실행 결과 예시
실행 결과

게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)

pobi,jason


pobi의 배팅 금액은?

10000


jason의 배팅 금액은?

20000

딜러와 pobi, jason에게 2장의 나누었습니다.

딜러: 3다이아몬드

pobi카드: 2하트, 8스페이드

jason카드: 7클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)

y

pobi카드: 2하트, 8스페이드, A클로버

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)

n

jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)

n

jason카드: 7클로버, K스페이드


딜러는 16이하라 한장의 카드를 더 받았습니다.

딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20

pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21

jason카드: 7클로버, K스페이드 - 결과: 17

최종 수익

딜러: 10000

pobi: 10000

jason: -20000

---
## 개발 설계
1. 이름 중복없는 플레이어 리스트를 만든다.
2. 참가자 별로 배팅 금액을 설정한다.
3. 배팅이 끝나면 딜러 + 참가자들은 랜덤으로 2장의 카드를 갖게 한다.
4. 참가자 중 처음 받은 카드에서 블랙잭이 존재하는지 확인한다. 존재하는 경우 딜러도 확인하고 금액을 준다.
5. 참가자 순서대로 카드를 원하는 만큼 더 받게 한다.
6. 모든 참가자가 카드를 다 가져가고 나면 딜러는 처음 받았던 카드 숫자 합이 16이하이면 한장을 더 받게 한다.
7. 딜러와 플레이어들의 카드를 비교하고 각자의 수익을 계산한다.
    1. 딜러가 21을 초과하면 무조건 플레이어의 승리
    2. 21이하의 숫자에서 딜러와 플레이어가 동점이라면 딜러 승리

---
## 구현한 게임 실행 결과 예시
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
a,b,c,d,e

a의 배팅 금액은?
1

b의 배팅 금액은?
2

c의 배팅 금액은?
1

d의 배팅 금액은?
3

e의 배팅 금액은?
1

dealer, a, b, c, d, e에게 2장의 카드씩 나누었습니다.

dealer의 카드 : 10하트, 11클로버

a의 카드 : 1스페이스, 10클로버

b의 카드 : 4스페이스, 11다이아

c의 카드 : 1다이아, 9스페이스

d의 카드 : 2하트, 6스페이스

e의 카드 : 8하트, 13스페이스

b는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)

y

b의 카드 : 4스페이스, 11다이아, 4다이아

b는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)

y

b의 카드 : 4스페이스, 11다이아, 4다이아, 8스페이스

c는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)

y

c의 카드 : 1다이아, 9스페이스, 2클로버

c는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)

y

c의 카드 : 1다이아, 9스페이스, 2클로버, 9다이아

d는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)

y

d의 카드 : 2하트, 6스페이스, 13클로버

최종 수익

dealer : 4

a : 1

b : -2

c : -1

d : -3

e : 1
