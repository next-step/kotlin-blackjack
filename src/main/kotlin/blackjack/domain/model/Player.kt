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

    open fun drawCard(trump: Trump) {
        cards.add(trump.getCard())
    }

    fun win() = info.record.win()

    fun lose() = info.record.lose()
}
