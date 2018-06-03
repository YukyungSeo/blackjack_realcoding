package kr.ac.cnu.web.games.blackjack;

import kr.ac.cnu.web.exceptions.NoSuchRankException;
import lombok.Data;

/**
 * Created by rokim on 2018. 5. 26..
 */
@Data
public class Card {
    private final int rank;
    private final Suit suit;

    public int getRankForSum(){
        if(this.rank == 11 || this.rank == 12 || this.rank == 13){
            return 10;
        }
        return this.rank;
    }

    public Card(int rank, Suit suit) {
        if (rank > 13) {
            throw new NoSuchRankException();
        }
        this.rank = rank;
        this.suit = suit;
    }
}
