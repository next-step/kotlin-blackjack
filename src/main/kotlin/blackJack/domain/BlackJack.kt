package blackJack.domain

class BlackJack(names: List<String>) {
    val players = Players(makePlayer(names))
    val dealer = Dealer()

    init {
        dealer.shuffleDeck()
        readyGame()
    }

    private fun makePlayer(names: List<String>): List<Player> = names.map { Player(it) }

    private fun readyGame() {
        repeat(START_HANDS) { giveCardAllPeople() }
    }

    private fun giveCardAllPeople() {
        players.giveCardAll(dealer)
        dealer.giveCard(dealer)
    }

    fun getResult(): Result = Result(dealer)

    companion object {
        private const val START_HANDS = 2
    }
}
