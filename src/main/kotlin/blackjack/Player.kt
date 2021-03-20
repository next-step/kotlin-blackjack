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

    fun getCardText(): String {
        val cardTexts = cardDeck.cards.map { it.cardNumber.showName + getCardTypeText(it.type) }
        return "${name}카드: ${cardTexts.joinToString(", ")}"
    }

    fun isDead(): Boolean {
        return cardDeck.getScore() > BLACK_JACK_NUM
    }
}

class Players(private val _players: List<Player>) {
    val players: List<Player>
        get() = _players.toList()

    fun addCardAllPlayer(cardExtractor: CardExtractor) {
        players.forEach { it.addCard(cardExtractor.getCard()) }
    }
}
