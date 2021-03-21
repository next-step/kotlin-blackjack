package blackjack

import blackjack.Card.Companion.BLACK_JACK_NUM

class Player(val name: String) {
    val cardDeck = Cards()

    init {
        require(name.isNotBlank()) { "이름이 공백이어서는 안됩니다." }
    }

    fun addCard(card: Card) {
        cardDeck.add(card)
    }

    fun isDead(): Boolean {
        return cardDeck.getScore() > BLACK_JACK_NUM
    }
}

class Players(private val _players: List<Player>) {
    val players: List<Player>
        get() = _players.toList()

    fun hit(cardExtractor: CardExtractor) {
        repeat(HIT_COUNT) {
            players.forEach { it.addCard(cardExtractor.getCard()) }
        }
    }

    companion object {
        private const val HIT_COUNT = 2
    }
}
