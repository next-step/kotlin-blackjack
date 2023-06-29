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
