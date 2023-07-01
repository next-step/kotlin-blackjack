package blackjack.domain.model

import blackjack.domain.Rule

open class Player(
    trump: Trump,
    val info: PlayerInfo = PlayerInfo(),
    val cards: Cards = Cards(trump = trump),
) {

    open fun canDrawCard(): Boolean {
        return cards.sum < Rule.BLACK_JACK
    }

    fun isBlackJack(): Boolean = cards.sum == Rule.BLACK_JACK

    fun hasTwoCards(): Boolean = cards.items.size == 2

    fun batting(batting: Batting, money: Money) {
        batting.addBattingMoney(this, money)
    }

    fun drawCard(trump: Trump) {
        cards.add(trump.getCard())
    }

    fun recordWin() = info.result.record.win()

    fun recordLose() = info.result.record.lose()

    fun addMoney(amount: Money) {
        info.result.addMoney(amount)
    }

    fun minusMoney(amount: Money) {
        info.result.minusMoney(amount)
    }
}
