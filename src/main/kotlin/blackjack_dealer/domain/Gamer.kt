package blackjack_dealer.domain

import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.GamerCards
import blackjack_dealer.entity.state.GamerCurrentState
import blackjack_dealer.entity.toGamerCards

abstract class Gamer(
    open val name: String,
) {
    protected lateinit var gamerCards: GamerCards
    protected var currentState: GamerCurrentState = GamerCurrentState.INITIAL

    fun getGamerName(): String = name
    fun getCurrentCards(): GamerCards = gamerCards.getCurrentCards()
    fun canKeepPlayingGame(): Boolean = currentState is GamerCurrentState.HIT
    fun initializeCard(cards: GamerCards) { gamerCards = cards }

    fun getCurrentGamerState(): GamerCurrentState = currentState

    open fun drawCard(cardDeque: CardDeque) {
        val newCard = cardDeque.generateSingleCard()
        gamerCards.addCard(newCard)
    }

    companion object {
        const val BLACK_JACK = 21
    }
}
