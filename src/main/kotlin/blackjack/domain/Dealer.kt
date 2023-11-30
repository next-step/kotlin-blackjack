package blackjack.domain

class Dealer(
    private val hand: Hand = Hand(),
) {
    fun getCardList(): List<Card> = hand.cards
}
