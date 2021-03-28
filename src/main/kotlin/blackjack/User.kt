package blackjack

abstract class User(val name: String) {
    val cardDeck = Cards()

    init {
        require(name.isNotBlank()) { "이름이 공백이어서는 안됩니다." }
    }

    fun addCard(card: Card) {
        cardDeck.add(card)
    }

    fun isBust(): Boolean {
        return cardDeck.getScore() > Card.BLACK_JACK_NUM
    }

    open fun firstDeal(cardExtractor: CardExtractor) {
        repeat(FIRST_CARD_COUNT) {
            addCard(cardExtractor.getCard())
        }
    }

    companion object {
        private const val FIRST_CARD_COUNT = 2
    }
}
