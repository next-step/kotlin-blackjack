package blackjack.domain

import kotlin.math.roundToInt

object GameResult {
    fun earnAmount(players: List<Player>, dealer: Dealer): Int {
        var dealerAmount = 0
        players.forEach {
            dealerAmount += earnDealerMoney(it, dealer)
        }
        return dealerAmount
    }

    private fun earnDealerMoney(player: Player, dealer: Dealer): Int {
        val playerStatus = player.blackJackStatus
        val dealerStatus = dealer.blackJackStatus
        return when {
            playerStatus == BlackJackStatus.INIT_BLACK_JACK && dealerStatus == BlackJackStatus.INIT_BLACK_JACK -> {
                0
            }
            playerStatus == BlackJackStatus.INIT_BLACK_JACK && dealerStatus != BlackJackStatus.INIT_BLACK_JACK -> {
                (-player.battingAmount.toDouble() * 0.5).roundToInt()
            }
            playerStatus == BlackJackStatus.BUST -> {
                player.battingAmount
            }
            dealerStatus == BlackJackStatus.BUST -> {
                -player.battingAmount
            }
            playerStatus == BlackJackStatus.BLACK_JACK && dealerStatus == BlackJackStatus.BLACK_JACK -> {
                0
            }
            else -> decideDealerAmount(player, dealer)
        }
    }

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

    private fun decideDealerAmount(player: Player, dealer: Dealer): Int {
        return when {
            player.playerCards.score() < dealer.playerCards.score() -> {
                player.battingAmount
            }
            player.playerCards.score() == dealer.playerCards.score() -> {
                0
            }
            else -> {
                -player.battingAmount
            }
        }
    }

    private fun decidePlayerAmount(player: Player, dealer: Dealer): Int {
        return when {
            player.playerCards.score() < dealer.playerCards.score() -> {
                -player.battingAmount
            }
            player.playerCards.score() == dealer.playerCards.score() -> {
                player.battingAmount
            }
            else -> {
                player.battingAmount
            }
        }
    }
}
