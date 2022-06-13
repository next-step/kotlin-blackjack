package blackjack.domain

import kotlin.math.roundToInt

class GameResult(
    private val dealer: Dealer,
    players: List<Participant>
) {
    val allParticipant = listOf(dealer) + players

    fun matchParticipantsIsBlackJack(participant: Participant) {
        when {
            participant.isBlackJack && dealer.isBlackJack -> {
                participant.appendEarnAmount(participant.battingAmount)
            }
            participant.isBlackJack && !dealer.isBlackJack -> {
                participant.appendEarnAmount((participant.battingAmount.toDouble() * 1.5).roundToInt())
                dealer.appendEarnAmount(-(participant.battingAmount.toDouble() * 0.5).roundToInt())
            }
        }
    }

    fun matchWithPlayer(participant: Participant) {
        when {
            participant.playerCards.isBust() -> playerIsBust(participant)
            dealer.playerCards.isBust() -> dealerIsBust(participant)
            else -> decideWinner(participant)
        }
    }

    fun playerIsBust(participant: Participant) {
        participant.appendEarnAmount(-participant.battingAmount)
        dealer.appendEarnAmount(participant.battingAmount)
    }

    fun dealerIsBust(participant: Participant) {
        participant.appendEarnAmount(participant.battingAmount)
        dealer.appendEarnAmount(-participant.battingAmount)
    }

    fun decideWinner(player: Participant) {
        when {
            player.playerCards.score() < dealer.playerCards.score() -> {
                player.appendEarnAmount(-player.battingAmount)
                dealer.appendEarnAmount(player.battingAmount)
            }
            player.playerCards.score() == dealer.playerCards.score() -> {
                player.appendEarnAmount(player.battingAmount)
            }
            else -> {
                player.appendEarnAmount(player.battingAmount)
                dealer.appendEarnAmount(-player.battingAmount)
            }
        }
    }
}
