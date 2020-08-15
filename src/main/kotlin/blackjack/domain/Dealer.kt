package blackjack.domain

class Dealer(
    private val deck: Deck = Deck(),
    private val cards: Cards = Cards(emptySet())
) : Participant {
    private val _matchResult = mutableListOf(0, 0)
    val matchResult: List<Int>
        get() = _matchResult.toList()

    fun pickCard(): Card? = deck.provideCard(deck.shuffled())

    override fun draw(newCard: Card?): Cards? {
        newCard ?: return null
        return Cards(cards.add(newCard))
    }

    fun hasLessScoreThan17(): Boolean = cards.isLessThan17()

    fun faceUpCard(): Card = cards.firstCard()

    fun checkWin(playersSize: Int, winCountOfPlayers: Int): List<Int> {
        _matchResult[0] = playersSize - winCountOfPlayers
        _matchResult[1] = winCountOfPlayers
        return matchResult
    }

    override fun amountOfCards(): Int = cards.size()

    override fun totalScore(): Int = cards.sumOfScores()

    override fun stateOfCards(): String = cards.toString()

    override fun toString(): String = DEALER_NAME

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
