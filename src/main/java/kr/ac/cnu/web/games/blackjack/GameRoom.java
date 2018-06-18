package kr.ac.cnu.web.games.blackjack;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by rokim on 2018. 5. 26..
 */
public class GameRoom {
    @Getter
    private final String roomId;
    @Getter
    private final Dealer dealer;
    @Getter
    private final Map<String, Player> playerList;
    @Getter
    private final Deck deck;
    @Getter
    private boolean isFinished;
    @Getter
    private boolean isDoubleDown;
    private final Evaluator evaluator;

    public GameRoom(Deck deck) {
        this.roomId = UUID.randomUUID().toString();
        this.deck = deck;
        this.dealer = new Dealer(new Hand(deck));
        this.playerList = new HashMap<>();
        this.evaluator = new Evaluator(playerList, dealer);
        this.isFinished = true;
    }

    public void addPlayer(String playerName, long seedMoney) {
        Player player = new Player(seedMoney, new Hand(deck));

        playerList.put(playerName, player);
    }

    public void removePlayer(String playerName) {
        playerList.remove(playerName);
    }

    public void reset() {
        dealer.reset();
        playerList.forEach((s, player) -> player.reset());
    }

    public void bet(String name, long bet) {
        Player player = playerList.get(name);

        player.placeBet(bet);
    }

    public void deal() {
        this.isFinished = false;
        dealer.deal();
        playerList.forEach((s, player) -> player.deal());
    }

    public Card hit(String name) {
        Player player = playerList.get(name);

        return player.hitCard();
    }

    public void stand(String name) {
        Player player = playerList.get(name);

        player.stand();
    }

    public void doubleDown(String name){ //double down 기능 추가 구현
        this.isDoubleDown = true;
        Player player = playerList.get(name);
        long plusBetting = player.getCurrentBet();

        player.placeBet(plusBetting); //배팅을 2배로한다(그래서 한번 더 배팅한다.)
        player.hitCard(); //카드를 한장 받고
        player.stand(); //게임을 종료한다.
    }

    public void playDealer() {
        dealer.play();
        evaluator.evaluate();
        this.isFinished = true;
    }

}
