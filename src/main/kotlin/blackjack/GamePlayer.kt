package blackjack

data class GamePlayer(
    val name: String,
    val cards: List<Card> = emptyList(),
    private val action: PlayerAction,
    val isBurst: Boolean = false
) {
    fun isContinueDeal() = action != PlayerAction.STAND

    fun receiveCard(card: Card): GamePlayer {
        val updatedCards = mutableListOf(card).apply { this.addAll(cards) }
        return GamePlayer(
            name = this.name,
            cards = updatedCards,
            action = PlayerAction.HIT,
            isBurst = isBurst(updatedCards)
        )
    }

    private fun isBurst(cards: List<Card>): Boolean =
        cards.sumOf { it.number.value } > GameBlackjack.BLACKJACK_MAX_SCORE
}
