package blackjack.domain

object DealMachine {
    const val INITIAL_DEAL_COUNT = 2

    fun initialDeal(players: Players) {
        players.players.forEach(this::initialDeal)
    }

    private fun initialDeal(player: Player) {
        (1..INITIAL_DEAL_COUNT).forEach { _ ->
            deal(player)
        }
    }

    fun deal(dealAnswer: DealAnswer, player: Player) {
        if(dealAnswer == DealAnswer.NO) return
        deal(player)
    }

    private fun deal(player: Player) {
        val card = Deck.pick()
        player.addPlayerCard(card)
    }
}