package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "플레이어가 hit 요청을 하면 새로운 카드를 받는다." {
        val player = Player("tester")
        val pokerCard = PokerCard(PokerSymbol.CLUBS, 11, "A")
        player.hands().size shouldBe 0
        player.hit(pokerCard)
        player.hands().size shouldBe 1
    }

    "참여자의 손패가 21이상이라면 더이상 카드를 받지 못한다." {
        val player = Player("tester")
        val pokerCard = PokerCard(PokerSymbol.CLUBS, 10, "K")
        player.hit(pokerCard, pokerCard, pokerCard)
        shouldThrow<IllegalStateException> {
            player.hit(pokerCard)
        }.message shouldBe "더이상 카드를 받을 수 없습니다."
    }

    "사용자가 스탑을 외치면 더이상 손패를 받지 않는다." {
        val player = Player("tester")
        val pokerCard = PokerCard(PokerSymbol.CLUBS, 10, "K")
        player.stand()
        shouldThrow<IllegalStateException> {
            player.hit(pokerCard)
        }.message shouldBe "더이상 카드를 받을 수 없습니다."
    }

    "사용자 손패에서 가장 최적의 값을 구해준다." {
        val king = PokerCard(PokerSymbol.CLUBS, 10, "K")
        val ace = PokerCard(PokerSymbol.CLUBS, 11, "A")
        val queen = PokerCard(PokerSymbol.CLUBS, 10, "Q")
        val five = PokerCard(PokerSymbol.CLUBS, 5, "5")
        val seven = PokerCard(PokerSymbol.CLUBS, 7, "7")
        listOf(
            arrayOf(king, king, ace) to 21,
            arrayOf(queen, five) to 15,
            arrayOf(ace, ace, ace) to 13,
            arrayOf(seven, queen, ace, ace, ace, ace) to 21
        ).forAll { (input, expected) ->
            val player = Player("tester")
            player.hit(*input)

            player.optimalValue() shouldBe expected
        }
    }
})
