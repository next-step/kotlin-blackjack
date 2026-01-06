package blackjack.domain

abstract class Participant(val name: String) {
    val hand = Hand()

    fun addCard(card: Card) = hand.add(card)

    fun score(): Int = hand.score()

    fun isBust(): Boolean = score() > BLACKJACK_SCORE

    fun isBlackjack(): Boolean = hand.count() == BLACKJACK_CARD_COUNT && score() == BLACKJACK_SCORE
}
