package blackjack.domain

class Game(
    private val players: List<Player>,
    private val dealer: Dealer,
) {

    val participants: List<Participant> = listOf(dealer) + players

    init {
        require(players.isNotEmpty()) { "플레이어는 최소 한 명 이상이어야 합니다." }
    }

    fun startGame() {
        participants.forEach { initialDeal(it) }
    }

    private fun initialDeal(participant: Participant) = repeat(INITIAL_CARDS) {
        participant.receiveCard(dealer.dealCard())
    }

    fun dealerTurn(): Boolean {
        if (dealer.canDraw()) {
            dealer.receiveCard(dealer.dealCard())
            return true
        }

        return false
    }

    fun playerTurn(player: Player, decision: PlayerDecision): Boolean {
        if (player.canDraw() && decision == PlayerDecision.HIT) {
            player.receiveCard(dealer.dealCard())
            return true
        }

        return false
    }

    fun getResults(): GameResult = GameResult(players, dealer)

    companion object {
        private const val INITIAL_CARDS = 2
    }
}
