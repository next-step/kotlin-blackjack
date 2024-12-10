package blackjack.step2.domain

class GameInitializer(private val cardPicker: CardPicker) {
    fun initializeDealer(): Dealer {
        val cards = List(INITIAL_DEAL_COUNT) { cardPicker.pick() }
        return Dealer(cards = Cards.of(cards))
    }

    fun initializePlayers(names: List<String>): List<Player> {
        return names.map { name ->
            val cards = List(INITIAL_DEAL_COUNT) { cardPicker.pick() }
            Player(name, Cards.of(cards))
        }
    }

    companion object {
        private const val INITIAL_DEAL_COUNT = 2
    }
}
