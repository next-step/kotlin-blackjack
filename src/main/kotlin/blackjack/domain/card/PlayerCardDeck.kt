package blackjack.domain.card

import blackjack.domain.player.PlayerName
import java.util.LinkedList

class PlayerCardDeck(
    private val playerName: PlayerName,
) {

    private val _cards = LinkedList<Card>()
    val cards: List<Card> = _cards

    fun insert(card: Card) {
        _cards.add(card)
    }

    fun insertAll(newCards: List<Card>) {
        _cards.addAll(newCards)
    }

    fun isNotEmpty(): Boolean {
        return _cards.isNotEmpty()
    }

    fun isEmpty(): Boolean {
        return _cards.isEmpty()
    }

    fun capture(): PlayerCardDeckCapture {
        return PlayerCardDeckCapture(
            playerName = playerName,
            cards = _cards.toList(),
        )
    }
}
