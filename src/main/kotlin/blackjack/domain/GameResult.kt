package blackjack.domain

import kotlin.math.roundToInt

object GameResult {
    fun earnAmount(participants: List<Participant>, dealer: Dealer): Int {
        var dealerAmount = 0
        participants.forEach {
            dealerAmount += earnDealerMoney(it, dealer)
        }
        return dealerAmount
    }

    private fun earnDealerMoney(participant: Participant, dealer: Dealer): Int {
        val playerStatus = participant.blackJackStatus
        val dealerStatus = dealer.blackJackStatus
        return when {
            playerStatus == BlackJackStatus.INIT_BLACK_JACK && dealerStatus == BlackJackStatus.INIT_BLACK_JACK -> {
                0
            }
            playerStatus == BlackJackStatus.INIT_BLACK_JACK && dealerStatus != BlackJackStatus.INIT_BLACK_JACK -> {
                (-participant.battingAmount.toDouble() * 0.5).roundToInt()
            }
            playerStatus == BlackJackStatus.BUST -> {
                participant.battingAmount
            }
            dealerStatus == BlackJackStatus.BUST -> {
                -participant.battingAmount
            }
            playerStatus == BlackJackStatus.BLACK_JACK && dealerStatus == BlackJackStatus.BLACK_JACK -> {
                0
            }
            else -> decideDealerAmount(participant, dealer)
        }
    }

    fun earnMoney(participant: Participant, dealer: Dealer): Int {
        val playerStatus = participant.blackJackStatus
        val dealerStatus = dealer.blackJackStatus
        return when {
            playerStatus == BlackJackStatus.INIT_BLACK_JACK && dealerStatus == BlackJackStatus.INIT_BLACK_JACK -> {
                participant.battingAmount
            }
            playerStatus == BlackJackStatus.INIT_BLACK_JACK && dealerStatus != BlackJackStatus.INIT_BLACK_JACK -> {
                (participant.battingAmount.toDouble() * 1.5).roundToInt()
            }
            playerStatus == BlackJackStatus.BUST -> {
                -participant.battingAmount
            }
            dealerStatus == BlackJackStatus.BUST -> {
                participant.battingAmount
            }
            playerStatus == BlackJackStatus.BLACK_JACK && dealerStatus == BlackJackStatus.BLACK_JACK -> {
                participant.battingAmount
            }
            else -> decidePlayerAmount(participant, dealer)
        }
    }

    private fun decideDealerAmount(participant: Participant, dealer: Dealer): Int {
        return when {
            participant.playerCards.score() < dealer.playerCards.score() -> {
                participant.battingAmount
            }
            participant.playerCards.score() == dealer.playerCards.score() -> {
                0
            }
            else -> {
                -participant.battingAmount
            }
        }
    }

    private fun decidePlayerAmount(participant: Participant, dealer: Dealer): Int {
        return when {
            participant.playerCards.score() < dealer.playerCards.score() -> {
                -participant.battingAmount
            }
            participant.playerCards.score() == dealer.playerCards.score() -> {
                participant.battingAmount
            }
            else -> {
                participant.battingAmount
            }
        }
    }
}
