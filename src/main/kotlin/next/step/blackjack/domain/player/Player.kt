package next.step.blackjack.domain.player

import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.player.state.HitAvailableState
import next.step.blackjack.domain.player.state.PlayerState

data class Player(
    private val name: PlayerName,
    private val cards: Cards = Cards.of(emptyList()),
    private var state: PlayerState = HitAvailableState
) {

    fun name() = name.name

    fun hit(card: Card) {
        cards.add(card)
        state = state.next(cards)
    }

    fun canHit(): Boolean = state.canHit()

    fun cardDescs(): Set<String> = cards.descs()

    fun point(): Int = cards.point()

    companion object {
        fun of(name: PlayerName, cards: Cards): Player =
            Player(name, cards, HitAvailableState.next(cards))

    }
}
