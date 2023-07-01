package blackjack.domain.model

import blackjack.domain.Rule

class Batting {
    private val battingMoneys = hashMapOf<Player, Money>()

    fun addBattingMoney(player: Player, money: Money) {
        battingMoneys[player] = money
    }

    fun settleMoney(player: Player, dealer: Dealer) {
        val battingMoney = battingMoneys[player]?.money ?: throw IllegalStateException("플레이어가 배팅을 하지 않았습니다")
        val winner = Rule.decisionWinner(dealer, player)

        if (winner == dealer) {
            dealer.addMoney(Money(battingMoney))
            player.minusMoney(Money(battingMoney))
            return
        }

        if (winner == player && player.hasTwoCards() && player.isBlackJack()) {
            val reward = (battingMoney * BLACK_JACK_BONUS).toInt()
            player.addMoney(Money(reward))
            return
        }
        player.addMoney(Money(battingMoney))
    }

    companion object {
        private const val BLACK_JACK_BONUS = 1.5
    }
}
