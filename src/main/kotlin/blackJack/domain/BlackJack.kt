package blackJack.domain

class BlackJack(names: List<String>) {
    val players = Players(names)
    val dealer = Dealer()

    init {
        dealer.shuffleDeck(Deck.DEFAULT_DECK)
        readyGame()
    }

    private fun readyGame() {
        repeat(START_HANDS) { giveCardAllPeople() }
    }

    private fun giveCardAllPeople() {
        players.giveCardAll(dealer)
        dealer.giveCard(dealer)
    }

    fun getResult(): Result = Result(dealer, players)

    fun playersForEach(function: (player: Player) -> Unit) {
        players.forEach { function(it) }
    }

    fun playerGetCard(player: Player, inputValue: String): Boolean? {
        if (inputValue == "y") {
            dealer.giveCard(player)
            return true
        }
        if (inputValue == "n") {
            return false
        }
        return null
    }

    companion object {
        private const val START_HANDS = 2
    }
}
