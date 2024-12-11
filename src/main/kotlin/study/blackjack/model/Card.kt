package study.blackjack.model

/**
 * @author 이상준
 */
data class Card(
    val suit: Suit,
    val cardRank: CardRank,
) {
    fun score(): Int = cardRank.score()
}
