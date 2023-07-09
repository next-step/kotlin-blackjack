package blackjack.domain

abstract class Participant(val name: String) {
    val cards = Cards()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun isBust(): Boolean {
        return cards.calculateScore() > Cards.BLACKJACK
    }

    fun isWin(participant: Participant): Boolean {
        if (isBust()) {
            return false
        }
        return cards.calculateScore() > participant.cards.calculateScore()
    }

    fun isLose(participant: Participant): Boolean {
        if (isBust()) {
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
