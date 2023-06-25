package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.PlayerCardDeck
import blackjack.domain.card.PlayerCardDeckCapture
import blackjack.domain.score.CardScoreCalculator

class Player(
    val name: PlayerName,
) {

    private val cardDeck = PlayerCardDeck(name)
    private val cardScoreCalculator = CardScoreCalculator()

    fun pass(card: Card) {
        cardDeck.insert(card)
    }

    fun pass(cards: List<Card>) {
        cardDeck.insertAll(cards)
    }

    fun hasCard(): Boolean {
        return cardDeck.isNotEmpty()
    }

    fun captureCardDeck(): PlayerCardDeckCapture {
        return cardDeck.capture()
    }

    fun isBust(): Boolean {
        return cardScoreCalculator.calculateScore(cardDeck.cards).isBust
    }
}

fun List<Player>.captureAllCardDecks(): List<PlayerCardDeckCapture> {
    return map { it.captureCardDeck() }
}
