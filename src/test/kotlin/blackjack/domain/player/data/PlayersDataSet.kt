package blackjack.domain.player.data

import blackjack.domain.Blackjack
import blackjack.domain.player.Players
import io.kotest.matchers.shouldBe

class PlayersDataSet {
    companion object {
        private val NOT_FINISHED_SCORE_RANGE = (2 until Blackjack.BLACKJACK_BEST_SCORE)
        fun testData(count: Int): Players =
            Players((1..count).map { PlayerDataSet.testDataWithTwoCards() })

        fun testDataWithOnlyNotFinishedPlayer(count: Int): Players {
            val players =
                Players(
                    (1..count).map {
                        PlayerDataSet.testDataWithTwoCards(NOT_FINISHED_SCORE_RANGE.random())
                    }
                )

            players.count { it.isFinished() } shouldBe 0
            players.count { it.isNotFinished() } shouldBe count

            return players
        }
    }
}
