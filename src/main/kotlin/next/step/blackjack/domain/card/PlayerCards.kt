package next.step.blackjack.domain.card

import next.step.blackjack.domain.card.state.CardsState
import next.step.blackjack.domain.card.state.UnfinishedState
import next.step.blackjack.domain.game.GameResult

class PlayerCards(
    val cards: Cards = Cards.of(emptyList()),
    var state: CardsState = UnfinishedState
) {

    fun hit(card: Card) {
        cards.add(card)
        state = state.next(cards)
    }

    fun isUnfinished() = state == UnfinishedState

    fun descs(): Set<String> = cards.descs()

    fun descFirst(): String = cards.descFirst()

    fun cards(): List<Card> = cards.cards()

    fun point(): Int = cards.point()

    fun fight(other: PlayerCards): GameResult {
        return state.fight(other.state) then cards.fight(other.cards)
    }

    companion object {
        fun of(cards: Cards) = PlayerCards(cards, UnfinishedState.next(cards))
    }
}
