package blackjack.domain

import blackjack.view.REPLY_HIT

data class Player(
    private val name: String,
    private val cards: Cards = Cards(emptySet())
) : Participant {

    var matchResult: String = TEXT_LOSE
        private set

    fun chooseToDraw(reply: String, dealer: Dealer): Player? {
        return drawIf(dealer) {
            REPLY_HIT == reply &&
                !hasMoreScoreThanMax(totalScore())
        } as Player?
    }

    override fun draw(newCard: Card?): Cards? {
        newCard ?: return null
        return Cards(cards.add(newCard))
    }

    override fun amountOfCards(): Int = cards.size()

    override fun totalScore(): Int = cards.sumOfScores()

    override fun stateOfCards(): String = cards.toString()

    fun win() {
        matchResult = TEXT_WIN
    }

    fun isWin() = matchResult == TEXT_WIN

    override fun toString(): String = name

    companion object {
        private const val TEXT_WIN = "승"
        private const val TEXT_LOSE = "패"
    }
}
