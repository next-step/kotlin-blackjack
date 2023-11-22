package blackjack_dealer.domain

import blackjack_dealer.CardGenerator
import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.GamerCards
import blackjack_dealer.entity.state.GamerCurrentState

abstract class Gamer(
    open val name: String,
) {
    protected val gamerCards: GamerCards = GamerCards()
    protected var currentState: GamerCurrentState = GamerCurrentState.INITIAL

    fun getGamerName(): String = name
    fun getCurrentCards(): GamerCards = gamerCards
    fun canKeepPlayingGame(): Boolean = currentState is GamerCurrentState.HIT
    fun initializeCard(cards: GamerCards) { cards.forEach { card -> gamerCards.add(card) } }

    fun getCurrentGamerState(): GamerCurrentState = currentState

    open fun drawCard(cardDeque: CardDeque) {
        val newCard = CardGenerator.generateSingleCard(cardDeque)
        gamerCards.add(newCard)
    }

    companion object {
        const val BLACK_JACK = 21
    }
}
