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


    fun getResults(): GameResult = GameResult(players, dealer)

    companion object {
        private const val INITIAL_CARDS = 2
    }
}
