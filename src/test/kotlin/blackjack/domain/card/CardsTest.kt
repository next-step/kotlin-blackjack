package blackjack.domain.card

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class CardsTest : StringSpec({

    "카드들은 최소 2장이여야 한다." {
        val message = shouldThrow<IllegalArgumentException> {
            Cards(emptyList())
        }

        message shouldHaveMessage "최초 카드 2장 이상이여야 합니다."
    }

    "카드를 얻으면 총 카드 숫가 1 만큼 증가된다." {
        val cards = Cards(
            listOf(
                Card(Suit.CLUB, Number.SIX),
                Card(Suit.DIAMOND, Number.SIX),
            )
        )
        val initCount = cards.cardStack.size

        val card = Card(Suit.CLUB, Number.ACE)
        cards.add(card)

        cards.cardStack shouldHaveSize initCount.inc()
    }

    "참여자의 카드 포인트가 총합을 계산하다." {
        forAll(
            row(
                listOf(
                    Card(Suit.CLUB, Number.SIX),
                    Card(Suit.DIAMOND, Number.SIX),
                    Card(Suit.HEART, Number.SIX),
                    Card(Suit.SPADE, Number.SIX)
                ),
                24
            ),
            row(
                listOf(
                    Card(Suit.CLUB, Number.SIX),
                    Card(Suit.DIAMOND, Number.SIX)
                ),
                12
            )
        ) { cardList, point ->
            val cards = Cards(cardList)

            cards.point() shouldBe point
        }
    }

    "Ace 는 블랙잭 숫자보다 합산이 작을 경우엔 포인트가 11로 계산된다." {
        val cards = Cards(
            listOf(
                Card(Suit.SPADE, Number.TWO),
                Card(Suit.HEART, Number.TWO),
                Card(Suit.SPADE, Number.ACE)
            )
        )

        cards.point() shouldBe 15
    }

    "Ace 는 블랙잭 숫자보다 합산이 클 경우엔 포인트가 1로 계산된다." {
        val cards = Cards(
            listOf(
                Card(Suit.HEART, Number.NINE),
                Card(Suit.DIAMOND, Number.NINE),
                Card(Suit.SPADE, Number.ACE)
            )
        )

        cards.point() shouldBe 19
    }
})
