package blackjack.domain

class Game(val players: Players, val dealer: Dealer) {
    var isOver = false
        private set

    init {
        dealer.setUpWithCards()
        players.setUpWithCards(dealer.pickCardsForPlayers(players.size()))
    }

    constructor(PlayerNames: String, dealer: Dealer = Dealer(Deck())) : this(
        Players(
            PlayerNames.split(PLAYER_NAMES_DELIMITER)
                .filterNot { it.isBlank() }
                .map { Player(it.trim()) }
        ),
        dealer
    )

    fun getBetMoney(amount: Int) {
        players.setStake(amount)
    }

    fun giveChanceToDraw(reply: String) {
        isOver = players.chooseToDraw(reply, dealer.deck)
    }

    fun playOfDealer(): Dealer {
        dealer.apply {
            if (hasScoreBelow17(score()))
                draw(pickCard())

            return this
        }
    }

    fun checkWin() {
        dealer.checkWin(dealer.getStateBy(), players)
    }

    fun currentPlayer() = players.currentPlayer()

    fun resetTurn() {
        players.resetTurn()
    }

    fun canTurnOver() = players.turn == players.size()

    companion object {
        private const val PLAYER_NAMES_DELIMITER = ","
        const val BLACKJACK_SCORE = 21
        const val DEFAULT_CARD_COUNT = 2
    }
}
