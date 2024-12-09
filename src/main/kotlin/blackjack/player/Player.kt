package blackjack.player

import blackjack.card.Card
import blackjack.participant.Participant

class Player(
    override val name: String,
    override val hand: Hand = Hand(cards = emptyList()),
) : Participant<Player> {
    override fun hitCard(card: Card): Player = Player(name = name, hand = hand.add(card))

    override fun isWin(opponent: Participant<*>): Boolean =
        when {
            opponent.isBust() -> true
            isBust() -> false
            else -> hand.sum() > opponent.hand.sum()
        }

    companion object {
        fun ready(name: String): Player = Player(name = name)
    }
}
