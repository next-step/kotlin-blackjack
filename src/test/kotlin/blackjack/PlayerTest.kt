package blackjack

import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class PlayerTest : FreeSpec({
    "핸드에 존재하는 카드 값의 총합을 계산한다." - {
        listOf(
            row(
                "2, 3, 4, A인 경우 합계 20이다.",
                Player(
                    PlayerName("테스트"),
                    mutableListOf(
                        Card(
                            CardSuit.HEARTS,
                            CardNumber.TWO,
                        ),
                        Card(
                            CardSuit.SPADES,
                            CardNumber.THREE,
                        ),
                        Card(
                            CardSuit.DIAMONDS,
                            CardNumber.FOUR,
                        ),
                        Card(
                            CardSuit.CLUBS,
                            CardNumber.ACE,
                        ),
                    )
                ),
                20,
            ),
            row(
                "2, 3, 4, A, A인 경우 합계 21이다.",
                Player(
                    PlayerName("테스트"),
                    mutableListOf(
                        Card(
                            CardSuit.HEARTS,
                            CardNumber.TWO,
                        ),
                        Card(
                            CardSuit.SPADES,
                            CardNumber.THREE,
                        ),
                        Card(
                            CardSuit.DIAMONDS,
                            CardNumber.FOUR,
                        ),
                        Card(
                            CardSuit.CLUBS,
                            CardNumber.ACE,
                        ),
                        Card(
                            CardSuit.DIAMONDS,
                            CardNumber.ACE,
                        ),
                    )
                ),
                21,
            ),
        )
            .forAll { (description, hands, expected) ->
                description - {
                    hands.totalValue() shouldBe expected
                }
            }
    }

    "카드를 핸드에 추가한다." - {
        val sut = Player(PlayerName("테스트"))

        sut.addCard(Card(CardSuit.HEARTS, CardNumber.TWO))

        sut.currentCards shouldBe listOf(DrawCard(CardSuit.HEARTS, CardNumber.TWO))
    }

    "카드를 핸드에 추가할 때 카드의 총 합이 21 이상이면 더이상 카드를 뽑지 않는다." - {
        val sut = Player(
            name = PlayerName("테스트"),
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
        val sut = Player(PlayerName("테스트"))

        sut.stopDraw()

        sut.canDraw() shouldBe false
    }
})