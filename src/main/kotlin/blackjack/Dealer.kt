package blackjack

class Dealer(
    initialCards: List<Card>,
    private val blackjackGameJudgeStrategy: BlackjackGameJudgeStrategy = DefaultBlackjackGameJudgeStrategy(),
    private val blackjackPlayerProfitCalculatorHandler: BlackjackPlayerProfitCalculatorHandler = BlackjackPlayerProfitCalculatorHandler(),
) : Participant(DEALER_NAME, initialCards) {
    override fun hit(card: Card) {
        check(shouldHit()) {
            "현재 딜러는 히트할 수 없는 상태입니다: sumOfCards=${sumOfCards()}"
        }
        super.hit(card)
    }

    fun shouldHit(): Boolean = sumOfCards() < UNDER_OVER

    fun evaluatePlayerResult(player: Player): PlayerResult = blackjackGameJudgeStrategy.evaluatePlayerResult(this.state, player.state)

    fun calculatePlayerProfit(player: Player): Money {
        val playerResult = evaluatePlayerResult(player)
        return blackjackPlayerProfitCalculatorHandler.findCalculator(playerResult).calculateProfit(player.state, player.betAmount)
    }

    fun calculateSelfProfit(players: List<Player>): Money = -players.map { this.calculatePlayerProfit(it) }.sum()

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val UNDER_OVER = 17
    }
}
