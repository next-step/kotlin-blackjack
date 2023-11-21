package blackjack.model.state.playState.gameState

import blackjack.model.card.CardDeck
import blackjack.model.state.playState.Finished

class Bust(override val cardDeck: CardDeck) : Finished(cardDeck) {

    init {
        require(cardDeck.isBust()) { NOT_BUST_MESSAGE }
    }

    override fun cards(): CardDeck {
        return cardDeck
    }

    companion object {
        private const val NOT_BUST_MESSAGE = "버스트가 아닙니다."
    }
}
