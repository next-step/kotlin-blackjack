package blackjack.model.state.playState.gameState

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.state.playState.Finished

class BlackJack(override val cardDeck: CardDeck) : Finished(cardDeck) {

    init {
        require(cardDeck.isBlackJack()) { NOT_BLACKJACK_MESSAGE }
    }

    override fun cards(): List<Card> {
        return cardDeck.deck
    }

    override fun calculateScore(): Int {
        return CardDeck.BLACKJACK_NUMBER
    }

    companion object {
        private const val NOT_BLACKJACK_MESSAGE = "블랙잭이 아닙니다."
    }
}
