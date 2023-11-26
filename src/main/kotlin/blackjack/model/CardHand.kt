package blackjack.model

class CardHand(cardDispenser: CardDispenser) {
    var cards: List<Card> = cardDispenser.getCards(FIRST_COUNT)
        private set
    val totalScore: Int
        get() = getTotalHandScore()

    fun addCard(card: Card) {
        val newCards = cards.toMutableList()
        newCards.add(card)
        cards = newCards.toList()
    }

    private fun getTotalHandScore(): Int {
        var (result, aceCount) = getTotalHandScoreAndAceCountBeforeAceScoreCheck()

        /*
        * 결과에 맞춰서 ACE 점수 조정
        * */
        while (result > BLACKJACK && aceCount > 0) {
            result -= 10
            aceCount -= 1
        }

        return result
    }

    private fun getTotalHandScoreAndAceCountBeforeAceScoreCheck(): Pair<Int, Int> {
        var result = 0
        var aceCount = 0

        for (card in cards) {
            when (card.cardValue.value) {
                "A" -> {
                    aceCount += 1
                    result += CardValue.ACE_HIGH_SCORE
                }
                "K", "Q", "J" -> result += CardValue.JQA_SCORE
                else -> result += card.cardValue.value.toInt()
            }
        }

        return result to aceCount
    }

    private fun getCardBust(): Boolean = getTotalHandScore() > BLACKJACK

    companion object {
        const val BLACKJACK = 21
        const val FIRST_COUNT = 2
    }
}
