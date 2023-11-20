package blackjack.model.state.playState.gameState

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.state.State
import blackjack.model.state.playState.Running

class Hit(override val cardDeck: CardDeck) : Running() {

    override fun draw(card: Card): State {
        cardDeck.add(card)
        return when {
            cardDeck.size() == 2 && cardDeck.isBlackJack() -> BlackJack(cardDeck)
            cardDeck.isBust() -> Bust(cardDeck)
            else -> Hit(cardDeck)
        }
    }

    override fun cards(): CardDeck {
        return cardDeck
    }
}
