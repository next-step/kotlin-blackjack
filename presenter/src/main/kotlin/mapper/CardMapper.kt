import blackjack.card.Card
import blackjack.card.CardRank
import blackjack.card.CardSuit

fun Card.toUiString(): String {
    return buildString {
        when (rank) {
            CardRank.ACE -> append("A")
            CardRank.TWO -> append("2")
            CardRank.THREE -> append("3")
            CardRank.FOUR -> append("4")
            CardRank.FIVE -> append("5")
            CardRank.SIX -> append("6")
            CardRank.SEVEN -> append("7")
            CardRank.EIGHT -> append("8")
            CardRank.NINE -> append("9")
            CardRank.TEN -> append("10")
            CardRank.JACK -> append("J")
            CardRank.QUEEN -> append("Q")
            CardRank.KING -> append("K")
        }
        when (suit) {
            CardSuit.HEARTS -> append("하트")
            CardSuit.CLUBS -> append("클로버")
            CardSuit.DIAMONDS -> append("다이아")
            CardSuit.SPADES -> append("스페이드")
        }
    }
}
