package study.blackjack.model

/**
 * @author 이상준
 */
data class Card(
    val suit: Suit,
    val cardRank: CardRank,
) {
    fun score(isAce: Boolean = true): Int {
        return cardRank.score(isAce)
    }
}
