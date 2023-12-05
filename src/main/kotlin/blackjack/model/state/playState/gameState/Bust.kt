package blackjack.model.state.playState.gameState

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.state.playState.Finished

class Bust(override val cardDeck: CardDeck) : Finished(cardDeck) {

    init {
        require(cardDeck.isBust()) { NOT_BUST_MESSAGE }
    }

    override fun isBust(): Boolean {
        return true
    }

    override fun isBlackJack(): Boolean {
        return false
    }

    override fun isStay(): Boolean {
        return false
    }

    override fun cards(): List<Card> {
        return cardDeck.deck
    }

    override fun calculateScore(): Int {
        return cardDeck.calculateScore()
    }

    companion object {
        private const val NOT_BUST_MESSAGE = "버스트가 아닙니다."
    }
}
