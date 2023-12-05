package blackjack.participant

import blackjack.ScoreCalculator
import blackjack.card.BlackJackCard

class Player(
    val name: Name,
    val bettingAmount: BettingAmount = BettingAmount(0),
    scoreCalculator: ScoreCalculator = ScoreCalculator()
) {
    constructor(name: Name, scoreCalculator: ScoreCalculator): this(name, BettingAmount(0), scoreCalculator)
    constructor(name: Name): this(name, BettingAmount(0), ScoreCalculator())

    private val blackjackStrategy: BlackjackStrategy = BlackjackStrategy(scoreCalculator)

    val cards get() = blackjackStrategy.cards

    val isBust get() = blackjackStrategy.isBust

    fun drawCard(cards: List<BlackJackCard>) {
        blackjackStrategy.drawCard(cards)
    }

    fun resultScore(): Int {
        return blackjackStrategy.resultScore()
    }

    fun shouldDraw(): Boolean {
        return !blackjackStrategy.isBust
    }
}
