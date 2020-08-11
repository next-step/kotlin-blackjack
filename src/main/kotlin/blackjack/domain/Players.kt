package blackjack.domain

private const val FIRST_PICK_COUNT = 2

data class Players(val players: List<Player>) {
    var currentPlayer = players[0]
        private set

    fun allPlayersReceivedCards(cardDeck: CardDeck) {
        repeat(FIRST_PICK_COUNT) { players.forEach { it.addCard(cardDeck.pickCard()) } }
    }

    fun currentPlayerPickCard(isHit: Boolean, cardDeck: CardDeck) {
        if (isHit) currentPlayer.addCard(cardDeck.pickCard()) else currentPlayer.isHit = isHit
    }

    fun getNextPlayer(): Player? {
        currentPlayer = players.drop(players.indexOf(currentPlayer) + 1).plus(players).find { it.isHit }!!
        return currentPlayer
    }
}
