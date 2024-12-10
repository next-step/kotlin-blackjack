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

    "점수는 카드 점수를 정확히 계산한다. - 일반 카드 조합" {
        val player = Player("dabomi")
        player.addCards(
            listOf(
                Card.of(CardNumber.TEN, CardShape.HEART),
                Card.of(CardNumber.SIX, CardShape.CLUB),
            ),
        )

        player.score shouldBe 16
    }

    "점수는 ACE를 11로 계산한다." {
        val player = Player("dabomi")
        player.addCards(
            listOf(
                Card.of(CardNumber.FIVE, CardShape.HEART),
                Card.of(CardNumber.ACE, CardShape.SPADE),
            ),
        )

        player.score shouldBe 16
    }

    "점수는 ACE를 1로 계산한다. - 합이 21을 초과하는 경우" {
        val player = Player("dabomi")
        player.addCards(
            listOf(
                Card.of(CardNumber.TEN, CardShape.HEART),
                Card.of(CardNumber.SIX, CardShape.CLUB),
                Card.of(CardNumber.ACE, CardShape.DIAMOND),
            ),
        )

        player.score shouldBe 17
    }

    "점수는 여러 ACE를 적절히 계산한다." {
        val player = Player("dabomi")
        player.addCards(
            listOf(
                Card.of(CardNumber.ACE, CardShape.HEART),
                Card.of(CardNumber.ACE, CardShape.CLUB),
                Card.of(CardNumber.NINE, CardShape.SPADE),
            ),
        )

        player.score shouldBe 21
    }
})
