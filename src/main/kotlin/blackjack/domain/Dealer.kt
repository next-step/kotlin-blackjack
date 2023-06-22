package blackjack.domain

class Dealer(val deck: Deck) {
    fun startRound(players: Array<Player>) {
        for (player in players) {
            val firstRoundCards = listOf(deck.drawCard(), deck.drawCard())
            player.hands.addAll(firstRoundCards)
        }
    }
}
