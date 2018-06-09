package kr.ac.cnu.web.games.blackjack;

import kr.ac.cnu.web.exceptions.NoMoreCardException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rokim on 2018. 5. 26..
 */
public class Deck {
    @Getter
    private final int number;
    @Getter
    private final List<Card> cardList;

    public Deck(int number) {
        this.number = number;
        this.cardList = new ArrayList<Card>();
        createCards(number);
        Collections.shuffle(cardList);
    }

    private void createCards(int number) {
        // create card for single deck
        for (int j = 0; j < number; j++) {
            for (Suit suit : Suit.values()) {
                for (int i = 1 ; i < 14; i++) {
                    Card card = new Card(i, suit);
                    cardList.add(card);
                }
            }
        }
    }

    public Card drawCard() {
        if (cardList.size() == 0) {
            // TODO 실제 게임에서 이런 일이 절대로 일어나면 안되겠죠?
            // 그래서 보통 게임에서는 N 장의 카드가 남으면 모든 카드를 합쳐서 다시 셔플 합니다.
            // 코드에 그런 내용이 들어가야 함.
            throw new NoMoreCardException();
        }
        return cardList.remove(0);
    }

    public void CardListSetting(){
        if (cardList.size() <= 10){ // [Add function] Deck에 남은 카드가 10장 이하인경우 shuffle
            // 게임이 끝나고 Deck에 남은 카드가 10장 이하인 경우 새롭게 shuffle 되도록 한다.
            cardList.clear();
            createCards(number);
            Collections.shuffle(cardList);
        }
    }
}
