package blackjack.domain

class BlackjackResults(
    val dealer: Dealer,
    val participants: List<Participant>,
) {

    val result: List<Player> = run {
        val dealerScore = dealer.score

        participants.forEach { participant ->
            val participantScore = participant.score
            val bettingMoney = when {
                (participantScore <= Const.NUMBER_BLACKJACK && participantScore > dealerScore) || dealerScore > Const.NUMBER_BLACKJACK -> participant.bettingMoney
                (dealerScore <= Const.NUMBER_BLACKJACK && participantScore < dealerScore) || participantScore > Const.NUMBER_BLACKJACK -> -participant.bettingMoney
                else -> 0
            }

            dealer.money -= bettingMoney
            participant.money += bettingMoney
        }

        listOf(dealer, *participants.toTypedArray())
    }
}
