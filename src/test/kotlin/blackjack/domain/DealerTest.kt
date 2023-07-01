package blackjack.domain

import blackjack.fake.FakeCardGenerator
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DealerTest : FunSpec({

    test("딜러는 플레이어에게 랜덤으로 카드를 나누어준다.") {
        // given
        val card = Card(Rank.Jack, Suit.SPADE)
        val dealer = Dealer(FakeCardGenerator(card))

        // when
        val actual = dealer.dealCard()

        // then
        actual shouldBe card
    }

})
