package blackjack_dealer.domain

import blackjack_dealer.entity.GamerCards
import blackjack_dealer.entity.state.GamerCurrentState

open class Gamer(
    open val name: String,
) {
    protected lateinit var gamerCards: GamerCards
    protected var currentState: GamerCurrentState = GamerCurrentState.INITIAL

    fun getGamerName(): String = name
    fun getCurrentCards(): GamerCards = gamerCards
    fun canKeepPlayingGame(): Boolean = currentState is GamerCurrentState.HIT

    companion object {
        const val BLACK_JACK = 21
    }
}
