package blackjack

class BlackJackGame(private val dealer: Gamer, private val players: List<Gamer>) {

    fun initCardForDealer() {
        repeat(FIRST_HAVE_NUMBER_OF_CARD) {
            dealer.requestCard(Deck.pop())
        }
    }

    fun initCardForPlayers() {
        repeat(FIRST_HAVE_NUMBER_OF_CARD) {
            players.map { it.requestCard(Deck.pop()) }
        }
    }

    companion object {
        private const val FIRST_HAVE_NUMBER_OF_CARD = 2
    }
}
