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
        return when (match(otherPlayer)) {
            WinLose.WIN -> betMoney * earningRate()
            WinLose.LOSE -> betMoney.opposite()
            WinLose.DRAW -> Money()
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
