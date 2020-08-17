package blackjack.domain.result

import blackjack.domain.player.Dealer

class PlayerResults(
    results: List<PlayerResult>
) {
    val values = results + createDealerResult(results)

    private fun createDealerResult(playerResults: List<PlayerResult>): PlayerResult {
        val earnMoney = -playerResults.sumBy { it.earnMoney }
        return PlayerResult(Dealer.NAME, earnMoney)
    }
}
