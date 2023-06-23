package blackjack.domain

class Dealer(private val deck: Deck = Deck()) {
    fun initializeRound(players: Array<Player>) {
        for (player in players) {
            val firstRoundCards = arrayOf(deck.drawCard(), deck.drawCard())
            player.hit(*firstRoundCards)
        }
    }

    fun draw(): PokerCard {
        return deck.drawCard()
    }
}
