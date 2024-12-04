package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "플레이어가 새로운 카드를 추가한다." {
        val player = Player("dabomi")
        player.addCards(listOf(Pair(CardNumber.TWO, CardShape.HEART), Pair(CardNumber.THREE, CardShape.SPADE)))
        player.getCardList() shouldBe listOf("TWO of HEART", "THREE of SPADE")
    }

    "플레이어가 빈 카드 리스트를 반환한다." {
        val player = Player("dabomi")
        player.getCardList() shouldBe emptyList()
    }

    "플레이어가 여러 번 카드를 추가한다." {
        val player = Player("dabomi")
        player.addCards(listOf(Pair(CardNumber.THREE, CardShape.SPADE)))
        player.addCards(listOf(Pair(CardNumber.TWO, CardShape.HEART)))
        player.getCardList() shouldBe listOf("THREE of SPADE", "TWO of HEART")
    }
})
