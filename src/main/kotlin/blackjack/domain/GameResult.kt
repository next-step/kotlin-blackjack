package blackjack.domain

import kotlin.math.roundToInt

class GameResult(
    private val dealer: Dealer,
    players: List<Player>
) {
    val allParticipant = listOf(dealer) + players

    fun matchParticipantsIsBlackJack(player: Player) {
        when {
            player.initIsBlackJack && dealer.initIsBlackJack -> {
                player.appendEarnAmount(player.battingAmount)
            }
            player.initIsBlackJack && !dealer.initIsBlackJack -> {
                player.appendEarnAmount((player.battingAmount.toDouble() * 1.5).roundToInt())
                dealer.appendEarnAmount(-(player.battingAmount.toDouble() * 0.5).roundToInt())
            }
        }
    }

    fun matchWithPlayer(player: Player) {
        when {
            player.playerCards.isBust() -> playerIsBust(player)
            dealer.playerCards.isBust() -> dealerIsBust(player)
            else -> decideWinner(player)
        }
    }

    fun playerIsBust(player: Player) {
        player.appendEarnAmount(-player.battingAmount)
        dealer.appendEarnAmount(player.battingAmount)
    }

    fun dealerIsBust(player: Player) {
        player.appendEarnAmount(player.battingAmount)
        dealer.appendEarnAmount(-player.battingAmount)
    }

    fun decideWinner(player: Player) {
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
