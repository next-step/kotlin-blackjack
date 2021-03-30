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
        return cardDeck.getScore() > BLACK_JACK_NUM
    }

    fun isBlackJack(): Boolean {
        return cardDeck.getScore() == BLACK_JACK_NUM && cardDeck.cards.size == FIRST_CARD_COUNT
    }

    abstract fun getEvaluate(users: Users): Int

    open fun firstDeal(cardExtractor: CardExtractor) {
        repeat(FIRST_CARD_COUNT) {
            addCard(cardExtractor.getCard())
        }
    }

    abstract fun getFirstDeal(): List<Card>

    companion object {
        private const val FIRST_CARD_COUNT = 2
        const val BLACK_JACK_NUM = 21
    }
}
