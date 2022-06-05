package blackjack.domain.player

import blackjack.domain.card.Card

data class Player(
    val name: String
) {
    val hand: Hand = Hand()
    var status: PlayerStatus = PlayerStatus.HIT
        private set

    fun addCards(vararg cards: Card) {
        cards.forEach { hand.add(it) }
        if (hand.isBust()) {
            changeStatus(PlayerStatus.BUST)
        }
    }

    fun score(): Int {
        return hand.score()
    }

    fun changeStatus(status: PlayerStatus) {
        this.status = status
    }
}
