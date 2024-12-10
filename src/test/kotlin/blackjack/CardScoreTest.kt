package blackjack

import blackjack.domain.CardScore
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardScoreTest {
    @Test
    fun `카드 점수가 17이상일 때 딜러인 경우 카드를 더 받지 못한다`() {
        val score = CardScore(17)

        score.isEnabledMoreCard(true) shouldBe false
    }

    @Test
    fun `카드 점수가 16이하일 때 딜러인 경우 카드를 더 받을 수 있다`() {
        val score = CardScore(16)

        score.isEnabledMoreCard(true) shouldBe true
    }

    @Test
    fun `카드 점수가 21이상일 때 일반 플레이어인 경우 카드를 더 받지 못한다`() {
        val score = CardScore(21)

        score.isEnabledMoreCard(false) shouldBe false
    }

    @Test
    fun `카드 점수가 20이하일 때 일반 플레이어인 경우 카드를 더 받을 수 있다`() {
        val score = CardScore(20)

        score.isEnabledMoreCard(false) shouldBe true
    }
}
