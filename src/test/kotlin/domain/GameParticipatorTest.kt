package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class GameParticipatorTest : StringSpec({

    lateinit var playerPobi: Player

    beforeTest {
        playerPobi = Player.withName("pobi")
    }

    "게암 참여자가 카드를 뽑아 보관합니다" {
        playerPobi.takeCards(Card(CardNumber.ACE, CardShape.SPACE))

        playerPobi.cards.cards shouldContain Card(CardNumber.ACE, CardShape.SPACE)
    }

    "최적의 점수를 반환합니다" {
        playerPobi.takeCards(
            Card(CardNumber.ACE, CardShape.SPACE),
            Card(CardNumber.ACE, CardShape.SPACE),
            Card(CardNumber.TEN, CardShape.SPACE)
        )

        playerPobi.choiceBestScore() shouldBe 12
    }

    "21점을 초과하면 패배입니다" {
        playerPobi.takeCards(
            Card(CardNumber.TEN, CardShape.SPACE),
            Card(CardNumber.TEN, CardShape.SPACE),
            Card(CardNumber.TWO, CardShape.SPACE)
        )

        playerPobi.isLoser() shouldBe true
    }
})
