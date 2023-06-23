package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.PlayerCardDeck
import blackjack.domain.card.PlayerCardDeckCapture

class Player(
    name: PlayerName,
) {

    private val cardDeck = PlayerCardDeck(name)

    fun pass(cards: List<Card>) {
        cardDeck.insertAll(cards)
    }

    fun notHasCard(): Boolean {
        return hasCard().not()
    }

    fun hasCard(): Boolean {
        return cardDeck.isNotEmpty()
    }

    fun captureCardDeck(): PlayerCardDeckCapture {
        return cardDeck.capture()
    }
}

fun List<Player>.captureAllCardDecks(): List<PlayerCardDeckCapture> {
    return map { it.captureCardDeck() }
}
