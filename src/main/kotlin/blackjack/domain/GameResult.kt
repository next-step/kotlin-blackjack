package blackjack.domain

import kotlin.math.roundToInt

object GameResult {
    fun earnMoney(player: Player, dealer: Dealer): Int {
        val playerStatus = player.blackJackStatus
        val dealerStatus = dealer.blackJackStatus
        return when {
            playerStatus == BlackJackStatus.INIT_BLACK_JACK && dealerStatus == BlackJackStatus.INIT_BLACK_JACK -> {
                player.battingAmount
            }
            playerStatus == BlackJackStatus.INIT_BLACK_JACK && dealerStatus != BlackJackStatus.INIT_BLACK_JACK -> {
                (player.battingAmount.toDouble() * 1.5).roundToInt()
            }
            playerStatus == BlackJackStatus.BUST -> {
                -player.battingAmount
            }
            dealerStatus == BlackJackStatus.BUST -> {
                player.battingAmount
            }
            playerStatus == BlackJackStatus.BLACK_JACK && dealerStatus == BlackJackStatus.BLACK_JACK -> {
                player.battingAmount
            }
            else -> decidePlayerAmount(player, dealer)
        }
    }

    private fun decidePlayerAmount(player: Player, dealer: Dealer): Int {
        val playerScore = player.participantCards.score()
        val dealerScore = dealer.participantCards.score()
        return when {
            playerScore < dealerScore -> {
                -player.battingAmount
            }
            playerScore == dealerScore -> {
                player.battingAmount
            }
            else -> {
                player.battingAmount
            }
        }
    }
}
