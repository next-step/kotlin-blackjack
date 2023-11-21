package blackjack.model.card

class CardDeck(var deck: MutableList<Card> = mutableListOf()) {

    fun pop(): Card {
        check(deck.isNotEmpty()) { NOT_EXIST_CARD_EXCEPTION }
        return deck.removeFirst()
    }

    fun add(card: Card) {
        check(card !in deck) { "$DUPLICATE_CARD_EXCEPTION - ${card.number}${card.suit}" }
        deck.add(card)
    }

    fun isBust(): Boolean {
        return calculateScore() > BLACKJACK_NUMBER
    }

    fun isStay(): Boolean {
        return calculateScore() <= BLACKJACK_NUMBER
    }

    fun isBlackJack(): Boolean {
        return calculateScore() == BLACKJACK_NUMBER && deck.size == BLACKJACK_SIZE
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

    private fun isOverThenBlackJackNumber(score: Int) = score > BLACKJACK_NUMBER

    companion object {
        private const val NOT_EXIST_CARD_EXCEPTION = "더 이상 카드가 존재하지 않습니다."
        private const val DUPLICATE_CARD_EXCEPTION = "중복된 카드는 추가할 수 없습니다."
        private const val BLACKJACK_NUMBER = 21
        private const val BLACKJACK_SIZE = 2
    }
}
