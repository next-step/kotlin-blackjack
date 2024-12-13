package study.blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import study.blackjack.model.BlackjackUser

/**
 * @author 이상준
 */
class BlackjackOperatorTest : StringSpec({
    val blackjackOperator = BlackjackOperator()

    "카드 생성 1개 테스트" {
        val player = BlackjackUser("test")
        blackjackOperator.addCard(player)

        player.cards().size() shouldBe 1
    }
    "카드 생성 2개 테스트" {
        val player = BlackjackUser("test")
        blackjackOperator.addCard(player)
        blackjackOperator.addCard(player)

        player.cards().size() shouldBe 2
    }
})
