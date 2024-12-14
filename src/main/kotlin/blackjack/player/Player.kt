package blackjack.player

import blackjack.card.Card
import blackjack.dealer.Dealer
import blackjack.participant.Participant

data class Player(
    override val name: String,
    override val hand: Hand = Hand(cards = emptyList()),
) : Participant<Player> {
    override fun hitCard(card: Card): Player = Player(name = name, hand = hand.add(card))

    override fun isWin(opponent: Participant<*>): Boolean? =
        (opponent as? Dealer)?.let { dealer ->
            when {
                dealer.isBust() -> true
                isBust() -> false
                else -> hand.sum() > opponent.hand.sum()
            }
        }

    override fun equals(other: Any?): Boolean =
        when {
            this === other -> true
            other !is Player -> false
            else -> name == other.name
        }

    override fun hashCode(): Int = name.hashCode()

    companion object {
        fun ready(name: String): Player = Player(name = name)
    }
}
