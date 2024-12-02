package study.blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import study.blackjack.model.BlackjackPlayer

/**
 * @author 이상준
 */
class BlackjackGameServiceTest: StringSpec({
    val blackjackGameService = BlackjackGameService()

    "카드 생성 1개 테스트" {
        val player = BlackjackPlayer("test")
        blackjackGameService.addCard(player)

        player.cards.size shouldBe 1
    }
    "카드 생성 2개 테스트" {
        val player = BlackjackPlayer("test")
        blackjackGameService.addCard(player)
        blackjackGameService.addCard(player)

        player.cards.size shouldBe 2
    }
})

