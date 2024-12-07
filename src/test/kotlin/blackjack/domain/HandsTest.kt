package blackjack.domain

import blackjack.fixture.cardFixture
import blackjack.fixture.handsFixture
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class HandsTest : StringSpec({
    "카드를 추가한다." {
        val card = cardFixture()
        val actual = Hands()

        actual.add(card)

        actual.size shouldBe 1
    }

    "카드 총 점수를 계산한다." {
        val cards =
            handsFixture(
                cardFixture(rank = Rank.TWO),
                cardFixture(rank = Rank.THREE),
                cardFixture(rank = Rank.TEN),
            )

        val actual = cards.calculateTotalValue()

        actual shouldBe 15
    }

    "ACE 1개, TEN 1개인 경우 21점으로 계산한다." {
        val cards =
            handsFixture(
                cardFixture(rank = Rank.TEN),
                cardFixture(rank = Rank.ACE),
            )

        val actual = cards.calculateTotalValue()

        actual shouldBe 21
    }

    "ACE 2개인 경우 12점으로 계산한다." {
        val cards =
            handsFixture(

                cardFixture(rank = Rank.ACE),
                cardFixture(rank = Rank.ACE),
            )

        val actual = cards.calculateTotalValue()

        actual shouldBe 12
    }

    "ACE 2개, NINE 1개인 경우 21점으로 계산한다." {
        val cards =
            handsFixture(
                cardFixture(rank = Rank.NINE),
                cardFixture(rank = Rank.ACE),
                cardFixture(rank = Rank.ACE),
            )

        val actual = cards.calculateTotalValue()

        actual shouldBe 21
    }
})
