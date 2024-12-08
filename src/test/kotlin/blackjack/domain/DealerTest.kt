package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({

    "카드 점수가 16점 이하라면 카드를 추가로 뽑을 수 있다." {
        val sut = Dealer(
            playerName = PlayerName("딜러"),
            cards = listOf(
                Card(
                    suit = CardSuit.SPADES,
                    number = CardNumber.ACE,
                ),
                Card(
                    suit = CardSuit.HEARTS,
                    number = CardNumber.FIVE,
                )
            )
        )

        sut.canDraw() shouldBe true
    }

    "카드 점수가 17점 이상이면 카드를 추가로 뽑을 수 없다." {
        val sut = Dealer(
            playerName = PlayerName("딜러"),
            cards = listOf(
                Card(
                    suit = CardSuit.SPADES,
                    number = CardNumber.KING,
                ),
                Card(
                    suit = CardSuit.HEARTS,
                    number = CardNumber.SEVEN,
                )
            )
        )

        sut.canDraw() shouldBe false
    }
})
