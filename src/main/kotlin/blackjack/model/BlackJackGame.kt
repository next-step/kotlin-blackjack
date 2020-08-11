package blackjack.model

class BlackJackGame(val players: List<Player>) {

    init {
        players.map {
            it.requestCard(Card.pop())
            it.requestCard(Card.pop())
        }
    }
}
