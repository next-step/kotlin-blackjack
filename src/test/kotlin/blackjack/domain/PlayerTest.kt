package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class PlayerTest : FreeSpec({
    "카드를 핸드에 추가한다." - {
        val sut = Player(
            playerName = PlayerName("테스트")
        )

        sut.addCard(Card(CardSuit.HEARTS, CardNumber.TWO))

        sut.currentCards shouldBe listOf(DrawCard(CardSuit.HEARTS, CardNumber.TWO))
    }

    "카드를 핸드에 추가할 때 카드의 총 합이 21 이상이면 더이상 카드를 뽑지 않는다." - {
        val sut = Player(
            playerName = PlayerName("테스트"),
            cards = mutableListOf(
                Card(CardSuit.HEARTS, CardNumber.TEN),
                Card(CardSuit.HEARTS, CardNumber.TEN),
            ),
            draw = true,
        )

        sut.addCard(Card(CardSuit.HEARTS, CardNumber.TWO))

        sut.canDraw() shouldBe false
    }

    "카드를 더이상 뽑지 않는다." - {
        val sut = Player(
            playerName = PlayerName("테스트")
        )

        sut.stopDraw()

        sut.canDraw() shouldBe false
    }
})
