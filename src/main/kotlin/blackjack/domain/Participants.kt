package blackjack.domain

data class Participants(
    private val dealer: Dealer,
    val players: List<Player>
) {
    fun onPreparePlay(action: (Dealer, Player) -> Unit): Participants {
        players.forEach { action(dealer, it) }
        return this
    }

    fun onStartPlay(action: (Dealer, Player) -> Unit): Participants {
        players.forEach { action(dealer, it) }
        return this
    }

    fun onEndPlay(action: (Dealer, Player) -> Unit): Participants {
        players.forEach { action(dealer, it) }
        return this
    }
}
