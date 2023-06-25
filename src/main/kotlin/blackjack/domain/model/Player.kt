package blackjack.domain.model

import blackjack.domain.Rule

open class Player(
    game: Game,
    val info: PlayerInfo = PlayerInfo(),
    val cards: Cards = Cards(game = game),
) {

    open fun canGetCard(): Boolean {
        return cards.sum < Rule.BLACK_JACK
    }

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun win() = info.record.win()

    fun lose() = info.record.lose()
}
