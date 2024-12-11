package blackjack.domain

class Game(
    private val deck: Deck,
    private val participants: GameParticipants,
) {
    companion object {
        private const val BUST_THRESHOLD = 21
        private const val BLACKJACK_MULTIPLIER = 1.5
    }

    fun start() {
        participants.distributeInitialCards(deck)
    }

    fun hit(playerName: String) {
        participants.players.hit(playerName, deck)
    }

    fun dealerPlayTurn() {
        participants.dealer.playTurn(deck)
    }

    fun calculateFinalResults(): Map<String, Int> {
        val dealerBlackjack = participants.dealer.isBlackjack()
        val dealerScore = participants.dealer.getTotalValue()

        val playerResults =
            participants.players.getPlayers()
                .associate { player ->
                    player.name to calculatePlayerResult(player, dealerScore, dealerBlackjack)
                }

        val dealerProfit = playerResults.values.sum() * -1
        return playerResults + ("Dealer" to dealerProfit)
    }

    private fun calculatePlayerResult(
        player: Player,
        dealerScore: Int,
        dealerBlackjack: Boolean,
    ): Int {
        val playerBlackjack = player.isBlackjack()
        return when {
            player.getTotalValue() > BUST_THRESHOLD -> -player.getBettingAmount()
            dealerScore > BUST_THRESHOLD -> player.getBettingAmount()
            dealerBlackjack && playerBlackjack -> 0
            playerBlackjack -> (player.getBettingAmount() * BLACKJACK_MULTIPLIER).toInt()
            player.getTotalValue() > dealerScore -> player.getBettingAmount()
            else -> -player.getBettingAmount()
        }
    }
}
