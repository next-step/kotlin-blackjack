package blackjack.domain

object Dealer : Participant() {
    override val name: String = "딜러"
    override val cards: Cards = Cards()

    fun shouldReceiveCard(): Boolean {
        return cards.calculateScore() <= Game.DEALER_RECEIVE_CARD_SCORE
    }

    fun getResult(playerResults: List<PlayerResult>): Map<PlayerResult, Int> {
        val countPerPlayerResult = playerResults.groupingBy { it }
            .eachCount()

        return mapOf(
            PlayerResult.WIN to (countPerPlayerResult[PlayerResult.LOSE] ?: 0),
            PlayerResult.DRAW to (countPerPlayerResult[PlayerResult.DRAW] ?: 0),
            PlayerResult.LOSE to (countPerPlayerResult[PlayerResult.WIN] ?: 0),
        )
    }
}
