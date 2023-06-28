package next.step.blackjack.domain.playercards

import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.game.GameResult
import next.step.blackjack.domain.playercards.state.BlackjackState
import next.step.blackjack.domain.playercards.state.PlayerCardsState
import next.step.blackjack.domain.playercards.state.StayState

data class PlayerCards(
    val cards: Cards = Cards.of(emptyList()),
    var state: PlayerCardsState = StayState
) {

    fun hit(card: Card) {
        cards.add(card)
        state = state.next(cards)
    }

    fun isStay(): Boolean = state == StayState

    fun isBlackjack(): Boolean = state == BlackjackState

    fun descs(): Set<String> = cards.descs()

    fun descFirst(): String = cards.descFirst()

    fun cards(): List<Card> = cards.cards()

    fun point(): Int = cards.point()

    fun fight(other: PlayerCards): GameResult {
        return state.fight(other.state) then cards.fight(other.cards)
    }

    companion object {
        fun of(cards: Cards) = PlayerCards(cards, StayState.next(cards))
    }
}
