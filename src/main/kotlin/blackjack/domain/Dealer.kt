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

    fun dealerWinScore(players: Players): Triple<Int, Int, Int> {
        var dealerWins = 0
        var dealerLosses = 0
        var dealerDraws = 0
        players.forEach { player ->
            when (dealerMatch(player)) {
                MatchType.WIN -> {
                    dealerWins++
                }
                MatchType.LOSE -> {
                    dealerLosses++
                }
                else -> {
                    dealerDraws++
                }
            }
        }

        return Triple(dealerWins, dealerLosses, dealerDraws)
    }

    private fun judgePlayer(player: Player): MatchType {
        return when {
            isBust -> MatchType.WIN // 딜러가 버스트면 유저 승리
            player.isBust -> MatchType.LOSE // 유저가 버스트면 딜러 승리
            else -> compareScoreGap(player)
        }
    }

    private fun compareScoreGap(player: Player): MatchType {
        return when {
            bustGap == player.bustGap -> MatchType.DRAW
            bustGap < player.bustGap -> MatchType.LOSE
            bustGap > player.bustGap -> MatchType.WIN
            else -> throw IllegalArgumentException("비정상적인 상황입니다.")
        }
    }

    private fun dealerMatch(player: Player): MatchType {
        val dealerGap = bustGap
        val playerGap = player.bustGap
        return when {
            isBust -> MatchType.LOSE
            player.isBust -> MatchType.WIN
            bustGap == player.bustGap -> MatchType.DRAW
            dealerGap < playerGap -> MatchType.WIN
            dealerGap > playerGap -> MatchType.LOSE
            else -> throw IllegalArgumentException("비정상적인 상황입니다.")
        }
    }

    companion object {
        val DEALER_HIT_SCORE = Score(17)
    }
}
