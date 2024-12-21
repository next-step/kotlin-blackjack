package blackjack.domain

class Players(
    val roster: List<Player>,
) {
    val iterator: Iterator<Player>
    var currentPlayer: Player
        private set
    val isDone: Boolean get() = roster.all { it.isDone }
    val isOutcomeUnknown: Boolean get() = roster.any { it.state is Stand }

    init {
        requireNamesIsNotEmpty()
        requireNamesAreDistinct()
        iterator = roster.iterator()
        currentPlayer = iterator.next()
    }

    operator fun get(index: Int): Player = roster[index]

    fun dealRoundOfCardsFrom(deck: Deck) {
        roster.forEach { it.initialDrawFrom(deck) }
        skipToNextPlayerIfDone()
    }

    fun hit(deck: Deck) {
        checkIsNotDone()
        currentPlayer.hit(deck)
        skipToNextPlayerIfDone()
    }

    fun stand() {
        checkIsNotDone()
        currentPlayer.stand()
        skipToNextPlayerIfDone()
    }

    fun placeBets(bets: List<Bet>) {
        requireBetsSizeEqualsRosterSize(bets)
        roster
            .zip(bets)
            .forEach { (player, bet) -> player.placeBet(bet) }
    }

    fun dealerDealtBlackjack() {
        roster.forEach(Player::dealerDealtBlackjack)
    }

    fun results(dealer: Dealer): List<PlayerResult> {
        checkIsDone()
        return roster.map { it.result(dealer) }
    }

    private fun requireBetsSizeEqualsRosterSize(bets: List<Bet>) {
        require(roster.size == bets.size) { "플레이어 수와 베팅 수가 일치하지 않습니다." }
    }

    private fun checkIsDone() {
        check(isDone) { "게임이 종료되지 않았습니다." }
    }

    private fun checkIsNotDone() {
        check(!isDone) { "모든 플레이어가 종료했습니다." }
    }

    private fun requireNamesIsNotEmpty() {
        require(roster.isNotEmpty()) { "플레이어 목록이 비어 있습니다" }
    }

    private fun requireNamesAreDistinct() {
        val names = roster.map { it.name }
        require(names.distinct().size == roster.size) {
            "중복된 이름이 있습니다."
        }
    }

    private fun skipToNextPlayerIfDone() {
        while (currentPlayer.isDone && iterator.hasNext()) {
            currentPlayer = iterator.next()
        }
    }

    companion object {
        fun from(names: List<String>): Players = Players(names.map { Player(it) })

        fun from(vararg names: String): Players = from(names.toList())
    }
}
