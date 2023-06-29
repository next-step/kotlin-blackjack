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

    fun batting(dealer: Dealer, money: Money) {
        dealer.addBattingMoney(this, money)
    }

    fun drawCard(trump: Trump) {
        cards.add(trump.getCard())
    }

    fun win() = info.result.record.win()

    fun lose() = info.result.record.lose()

    fun addMoney(amount: Money) {
        info.result.addMoney(amount)
    }

    fun minusMoney(amount: Money) {
        info.result.minusMoney(amount)
    }
}
