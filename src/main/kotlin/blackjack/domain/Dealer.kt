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

    fun playerResult(players: List<Player>): Map<String, MatchType> {
        return players.associate { player ->
            player.name to matchOf(player)
        }
    }

    fun dealerResult(players: Players): ResultStatistics {
        var resultStatistics = ResultStatistics()
        players.forEach { player ->
            resultStatistics = resultStatistics.merge(matchToStatistics(player))
        }

        return resultStatistics
    }

    private fun matchOf(player: Player): MatchType {
        return when {
            isBust -> MatchType.WIN
            player.isBust -> MatchType.LOSE
            else -> MatchType.evaluate(player.bustGap, bustGap)
        }
    }

    companion object {
        val DEALER_HIT_SCORE = Score(17)
    }
}
