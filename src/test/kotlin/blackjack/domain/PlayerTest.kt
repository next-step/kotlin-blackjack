package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "이름과 초기 빈 카드 목록을 가진다." {
        val player = Player("dabomi")
        player.name shouldBe "dabomi"
        player.cards shouldBe emptyList()
        player.score shouldBe 0
    }

    "카드를 추가하면 카드 목록이 갱신된다." {
        val player = Player("dabomi")
        val newCards =
            listOf(
                Card.of(CardNumber.TEN, CardShape.HEART),
                Card.of(CardNumber.FIVE, CardShape.CLUB),
            )

        player.addCards(newCards)
        player.cards.size shouldBe 2
        player.cards shouldBe newCards
    }

    "점수가 21 초과 시 더 이상 진행할 수 없다." {
        val player = Player("dabomi")
        player.addCards(
            listOf(
                Card.of(CardNumber.TEN, CardShape.HEART),
                Card.of(CardNumber.TEN, CardShape.CLUB),
                Card.of(CardNumber.TWO, CardShape.SPADE),
            )
        )

        player.canContinue() shouldBe false
    }
})
