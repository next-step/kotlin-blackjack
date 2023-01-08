package blackjack.domain

object Report {

    fun calculate(player: Player, dealer: Dealer, others: List<Player>): Boolean {
        if (dealer.totalScore > BLACK_JACK) return true
        if (dealer.totalScore == BLACK_JACK) return false
        if (player.totalScore > BLACK_JACK) return false
        if (player.totalScore == BLACK_JACK) return true

        var win = 0
        var lose = 0
        repeat(others.size) action@{ index ->
            if (others[index].totalScore > player.totalScore) lose++
            if (others[index].totalScore < player.totalScore) win++
        }

        return lose != 0 && dealer.totalScore < player.totalScore
    }

    fun calculateByDealer(dealer: Dealer, players: List<Player>): VictoryOrDefeat {
        if (dealer.totalScore > BLACK_JACK) return VictoryOrDefeat(0, players.size)

        var win = 0
        var lose = 0
        repeat(players.size) action@{ index ->
            if (players[index].totalScore > dealer.totalScore) lose++
            if (players[index].totalScore < dealer.totalScore) win++
        }
        return VictoryOrDefeat(win, lose)
    }
}
