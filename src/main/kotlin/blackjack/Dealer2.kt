package blackjack

class Dealer2(
    initialCards: List<Card>,
    private val blackjackGameJudgeStrategy: BlackjackGameJudgeStrategy = DefaultBlackjackGameJudgeStrategy(),
) : Participant2(DEALER_NAME, initialCards) {
    override fun hit(card: Card) {
        check(shouldHit()) {
            "현재 딜러는 히트할 수 없는 상태입니다: sumOfCards=${sumOfCards()}"
        }
        super.hit(card)
    }

    fun shouldHit(): Boolean = sumOfCards() < UNDER_OVER

    fun evaluatePlayerResult(player: Player2): PlayerResult = blackjackGameJudgeStrategy.evaluatePlayerResult(this.state, player.state)

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val UNDER_OVER = 17
    }
}
