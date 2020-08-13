package blackJack.domain

class BlackJack(names: List<String>) {
    val players = makePlayer(names)
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
        players.forEach { giveCard(it) }
        giveCard(dealer)
    }

    fun giveCard(people: People) {
        dealer.giveCard(people)
    }

    fun getResult(): Result = Result(dealer)

    companion object {
        private const val START_HANDS = 2
    }
}
