package blackjack.domain

private const val FIRST_PICK_COUNT = 2

data class Players(val players: List<Player>) {
    val dealer: Dealer = players[0] as Dealer
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
        val dealerPoint: Int = dealer.point
        val onlyPlayers = players.filterNot { it == dealer }.asSequence()
        if (dealer.isBusted()) {
            onlyPlayers.forEach { it.playResult = PlayResultType.WIN }
        } else {
            onlyPlayers.forEach { it.getResult(dealerPoint) }
        }

        dealer.dealerResult.setStatic(
            onlyPlayers.filter { it.playResult == PlayResultType.LOSE }.count(),
            onlyPlayers.filter { it.playResult == PlayResultType.WIN }.count()
        )
    }

    companion object {
        fun newInstance(playerNames: String): Players? {
            if(!PLAYER_REGULAR_EXPRESSION.matches(playerNames)) return null
            val players = playerNames.split(SPLIT_CHARACTER).map { Player(it) }.toMutableList()
            players.add(0, Dealer(playerCount = players.size))
            return Players(players)
        }
    }
}
