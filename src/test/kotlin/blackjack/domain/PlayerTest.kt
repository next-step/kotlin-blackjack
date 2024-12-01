package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "플레이어가 새로운 카드를 추가한다." {
        val player = Player("dabomi")
        player.addCards(listOf("2하트", "3스페이드"))
        player.getCardList() shouldBe listOf("2하트", "3스페이드")
    }

    "플레이어가 빈 카드 리스트를 반환한다." {
        val player = Player("dabomi")
        player.getCardList() shouldBe emptyList()
    }
})
