package blackjack.domain.card

data class Deck(
    private val cards: MutableList<Card> = mutableListOf()
) {
    fun add(card: Card) {
        val score = sumScore(cards + card)
        validateScore(score)
        cards.add(card)
    }

    private fun sumScore(cards: List<Card>): Int {
        var score = 0
        for (card in cards) {
            score += card.getScore(score)
        }
        return score
    }

    private fun validateScore(score: Int) {
        require(score <= Denomination.WINNING_NUMBER) {
            "카드 숫자 합이 21을 초과할 수 없습니다."
        }
    }

    fun getScore() = sumScore(cards.toMutableList().sortedByDescending { it.getScore() })

    fun contains(card: Card) = cards.contains(card)

    fun getCards() = cards.toList()
}
