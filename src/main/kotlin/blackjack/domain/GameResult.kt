package blackjack.domain

class GameResult(
    private val dealer: Dealer,
    players: List<Participant>
) {
    val allParticipant = listOf(dealer) + players

    fun matchWithPlayer(participant: Participant) {
        when {
            participant.playerCards.isBust() -> playerIsBust(participant)
            dealer.playerCards.isBust() -> dealerIsBust(participant)
            else -> decideWinner(participant)
        }
    }

    fun playerIsBust(participant: Participant) {
        participant.gameScore.lose()
        dealer.gameScore.win()
    }

    fun dealerIsBust(participant: Participant) {
        participant.gameScore.win()
        dealer.gameScore.lose()
    }

    fun decideWinner(player: Participant) {
        when {
            player.playerCards.score() < dealer.playerCards.score() -> {
                player.gameScore.lose()
                dealer.gameScore.win()
            }
            player.playerCards.score() == dealer.playerCards.score() -> {
                player.gameScore.draw()
                dealer.gameScore.draw()
            }
            else -> {
                player.gameScore.win()
                dealer.gameScore.lose()
            }
        }
    }
}
