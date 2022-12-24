package blackjack.domain

import blackjack.domain.Game.Companion.INITIAL_CARDS_COUNT
import blackjack.domain.Playing.Hit
import blackjack.model.Card

class GameDealer(
    initialState: State = Started(),
    private val _deck: CardDeck = GameCardDeck()
) : Dealer {
    override val name: String = "딜러"
    override val deck: CardDeck
        get() = _deck
    override val cards: Cards
        get() = _state.cards

    private var _state: State = initialState
    override val state: State
        get() = _state
    override val finished: Boolean
        get() = state.finished

    override fun shouldBeReadyToPlay(): Boolean =
        cards.size == INITIAL_CARDS_COUNT && state !is Started

    override fun deliverCard(): Card = _deck.takeOutFirstCard()

    override fun shuffle() = _deck.shuffle()

    override fun draw(card: Card) {
        _state = state.draw(card)
        if (shouldStay()) _state = state.stay()
    }

    override fun sumCards(): Int = state.cards.sum()

    private fun shouldStay(): Boolean = state is Hit && cards.sum() >= STAY_SCORE

    companion object {
        private const val STAY_SCORE = 17
    }
}
