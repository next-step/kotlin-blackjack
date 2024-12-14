package blackjack.step2.domain

class GameInitializer(private val cardPicker: CardPicker) {
    fun initializeDealer(): Dealer {
        val cards = List(INITIAL_DEAL_COUNT) { cardPicker.pick() }
        return Dealer(cards = Cards.of(cards))
    }

    fun initializePlayers(players: List<Player>): List<Player> {
        return players.map { player ->
            val cards = List(INITIAL_DEAL_COUNT) { cardPicker.pick() }
            Player(player.name, Cards.of(cards))
        }
    }

    companion object {
        const val INITIAL_DEAL_COUNT = 2
    }
}
