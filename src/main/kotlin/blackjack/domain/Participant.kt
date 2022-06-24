package blackjack.domain

sealed class Participant(
    val name: String = "",
    cards: List<Card> = emptyList(),
) {
    private val _cards = cards.toMutableList()
    val cards: List<Card> get() = _cards.toList()

    fun addCard(newCards: List<Card>) = _cards.addAll(newCards)
    fun addCard(newCards: Card) = _cards.add(newCards)

    fun result(participant: Participant): Result {
        val anotherScore = participant.score()
        val ownScore = score()

        return when {
            ownScore.isBust && this is Dealer -> Result.LOSE
            ownScore.isBust && this is Player -> Result.WIN
            ownScore.isGreater(anotherScore) -> Result.WIN
            ownScore == anotherScore -> Result.DRAW
            else -> Result.LOSE
        }
    }

    fun score(): Score {
        val denominations = Denominations(cards.filter { it.denomination.isSingleScore }.map { it.denomination })
        val total: Score = denominations.sumScore
        val hasAce = cards.firstOrNull() { it.denomination == Denomination.ACE } != null

        return if (hasAce) {
            total + Score.aceScore(total)
        } else total
    }

    abstract fun canReceive(): Boolean
}
