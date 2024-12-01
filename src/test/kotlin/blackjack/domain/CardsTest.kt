package blackjack.domain

import blackjack.fixture.cardFixture
import blackjack.fixture.cardsFixture
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardsTest: StringSpec ({
    "52장의 카드를 생성한다." {
        val actual = Cards.fullCards()

        actual.size shouldBe 52
    }

    "빈 카드를 생성한다." {
        val actual = Cards.emptyCards()

        actual.size shouldBe 0
    }

    "카드를 추가한다." {
        val card = cardFixture()
        val actual = Cards.emptyCards()

        actual.add(card)

        actual.size shouldBe 1
    }

    "카드를 한 장 뽑는다." {
        val card = Card(Suit.SPADE, Rank.ACE)
        val cards = Cards.emptyCards()
        cards.add(card)

        val actual = cards.draw()

        actual shouldBe card
        cards.size shouldBe 0
    }

    "카드 총 점수를 계산한다." {
        val cards = cardsFixture(
            listOf(
                cardFixture(rank = Rank.TWO),
                cardFixture(rank = Rank.THREE),
                cardFixture(rank = Rank.TEN),
            )
        )

        val actual = cards.calculateTotalValue()

        actual shouldBe 15
    }

    "ACE가 있는 경우 21에 가까운 숫자로 점수를 계산한다." {
        val cards = cardsFixture(
            listOf(
                cardFixture(rank = Rank.TEN),
                cardFixture(rank = Rank.ACE),
            )
        )

        val actual = cards.calculateTotalValue()

        actual shouldBe 21
    }

    "ACE가 여러 개 있는 경우 21에 가까운 숫자로 점수를 계산한다." {
        val cards = cardsFixture(
            listOf(
                cardFixture(rank = Rank.TEN),
                cardFixture(rank = Rank.ACE),
                cardFixture(rank = Rank.ACE),
            )
        )

        val actual = cards.calculateTotalValue()

        actual shouldBe 12
    }
})
