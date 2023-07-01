package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CardTest : FunSpec({

    test("카드는 (1-9, K, Q, J, A) 중 하나의 rank 와 (하트, 스페이드, 클로바) 중 하나의 무늬를 가진다.") {
        // given
        val card = Card(rank = Rank.Number(3), suit = Suit.HEART)
        val expectedNumber = Rank.Number(3)
        val expectedSuit = Suit.HEART

        // when, then
        card.rank shouldBe expectedNumber
        card.suit shouldBe expectedSuit
    }

})
