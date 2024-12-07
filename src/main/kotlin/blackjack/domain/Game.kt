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
            dealer.initialDrawFrom(deck)
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
        check(arePlayersDone) { "플레이어들의 턴이 종료되어야 합니다." }
        dealer.flipHoleCardUp()
    }

    companion object {
        private const val INITIAL_ROUNDS = 2
    }
}
