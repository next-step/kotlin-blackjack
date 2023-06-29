package blackjack.domain.model

import blackjack.domain.Rule

class Dealer(
    trump: Trump,
    cards: Cards = Cards(trump = trump)
) : Player(trump, PlayerInfo(NAME), cards) {
    private val battingMoneys = hashMapOf<Player, Money>()
    override fun canDrawCard(): Boolean {
        if (cards.items.size >= MAXIMUM_CARD_COUNT) return false
        return cards.sum < Rule.DEALER_MINIMUM_SCORE
    }

    fun addBattingMoney(player: Player, money: Money) {
        battingMoneys[player] = money
    }

    fun settleMoney(player: Player) {
        val battingMoney = battingMoneys[player]?.money ?: throw IllegalStateException("플레이어가 배팅을 하지 않았습니다")

        val winner = Rule.decisionWinner(this, player) ?: run {
            draw(player, battingMoney)
            return
        }

        if (winner == player && player.hasTwoCards() && player.isBlackJack()) {
            val reward = (battingMoney * 1.5).toInt()
            lose(player, reward)
            return
        }

        if (winner == this) {
            win(player, battingMoney)
        } else {
            lose(player, battingMoney)
        }
    }

    private fun win(player: Player, battingMoney: Int) {
        val money = Money(battingMoney)
        this.addMoney(money)
        player.minusMoney(money)
    }

    private fun lose(player: Player, battingMoney: Int) {
        player.addMoney(Money(battingMoney))
    }

    private fun draw(player: Player, battingMoney: Int) {
        player.addMoney(Money(battingMoney))
    }

    companion object {
        const val NAME = "딜러"
        private const val MAXIMUM_CARD_COUNT = 3
    }
}
