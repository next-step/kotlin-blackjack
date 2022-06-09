package blackjack.state

import blackjack.Card
import blackjack.PlayerDeck

class Ready(private val playerDeck: PlayerDeck) : State {
    override fun currentCard(): PlayerDeck = playerDeck

    override fun isFinish(): Boolean = false

    override fun draw(card: Card): State {
        playerDeck.addCard(card)

        when {
            playerDeck.cards.size < BASE_CARD_SIZE -> return Ready(playerDeck)
            playerDeck.cards.size == BASE_CARD_SIZE && score(playerDeck.cards) == BLACKJACK_NUMBER -> return BlackJack(playerDeck)
        }

        return Hit(playerDeck)
    }

    companion object {
        private const val BASE_CARD_SIZE = 2
    }
}
