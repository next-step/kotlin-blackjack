package blackjack.domain

val DEALER_NAME = EntrantName("딜러")

class Dealer : Player(DEALER_NAME, Hand()) {
    val shouldAddCard: Boolean
        get() = super.score() < DEALER_HIT_SCORE

    fun additionalCard(deck: Card) {
        if (shouldAddCard) {
            receive(Deck(listOf(deck)))
        }
    }

    fun judge(players: List<Player>): Map<String, MatchType> {
        return players.associate { player ->
            player.name to judgePlayer(player)
        }
    }

    fun dealerResult(players: Players): ResultStatistics {
        var resultStatistics = ResultStatistics()
        players.forEach { player ->
            resultStatistics =
                when (dealerMatch(player)) {
                    MatchType.WIN -> {
                        resultStatistics.increment(MatchType.WIN)
                    }

                    MatchType.LOSE -> {
                        resultStatistics.increment(MatchType.LOSE)
                    }

                    else -> {
                        resultStatistics.increment(MatchType.DRAW)
                    }
                }
        }

        return resultStatistics
    }

    private fun judgePlayer(player: Player): MatchType {
        return when {
            isBust -> MatchType.WIN
            player.isBust -> MatchType.LOSE
            else -> MatchType.evaluate(player.bustGap, bustGap)
        }
    }

    private fun dealerMatch(player: Player): MatchType {
        return when {
            isBust -> MatchType.LOSE
            player.isBust -> MatchType.WIN
            else -> MatchType.evaluate(bustGap, player.bustGap)
        }
    }

    companion object {
        val DEALER_HIT_SCORE = Score(17)
    }
}
