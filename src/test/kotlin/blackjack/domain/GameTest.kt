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
                Card.of(CardNumber.TEN, CardShape.HEART),
                Card.of(CardNumber.TEN, CardShape.CLUB),
                Card.of(CardNumber.TWO, CardShape.SPADE),
            ),
        )

        game.canContinue(player) shouldBe false
    }

    "딜러의 점수가 16 이하이면 카드를 추가로 받는다." {
        val playerNames = listOf("kim")
        val game = Game(playerNames)

        val result = game.handleDealerTurn()
        result shouldBe (game.dealer.score <= 16)
    }

    "게임 결과 - 딜러 승패 계산" {
        val playerNames = listOf("kim", "lee", "park")
        val game = Game(playerNames)

        val results = mapOf(
            "kim" to "승",
            "lee" to "패",
            "park" to "패"
        )

        val dealerResult = game.calculateDealerResult(results)
        dealerResult shouldBe "2승 1패"
    }
})
