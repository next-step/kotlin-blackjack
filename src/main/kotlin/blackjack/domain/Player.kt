package blackjack.domain

class Player(
    name: String,
    cards: Cards = Cards(),
    private val betMoney: Money = Money()
) : BlackJackPlayer(name, cards) {

    override fun firstOpenCards(): Cards {
        return cards.take(FIRST_OPEN_COUNT)
    }

    override fun isHit(): Boolean {
        return status == PlayerStatus.HIT
    }

    fun stand() {
        forceUpdateStatus(PlayerStatus.STAND)
    }

    fun winLoseMoney(otherPlayer: BlackJackPlayer): Money {
        val winLose = match(otherPlayer)
        return when {
            winLose == WinLose.WIN -> betMoney * earningRate()
            winLose == WinLose.LOSE -> betMoney.opposite()
            winLose == WinLose.DRAW -> Money()
            otherPlayer.status == PlayerStatus.BURST -> betMoney
            else -> throw IllegalArgumentException("계산할 수 없습니다.")
        }
    }

    private fun earningRate(): Double {
        return when (status) {
            PlayerStatus.BLACKJACK -> 1.5
            PlayerStatus.STAND -> 1.0
            PlayerStatus.BURST -> -1.0
            else -> throw IllegalArgumentException("계산할 수 없습니다.")
        }
    }

    companion object {
        private const val FIRST_OPEN_COUNT = 2
    }
}
