package blackjack.domain

class Game(private val dealer: Dealer, private val players: Players) {
    fun start() {
        dealer.deal(players)
    }
}
