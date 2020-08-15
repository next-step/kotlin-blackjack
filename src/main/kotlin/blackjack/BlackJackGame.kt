package blackjack

class BlackJackGame(val dealer: Gamer, val players: List<Gamer>) {

    fun initCardForDealer() {
        repeat(Constant.FIRST_HAVE_NUMBER_OF_CARD) {
            dealer.requestCard(Deck.pop())
        }
    }

    fun initCardForPlayers() {
        repeat(Constant.FIRST_HAVE_NUMBER_OF_CARD) {
            players.map { it.requestCard(Deck.pop()) }
        }
    }

    fun getDealerPoint(): Point = dealer.calculatePoint()

    fun getPlayersPoint(): List<Point> = players.map { it.calculatePoint() }
}
