package blackjack.model

class BlackJackGame(val players: List<Gamer>) {

    init {
        players.map {
            it.requestCard(Card.pop())
            it.requestCard(Card.pop())
        }
    }
}
