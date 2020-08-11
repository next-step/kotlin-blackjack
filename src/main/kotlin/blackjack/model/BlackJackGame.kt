package blackjack.model

class BlackJackGame(val dealer: Gamer, val players: List<Gamer>) {

    init {
        dealer.run {
            requestCard(Card.pop())
            requestCard(Card.pop())
        }
        players.map {
            it.requestCard(Card.pop())
            it.requestCard(Card.pop())
        }
    }
}
