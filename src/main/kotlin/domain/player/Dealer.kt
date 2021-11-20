package domain.player

import domain.card.CardGenerator
import domain.card.PlayingCards
import exception.IllegalDrawException

class Dealer(cards: PlayingCards) : Player(dealerInfo, cards) {
    constructor(cardGenerator: CardGenerator) : this(PlayingCards(cardGenerator))

    fun isDrawable(): Boolean = score() <= DRAWABLE_THRESHOLD

    fun draw(cardGenerator: CardGenerator) {
        if (!isDrawable()) {
            throw IllegalDrawException("딜러는 17점 이상이면 Draw 가 불가능합니다.")
        }
        play(true, cardGenerator)
    }

    companion object {
        private const val DRAWABLE_THRESHOLD = 16
        private const val DEALER_NAME = "딜러"
        private val dealerInfo = PlayerInfo(PlayerName(DEALER_NAME))
    }
}
