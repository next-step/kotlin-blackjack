package blackjack.player

import blackjack.card.Card
import blackjack.dealer.Dealer
import blackjack.participant.Participant

class Player(
    override val name: String,
    override val hand: Hand = Hand(cards = emptyList()),
) : Participant<Player> {
    override fun hitCard(card: Card): Player = Player(name = name, hand = hand.add(card))

    fun isWin(
        dealer: Dealer,
    ): Boolean =
        when {
            isBust() -> false
            dealer.isBust() -> true
            else -> hand.sum() > dealer.hand.sum()
        }

    companion object {
        fun ready(name: String): Player = Player(name = name)
    }
}
