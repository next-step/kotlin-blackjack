package blackjack.domain

class Player(val name: String) {
    var deck = Cards()

    fun start(): Player {
        deck = CardDeck.start()
        return this
    }
}
