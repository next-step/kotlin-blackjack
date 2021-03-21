package blackjack.domain

class DealMachine(
    private val deck: Deck
) {

    fun initialDeal(players: Players) {
        players.players.forEach(this::initialDeal)
    }

    private fun initialDeal(player: Player) {
        repeat(INITIAL_DEAL_COUNT) {
            deal(player)
        }
    }

    fun deal(dealAnswer: DealAnswer, player: Player) {
        if (dealAnswer == DealAnswer.NO) return
        deal(player)
    }

    private fun deal(player: Player) {
        val card = deck.pick()
        player.addPlayerCard(card)
    }

    companion object {
        const val INITIAL_DEAL_COUNT = 2
    }
}
