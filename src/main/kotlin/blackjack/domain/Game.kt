package blackjack.domain

class Game(
    val players: Players,
    val deck: Deck,
    val dealer: Dealer = Dealer(),
) {
    val arePlayersDone: Boolean
        get() = players.isDone
    val currentPlayer: Player
        get() = players.currentPlayer

    fun initialDeal() {
        repeat(INITIAL_ROUNDS) {
            players.dealRoundOfCardsFrom(deck)
            dealer.drawFrom(deck)
        }
        if (dealer.isBlackjack) {
            players.dealerDealtBlackjack()
        }
    }

    fun playerHits() {
        players.hit(deck)
    }

    fun playerStands() {
        players.stand()
    }

    fun dealerTurn() {
        checkPlayersAreDone()

        dealer.flipHoleCardUp()

        if (!isDealerActionNecessary()) {
            return
        }
        dealerAction()
    }

    private fun dealerAction() {
        while (dealer.mustDrawCard()) {
            dealer.drawFrom(deck)
        }
    }

    private fun isDealerActionNecessary() = players.isOutcomeUnknown

    private fun checkPlayersAreDone() {
        check(arePlayersDone) { "플레이어들의 턴이 종료되어야 합니다." }
    }

    companion object {
        private const val INITIAL_ROUNDS = 2
    }
}
