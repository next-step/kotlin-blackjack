package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class GameTest : StringSpec({
    "초기화 시 플레이어마다 2장의 카드가 분배된다." {
        val playerNames = listOf("kim", "da")
        val game = Game(playerNames)

        game.players.shouldHaveSize(2)
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

    "플레이어가 카드를 추가로 받을 수 있다." {
        val playerNames = listOf("kim")
        val game = Game(playerNames)
        val player = game.players.first()
        val initialCardCount = player.cards.size

        game.drawCardForPlayer(player)
        player.cards.size shouldBe initialCardCount + 1
    }

    "덱에서 카드를 올바르게 소모한다." {
        val playerNames = listOf("kim")
        val game = Game(playerNames)
        val initialDeckSize = Deck().cards.size

        val cardsDrawn = game.players.sumOf { it.cards.size }
        cardsDrawn shouldBe 2

        val remainingDeckSize = initialDeckSize - cardsDrawn
        remainingDeckSize shouldBe 50
    }
})
