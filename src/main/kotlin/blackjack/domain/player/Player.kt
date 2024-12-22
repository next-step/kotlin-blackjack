package blackjack.domain.player

import blackjack.domain.card.PlayingCard
import blackjack.domain.state.Hands
import blackjack.domain.state.Ready
import blackjack.domain.state.State

class Player private constructor(
    private val name: PlayerName,
    private var state: State = Ready()
) {
    val hands: Hands
        get() = state.hands

    fun drawCard(card: PlayingCard) {
        state = state.draw(card)
    }

    fun stay() {
        state = state.stay()
    }

    fun isRunning(): Boolean = state.isRunning()

    fun calculateProfit(betAmount: Int): Double = state.profit(betAmount)

    companion object {
        fun of(name: String): Player {
            return Player(PlayerName.from(name))
        }
    }
}
