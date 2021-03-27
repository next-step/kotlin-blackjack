package blackjack

abstract class User(val name: String) {
    val cardDeck = Cards()

    init {
        require(name.isNotBlank()) { "이름이 공백이어서는 안됩니다." }
    }

    fun addCard(card: Card) {
        cardDeck.add(card)
    }

    fun isDead(): Boolean {
        return cardDeck.getScore() > Card.BLACK_JACK_NUM
    }

    open fun hit(cardExtractor: CardExtractor) {
        repeat(HIT_COUNT) {
            addCard(cardExtractor.getCard())
        }
    }

    companion object {
        private const val HIT_COUNT = 2
    }
}
