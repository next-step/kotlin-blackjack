package next.step.blackjack.domain.player

import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.card.state.CardsState
import next.step.blackjack.domain.card.state.UnfinishedState

open class Player(
    private val name: PlayerName,
    private val cards: Cards = Cards.of(emptyList()),
    private var state: CardsState = UnfinishedState
) {

    fun name(): String = name.name

    fun hit(card: Card) {
        cards.add(card)
        state = state.next(cards)
    }

    open fun canHit(): Boolean = state == UnfinishedState

    fun cardDescs(): Set<String> = cards.descs()

    fun point(): Int = cards.point()

    companion object {
        fun of(name: PlayerName, cards: Cards): Player =
            Player(name, cards, UnfinishedState.next(cards))
    }
}
