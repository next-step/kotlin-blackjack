package blackjack.model

class ParticipantResults(
    val playerResults: Set<Pair<Player, Result>>,
    val dealerResult: Pair<Dealer, Result>,
) {
    fun dealerResult(): Result {
        return dealerResult.second
    }

    fun findPlayerResult(player: Player): Result {
        TODO()
    }
}
