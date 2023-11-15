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

    fun winLoseMoney(winLose: WinLose): Money {
        return betMoney.calculateWinLoseMoney(winLose, isBlackJack())
    }

    companion object {
        private const val FIRST_OPEN_COUNT = 2
    }
}
