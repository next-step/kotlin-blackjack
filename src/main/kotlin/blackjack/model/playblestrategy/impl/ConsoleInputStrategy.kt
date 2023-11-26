package blackjack.model.playblestrategy.impl

import blackjack.model.playblestrategy.PlayingStrategy

class ConsoleInputStrategy : PlayingStrategy {

    override fun isHit(): Boolean {
        println("플레이어는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return this.isHitInput()
    }

    private fun isHitInput(): Boolean {
        return (readlnOrNull() ?: "") == HIT_COMMAND
    }

    companion object {
        private const val HIT_COMMAND: String = "y"
    }
}
