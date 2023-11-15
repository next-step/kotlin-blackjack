package blackjack.domain

class Dealer(cards: Cards = Cards()) : BlackJackPlayer(DEALER_NAME, cards) {

    override fun firstOpenCards(): Cards {
        return cards.take(FIRST_OPEN_COUNT)
    }

    override fun isHit(): Boolean {
        return cards.score() < Score(DEALER_HIT_SCORE)
    }

    fun winLoseMoney(players: List<Player>): Money {
        var winLoseMoney: Money = Money()
        players.forEach {
            val playerEarningMoney = it.winLoseMoney(this)
            winLoseMoney += playerEarningMoney.opposite()
        }
        return winLoseMoney
    }

    companion object {
        private const val DEALER_HIT_SCORE = 17
        private const val DEALER_NAME = "딜러"
        private const val FIRST_OPEN_COUNT = 1
    }
}
