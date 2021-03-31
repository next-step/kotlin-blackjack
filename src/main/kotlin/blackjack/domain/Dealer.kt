package blackjack.domain

class Dealer(
    cards: Set<Card> = emptySet()
) : Participant {

    val player = Player("딜러", cards)
    val isUnderSixteen: Boolean
        get() = this.cardPointSum() <= DEALER_POINT_TO_TAKE_MORE_CARD
    val cardSize
        get() = player.cardSize

    override fun takeCard(card: Card) {
        player.takeCard(card)
    }

    override fun cardPointSum(): Int {
        return player.cardPointSum()
    }

    companion object {
        private const val DEALER_POINT_TO_TAKE_MORE_CARD = 16
    }
}
