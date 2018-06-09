package kr.ac.cnu.web.games.blackjack;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by rokim on 2018. 5. 26..
 */
public class Hand {
    @Getter
    private Deck deck;
    @Getter
    private List<Card> cardList = new ArrayList<>();

    public Hand(Deck deck) {
        this.deck = deck;
    }

    public Card drawCard() {
        Card card = deck.drawCard();
        cardList.add(card);
        return card;
    }

    public int getCardSum() {
        int sum =  cardList.stream().mapToInt(card -> card.getRankForSum()).sum();
        Iterator<Card> iterator = cardList.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getRank() == 1){
                sum += 10;  // 일단 11로 계산해 본 후
                if(sum > 21){ sum -= 10; } // 21 초과면 다시 원상 복구
                                            // 21 이하면 그대로 11로 계산
            }
        }
        return sum;
    }

    public void reset() {
        cardList.clear();
    }
}
