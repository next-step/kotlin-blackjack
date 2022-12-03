package model

private const val BLACKJACK_MAX_SCORE = 21

private const val ACE_SPECIAL_MINUS_SCORE = 10

class PokerScore(val cards: Cards) {
    fun sum(): Int {

        val aceCard = cards.cards.find { it.pokerNumber == PokerNumberFinder("A").pokerNumber } != null
        val score = cards.cards.sumOf { pokerNumberScore(it.pokerNumber) }
        return specialAceRule(aceCard, score)
    }

    private fun specialAceRule(aceCard: Boolean, score: Int): Int {
        if (aceCard && score > BLACKJACK_MAX_SCORE) {
            return score - ACE_SPECIAL_MINUS_SCORE
        }
        return score
    }

    private fun pokerNumberScore(pokerNumber: PokerNumber): Int =
        when (pokerNumber) {
            PokerNumberFinder("A").pokerNumber -> 11
            PokerNumberFinder("2").pokerNumber -> 2
            PokerNumberFinder("3").pokerNumber -> 3
            PokerNumberFinder("4").pokerNumber -> 4
            PokerNumberFinder("5").pokerNumber -> 5
            PokerNumberFinder("6").pokerNumber -> 6
            PokerNumberFinder("7").pokerNumber -> 7
            PokerNumberFinder("8").pokerNumber -> 8
            PokerNumberFinder("9").pokerNumber -> 9
            PokerNumberFinder("10").pokerNumber -> ACE_SPECIAL_MINUS_SCORE
            PokerNumberFinder("Jack").pokerNumber -> ACE_SPECIAL_MINUS_SCORE
            PokerNumberFinder("Queen").pokerNumber -> ACE_SPECIAL_MINUS_SCORE
            PokerNumberFinder("King").pokerNumber -> ACE_SPECIAL_MINUS_SCORE
            else -> throw IllegalArgumentException("해당 번호를 찾을수 없습니다.")
        }
}
