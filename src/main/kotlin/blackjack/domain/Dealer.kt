package blackjack.domain

class Dealer(val deck: Deck) {
    fun startRound(players: Array<Player>) {
        for (player in players) {
            require(deck.canDrawFromDeck(DOUBLE_DRAW)) { EMPTY_DECK_ERROR }
            val firstRoundCards = listOf(deck.drawCard(), deck.drawCard())
            player.hands.addAll(firstRoundCards)
        }
    }

    fun hit(): PokerCard {
        require(deck.canDrawFromDeck(SINGLE_DRAW)) { EMPTY_DECK_ERROR }
        return deck.drawCard()
    }

    companion object {
        private const val EMPTY_DECK_ERROR = "모든 덱이 소진되었습니다."
        private const val SINGLE_DRAW = 1
        private const val DOUBLE_DRAW = 2

    }
}
