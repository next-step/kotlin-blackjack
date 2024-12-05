package study.blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import study.blackjack.model.BlackjackPlayer

/**
 * @author 이상준
 */
class BlackjackOperatorTest : StringSpec({
    val blackjackOperator = BlackjackOperator()

    "카드 생성 1개 테스트" {
        val player = BlackjackPlayer("test")
        blackjackOperator.addCard(player)

        player.cards().cardCount() shouldBe 1
    }
    "카드 생성 2개 테스트" {
        val player = BlackjackPlayer("test")
        blackjackOperator.addCard(player)
        blackjackOperator.addCard(player)

        player.cards().cardCount() shouldBe 2
    }
})
