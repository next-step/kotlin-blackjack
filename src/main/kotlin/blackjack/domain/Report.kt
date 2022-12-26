package blackjack.domain

object Report {

    fun report(player: Participant, others: List<Participant>): VictoryOrDefeat {
        if (player.totalScore > Participant.BLACK_JACK) {
            return VictoryOrDefeat(0, 1)
        }

        var win = 0
        var lose = 0
        repeat(others.size) action@{ index ->
            if (others[index].totalScore > player.totalScore) {
                lose++
                return@action
            }
            if (others[index].totalScore < player.totalScore) win++
        }
        return VictoryOrDefeat(win, lose)
    }
}
