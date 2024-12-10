package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class GameTest : StringSpec({
    "게임 초기화 시 딜러와 플레이어에게 2장의 카드가 분배된다." {
        val playerNames = listOf("kim", "da")
        val game = Game(playerNames)

        game.players.shouldHaveSize(2)
        game.dealer.cards.size shouldBe 2
        game.players.forEach { player ->
            player.cards.size shouldBe 2
        }
    }

    "플레이어의 점수가 21을 초과하면 진행 불가하다." {
        val playerNames = listOf("kim")
        val game = Game(playerNames)
        val player = game.players.first()

        player.addCards(
            listOf(
                TestCards.HEART_10,
                TestCards.CLUB_10,
                TestCards.SPADE_2,
            ),
        )

        player.canContinue() shouldBe false
    }

    "게임 결과 - 딜러 승패 계산" {
        val playerNames = listOf("kim", "lee", "park")
        val game = Game(playerNames)

        val results =
            mapOf(
                "kim" to "승",
                "lee" to "패",
                "park" to "패",
            )

        val dealerResult = game.calculateDealerResult(results)
        dealerResult shouldBe "2승 1패"
    }
})
