package blackjack.domain

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
})
