package blackjack.model.card

class CardDeck(var deck: MutableList<Card> = mutableListOf()) {

    constructor(
        cards: List<Card>,
        @Suppress("UNUSED_PARAMETER") dummy: Any? = null
    ) : this(deck = cards.toMutableList())

    fun get(): Card {
        check(deck.isNotEmpty()) { NOT_EXIST_CARD_EXCEPTION }
        return deck.removeFirst()
    }

    fun add(card: Card) {
        check(!deck.contains(card)) { DUPLICATE_CARD_EXCEPTION }
        deck.add(card)
    }

    fun isBust(): Boolean {
        return calculateScore() > BLACKJACK
    }

    fun isStay(): Boolean {
        return calculateScore() <= BLACKJACK
    }

    fun isBlackJack(): Boolean {
        return calculateScore() == BLACKJACK && deck.size == BLACKJACK_SIZE
    }

    fun calculateScore(): Int {
        var score = deck.sumOf { it.score }
        var numberOfAces = deck.count { it.number == CardNumber.ACE }

        while (numberOfAces > 0 && isOverThenBlackJackNumber(score)) {
            score -= 10
            numberOfAces--
        }

        return score
    }

    private fun isOverThenBlackJackNumber(score: Int) = score > BLACKJACK

    companion object {
        private const val NOT_EXIST_CARD_EXCEPTION = "더 이상 카드가 존재하지 않습니다."
        private const val DUPLICATE_CARD_EXCEPTION = "중복된 카드는 추가할 수 없습니다."
        private const val BLACKJACK = 21
        private const val BLACKJACK_SIZE = 2
    }
}
