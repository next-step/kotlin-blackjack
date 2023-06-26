package next.step.blackjack.domain.dealer

import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.card.state.CardsState
import next.step.blackjack.domain.card.state.UnfinishedState
import next.step.blackjack.domain.player.Player
import next.step.blackjack.domain.player.PlayerName

class Dealer(
    name: PlayerName = PlayerName.of(DEALER_NAME),
    cards: Cards = Cards.of(emptyList()),
    state: CardsState = UnfinishedState
) : Player(name, cards, state) {

    fun cardDescFirst(): String = cards.descFirst()

    fun turn(cardGenerator: () -> Card, announce: (String) -> Unit) {
        if (canHit()) {
            hit(cardGenerator())
            announce(name())
        }
    }

    override fun canHit(): Boolean = cards.point() <= HIT_AVAILABLE_POINT

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val HIT_AVAILABLE_POINT = 16

        fun of(cards: Cards): Dealer = Dealer(cards = cards, state = UnfinishedState.next(cards))
    }
}