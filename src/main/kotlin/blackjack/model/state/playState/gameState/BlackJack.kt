package blackjack.model.state.playState.gameState

import blackjack.model.card.CardDeck
import blackjack.model.state.playState.Finished

class BlackJack(override val cardDeck: CardDeck) : Finished(cardDeck) {

    init {
        require(cardDeck.isBlackJack()) { NOT_BLACKJACK_MESSAGE }
    }

    override fun cards(): CardDeck {
        return cardDeck
    }

    companion object {
        private const val NOT_BLACKJACK_MESSAGE = "블랙잭이 아닙니다."
    }
}
