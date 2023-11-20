package blackjack.domain

class Game(
    private val players: List<Player>,
    private val dealer: Dealer,
    private val observer: GameObserver
) {

    private val participants = mutableListOf<Participant>().apply {
        add(dealer)
        addAll(players)
    }

    init {
        require(players.isNotEmpty()) { "플레이어는 최소 한 명 이상이어야 합니다." }
    }

    fun startGame() {
        participants.forEach { initialDeal(it) }
        observer.onGameStarted(participants)
    }

    private fun initialDeal(participant: Participant) = repeat(INITIAL_CARDS) {
        participant.receiveCard(dealer.dealCard())
    }

    fun playerTurn(shouldHit: (Player) -> Boolean) {
        players.forEach { player ->
            while (shouldHit(player)) {
                player.receiveCard(dealer.dealCard())
                if (player.calculateScore() <= PLAYER_MAXIMUM_SCORE) {
                    break
                }

                observer.onPlayerHits(player)
            }
        }
    }

    fun dealerTurn() {
        if (dealer.calculateScore() <= DEALER_MINIMUM_SCORE) {
            dealer.receiveCard(dealer.dealCard())
            observer.onDealerHits(dealer)
        }
        observer.onGameEnded(participants)
    }

    fun getResults(): GameResult = GameResult(players, dealer)

    companion object {
        private const val PLAYER_MAXIMUM_SCORE = 21
        private const val DEALER_MINIMUM_SCORE = 16

        private const val INITIAL_CARDS = 2
    }
}
