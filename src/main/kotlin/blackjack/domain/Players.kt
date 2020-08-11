package blackjack.domain

private const val FIRST_PICK_COUNT = 2

data class Players(val players: List<Player>) {
    var currentPlayer = players[1]
        private set

    fun allPlayersReceivedCards(cardDeck: CardDeck) {
        repeat(FIRST_PICK_COUNT) { players.forEach { it.addCard(cardDeck.pickCard()) } }
    }

    fun currentPlayerPickCard(isHit: Boolean, cardDeck: CardDeck) {
        if (isHit) currentPlayer.addCard(cardDeck.pickCard()) else currentPlayer.isHit = isHit
    }

    fun getNextPlayer(): Player? {
        if (players.isEmpty()) return null
        return try {
            currentPlayer = players.drop(players.indexOf(currentPlayer) + 1).plus(players).find { it.isHit }!!
            currentPlayer
        } catch (e: Exception) {
            null
        }
    }

    fun calculateResult() {
        val dealer: Player = players.find { it is Dealer } ?: return
        val dealerPoint: Int = dealer.point
        val notLosingPlayers =
            players.filterNot { it is Dealer }.filterNot { it.playResult == PlayResultType.LOSE }.asSequence()
        val dealerLoseCount =
            if (dealer.isBusted) {
                notLosingPlayers.count()
            } else {
                notLosingPlayers
                    .filter { it.point > dealerPoint }
                    .map { it.playResult = PlayResultType.WIN }.count()
            }
        println(dealerLoseCount)
    }
}
