package blackjack.domain.player

import blackjack.domain.card.PlayingCard
import blackjack.domain.state.Bust
import blackjack.domain.state.Ready
import blackjack.domain.state.State

class Player(
    private val name: PlayerName,
    private var state: State = Ready()
) {

    fun cards(): List<PlayingCard> = state.hands.cards


    fun drawCard(card: PlayingCard) {
        state = state.draw(card)
    }

    fun stay() {
        state = state.stay()
    }
    fun isBust(): Boolean = state is Bust
    fun score(): Int = state.hands.score()

    fun isRunning(): Boolean = state.isRunning()

    fun calculateProfit(betAmount: Int): Double = state.profit(betAmount)
    override fun toString(): String = name.toString()

    companion object {
        fun of(name: String): Player {
            return Player(PlayerName.from(name))
        }
    }
}
