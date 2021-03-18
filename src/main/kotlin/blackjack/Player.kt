package blackjack

import blackjack.Card.Companion.BLACK_JACK_NUM

class Player(val name: String) {
    val cardDeck = Cards()

    fun getCardText(): String {
        val cardTexts = cardDeck.cards.map { it.cardNumber.showName + it.type.showName }
        return "${name}카드: ${cardTexts.joinToString(", ")}"
    }

    fun isDead(): Boolean {
        return cardDeck.getScore() > BLACK_JACK_NUM
    }
}

class Players(private val _players: List<Player>) {
    val players: List<Player>
        get() = _players.toList()
}
