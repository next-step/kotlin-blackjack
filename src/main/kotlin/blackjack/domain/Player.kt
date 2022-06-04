package blackjack.domain

class Player(val name: String, hand: Hand = Hand(emptyList())) {
    private var _hand = hand

    val score: Score
        get() = Score(_hand.cards)

    val hand: Hand
        get() = _hand

    fun addCard(card: Card) {
        _hand = hand.addCard(card)
    }
}
