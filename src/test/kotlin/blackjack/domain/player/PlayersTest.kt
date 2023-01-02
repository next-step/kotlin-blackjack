package blackjack.domain.player

import blackjack.domain.card.data.CardsDataSet
import blackjack.domain.player.data.PlayersDataSet
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class PlayersTest : FunSpec({
    context("Players가 정상적으로 생성된다.") {
        withData(
            nameFn = { "player 총 ${it.size}명" },
            (0..20).map { playerSize ->
                (1..playerSize).map {
                    Player("user$it", CardsDataSet.testDataWithTwoCards())
                }
            }
        ) { playerList ->
            val players = Players(playerList)

            players shouldNotBe null
            players shouldHaveSize playerList.size
            players.names() shouldContainAll playerList.map { it.name }
        }
    }

    context("Players가 종료되지 않은 player 목록을 정상적으로 리턴한다.") {
        withData(
            nameFn = { "totalPlayerCount: ${it.first}명, finishedPlayerCount: ${it.second}명" },
            listOf(
                10 to 4,
                20 to 10,
                5 to 4,
                5 to 0
            )
        ) { (totalPlayerCount, finishedPlayerCount) ->
            val players =
                testDataWithFinishedPlayer(totalPlayerCount, finishedPlayerCount)

            players.notFinishedPlayers() shouldHaveSize totalPlayerCount - finishedPlayerCount
            players.notFinishedPlayers() shouldContainAll players.filter { it.isNotFinished() }
        }
    }
})

private fun testDataWithFinishedPlayer(totalPlayerCount: Int, finishedPlayerCount: Int): Players {
    val players = PlayersDataSet.testDataWithOnlyNotFinishedPlayer(totalPlayerCount)

    players.shuffled()
        .take(finishedPlayerCount)
        .forEach { player ->
            while (player.isNotFinished()) {
                player.stay()
            }
        }

    players.count { it.isFinished() } shouldBe finishedPlayerCount
    players.count { it.isNotFinished() } shouldBe totalPlayerCount - finishedPlayerCount

    return players
}
