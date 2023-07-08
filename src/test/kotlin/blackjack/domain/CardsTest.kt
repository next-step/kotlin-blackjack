package blackjack.domain

import blackjack.enums.Denomination
import blackjack.enums.Suit
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe

class CardsTest : StringSpec({
    val cardList = listOf(
        Card(Denomination.KING, Suit.SPADE),
        Card(Denomination.QUEEN, Suit.DIAMOND),
        Card(Denomination.ACE, Suit.HEART)
    )
    val cards = Cards(cardList)

    "카드 숫자의 합을 구하여 반환한다." {
        cards.value() shouldBe 21
    }

    "카드 리스트를 가져올 수 있다." {
        cards.cards.size shouldBe 3
        cards.cards shouldContainAll cardList
    }

    "카드 리스트에 ACE가 포함 되어 있을 경우 ACE를 11로 계산해도 합이 21 초과가 아니라면 A 한 장을 11로 계산한다." {
        forAll(
            row(
                Cards(
                    listOf(
                        Card(Denomination.KING, Suit.SPADE),
                        Card(Denomination.ACE, Suit.HEART)
                    )
                ),
                21
            ),
            row(
                Cards(
                    listOf(
                        Card(Denomination.KING, Suit.SPADE),
                        Card(Denomination.ACE, Suit.HEART),
                        Card(Denomination.ACE, Suit.SPADE),
                        Card(Denomination.ACE, Suit.CLOVER),
                        Card(Denomination.ACE, Suit.DIAMOND),
                    )
                ),
                14
            ),
        ) { someCards, value ->
            someCards.value() shouldBe value
        }
    }
})
