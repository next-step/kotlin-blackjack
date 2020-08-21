package blackjack.domain

const val FIRST_PICK_COUNT = 2

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
        val list = players.drop(players.indexOf(currentPlayer) + 1)
            .plus(players.filterNot { it is Dealer }).filterNot { it.isFinished() }
        if (list.isEmpty() && dealer.isHit) {
            currentPlayer = dealer
            return currentPlayer
        }
        val player = list.firstOrNull()
        if (player != null) currentPlayer = player
        return player
    }

    fun calculateResult(dealerPoint: Int = dealer.point) {
        val onlyPlayers = players.filterNot { it == dealer }
        onlyPlayers.forEach { it.calculateResult(dealerPoint) }
    }

    companion object {
        fun from(playerNames: String): Players {
            require(PLAYER_REGULAR_EXPRESSION.matches(playerNames)) { "플레이어명은 영문과 구분자 ',' 로만 입력해주세요." }
            val players = playerNames.split(SPLIT_CHARACTER).map { Player(it) }.toMutableList()
            players.add(0, Dealer())
            return Players(players)
        }
    }
}
