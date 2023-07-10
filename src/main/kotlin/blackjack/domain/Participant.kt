package blackjack.domain

abstract class Participant(val name: String) {
    val cards = Cards()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getInitialCards(): List<Card> {
        if (this is Dealer) {
            return listOf(cards.cards.first())
        }
        return cards.cards
    }

    fun isWin(participant: Participant): Boolean {
        if (cards.isBust()) {
            return false
        }
        return cards.calculateScore() > participant.cards.calculateScore()
    }

    fun isLose(participant: Participant): Boolean {
        if (cards.isBust()) {
            return true
        }
        return cards.calculateScore() < participant.cards.calculateScore()
    }

    fun calculateResult(participant: Participant): GameResult {
        return when {
            isWin(participant) -> GameResult.WIN
            isLose(participant) -> GameResult.LOSE
            else -> GameResult.DRAW
        }
    }

    abstract fun canDrawMoreCard(): Boolean
}
