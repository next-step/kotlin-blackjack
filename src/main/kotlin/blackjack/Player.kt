package blackjack

import blackjack.card.Card
import blackjack.card.deck.PlayerCardDeck
import blackjack.card.score.BlackJackScoringStrategy

class Player(
    val name: String,
    private val playerCardDeck: PlayerCardDeck = PlayerCardDeck(),
) {
    fun hit(vararg cards: Card) {
        hit(cards.toList())
    }

    fun hit(cards: List<Card>) {
        cards.forEach { playerCardDeck.addCard(it) }
    }

    fun getCards(): List<Card> {
        return playerCardDeck.getCards()
    }

    fun ableToCastCard(strategy: BlackJackScoringStrategy): Boolean {
        return strategy.score(playerCardDeck) < BLACK_JACK_GOAL_NUMBER
    }

    fun score(strategy: BlackJackScoringStrategy): Int {
        return strategy.score(playerCardDeck)
    }

    companion object {
        private const val BLACK_JACK_GOAL_NUMBER = 21
    }
}
