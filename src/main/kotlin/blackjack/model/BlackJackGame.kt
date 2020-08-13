package blackjack.model

class BlackJackGame(val dealer: Gamer, val players: List<Gamer>) {

    fun initDealer() {
        dealer.run {
            requestCard(Card.pop())
            requestCard(Card.pop())
        }
    }

    fun initPlayers() {
        players.map {
            it.requestCard(Card.pop())
            it.requestCard(Card.pop())
        }
    }

    fun getDealerPoint(): Point = dealer.calculatePoint()

    fun getPlayersPoint(): List<Point> = players.map { it.calculatePoint() }
}
