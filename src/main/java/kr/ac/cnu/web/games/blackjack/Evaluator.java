package kr.ac.cnu.web.games.blackjack;

import java.util.Map;

/**
 * Created by rokim on 2018. 5. 27..
 */
public class Evaluator {
    private Map<String, Player> playerMap;
    private Dealer dealer;

    public Evaluator(Map<String, Player> playerMap, Dealer dealer) {
        this.playerMap = playerMap;
        this.dealer = dealer;
    }

    public boolean evaluate() {
        if (playerMap.values().stream().anyMatch(player -> player.isPlaying())) {
            return false;
        }

        int dealerResult = dealer.getHand().getCardSum();

        if (dealerResult > 21) {
            playerMap.forEach((s, player) -> {
                int playerResult = player.getHand().getCardSum();
                if(playerResult > 21) { //dealerResult와 playerResult가 둘 다 21이 넘으면
                    player.lost(); //player가 무조건 진다.
                }
                else{ //만약 playerResult가 21이하이고 dealerResult는 21이 넘으면
                    player.win(); //player가 승리한다.
                }
            });

            return true;
        }

        playerMap.forEach((s, player) -> {
            int playerResult = player.getHand().getCardSum();
            if (playerResult > 21) {
                player.lost();
            } else if (playerResult > dealerResult) {
                player.win();
            } else if (playerResult == dealerResult) {
                player.tie();
            } else {
                player.lost();
            }
        });

        return true;
    }


}
