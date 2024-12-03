package blackjack.domain

class BlackjackGameResult(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    private val result: MutableMap<Player, GameMatchResult> = mutableMapOf()

    init {
        players.forEach { player ->
            result[player] = player.compare(dealer)
        }
    }

    fun extractPlayerGameResult(): Map<Player, GameMatchResult> {
        return result.toMap()
    }

    fun extractDealerGameResult(): String {
        val (wins, draws, losses) = calculateGameResults()
        return formatGameResults(wins, draws, losses)
    }

    private fun calculateGameResults(): Triple<Int, Int, Int> {
        return result.values.fold(Triple(0, 0, 0)) { (wins, draws, losses), gameResult ->
            when (gameResult) {
                GameMatchResult.WIN -> Triple(wins, draws, losses + 1)
                GameMatchResult.LOSE -> Triple(wins + 1, draws, losses)
                GameMatchResult.DRAW -> Triple(wins, draws + 1, losses)
            }
        }
    }

    private fun formatGameResults(
        wins: Int,
        draws: Int,
        losses: Int,
    ): String {
        val resultStrings = mutableListOf<String>()

        if (wins > 0) resultStrings.add("${wins}승")
        if (draws > 0) resultStrings.add("${draws}무")
        if (losses > 0) resultStrings.add("${losses}패")

        return "딜러: " + resultStrings.joinToString(" ")
    }
}
