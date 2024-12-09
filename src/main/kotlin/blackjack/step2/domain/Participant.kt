package blackjack.step2.domain

interface Participant {
    val name: String
    val cards: Cards

    fun pickCard(card: Card): Participant

    fun calculateScore(): Int {
        return ScoreCalculator.calculate(cards)
    }
}
