package next.step.blackjack.domain.player

import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.player.state.HitAvailableState
import next.step.blackjack.domain.player.state.PlayerState

data class Player(val name: String, private val cards: PlayerCards, private var state: PlayerState) {

    fun hit(card: Card) {
        cards.add(card)
        state = state.next(cards)
    }

    fun canHit(): Boolean = state.canHit()

    fun cardDescs(): Set<String> = cards.descs()

    fun point(): Int = cards.point()

    companion object {
        fun of(name: String, cards: PlayerCards = PlayerCards.of(emptyList())): Player =
            Player(name, cards, HitAvailableState.next(cards))
    }
}
