package blackjack.model.state.running

import blackjack.model.BlackJackGame.Companion.BLACKJACK_SCORE
import blackjack.model.card.Card
import blackjack.model.card.Cards
import blackjack.model.state.State
import blackjack.model.state.finished.Stay
import blackjack.model.state.finished.Bust

class Hit(override val cards: Cards) : Running() {

    override fun addCard(newCard: Card): State {
        val cardsAdded = cards + newCard

        if (cardsAdded.isBust())
            return Bust(cardsAdded)

        if (cardsAdded.score() == BLACKJACK_SCORE)
            return Stay(cardsAdded)

        return Hit(cardsAdded)
    }
}
