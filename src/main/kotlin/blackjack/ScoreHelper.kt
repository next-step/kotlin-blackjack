package blackjack

object ScoreHelper {

    private const val TWENTY_ONE = 21
    private const val ACE_BONUS_SCORE = 11

    fun sum(cards: List<Card>): Int = cards.asSequence()
        .sortedByDescending { it.denomination.defaultScore }
        .map { it.denomination }
        .fold(0) { acc, denomination ->
            if (denomination.isAce() && acc + ACE_BONUS_SCORE <= TWENTY_ONE) {
                acc + ACE_BONUS_SCORE
            } else {
                acc + denomination.defaultScore
            }
        }
}
