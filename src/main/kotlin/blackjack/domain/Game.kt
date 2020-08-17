package blackjack.domain

class Game(_players: List<Player>, val dealer: Dealer) {
    val players = Players(_players)
    private var turn = 0

    init {
        dealer.setUpWithCards(players)
    }

    constructor(PlayerNames: String, dealer: Dealer = Dealer(Deck())) : this(
        PlayerNames.split(PLAYER_NAMES_DELIMITER)
            .filterNot { it.isBlank() }
            .map { Player(it.trim()) },
        dealer
    )

    fun giveChanceToDrawing(reply: String): Player? {
        currentPlayer().apply {
            val state: PlayerState =
                getState(wantToDraw = reply, score = totalScore(), count = countOfCards())

            if (!isHit(state)) {
                goToNextTurn()
                return this
            }

            if (isHit(state)) {
                draw(dealer.pickCard()) ?: return null
            }
            return this
        }
    }

    private fun goToNextTurn() {
        turn++
    }

    fun playOfDealer(): Dealer? {
        dealer.apply {
            if (hasScoreBelow17(totalScore())) {
                draw(pickCard()) ?: return null
            }
            return this
        }
    }

    fun getResult(): Pair<Dealer, Players> {
        dealer.checkWin(players)
        return Pair(dealer, players)
    }

    fun currentPlayer() = players.findPlayer(turn)

    fun isOver() = turn == players.size()

    companion object {
        private const val PLAYER_NAMES_DELIMITER = ","
        const val MAXIMUM_GAME_SCORE = 21
        const val MAXIMUM_SCORE_FOR_DEALER_DRAWING = 17
        const val DEFAULT_CARD_AMOUNT = 2
    }
}
