package blackjack.model.player.playblestrategy.impl

import blackjack.model.player.playable.impl.Player
import blackjack.model.player.playblestrategy.PlayingStrategy

class ConsoleInputStrategy(
    val player: Player,
) : PlayingStrategy {

    override fun isHit(): Boolean {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return this.isHitInput()
    }

    private fun isHitInput(): Boolean {
        return (readlnOrNull() ?: "") == HIT_COMMAND
    }

    companion object {
        private const val HIT_COMMAND: String = "y"
    }
}
