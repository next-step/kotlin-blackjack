package com.nextstep.blackjack.domain.card

import com.nextstep.blackjack.domain.card.CardNumber.ACE
import com.nextstep.blackjack.domain.card.CardNumber.KING
import com.nextstep.blackjack.domain.card.CardNumber.TWO
import com.nextstep.blackjack.domain.card.CardPattern.SPADE
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class CardTest : FunSpec({
    test("카드가 Ace 인지 아닌지 알 수 있다") {
        forAll(
            row(ACE, true),
            row(TWO, false),
            row(KING, false),
        ) {
            cardNumber, expected ->
            val card = Card(cardNumber, SPADE)
            card.isAce() shouldBe expected
        }
    }

    test("카드의 점수를 가져올 수 있다") {
        forAll(
            row(ACE, 1),
            row(TWO, 2),
            row(KING, 10),
        ) { cardNumber, expected ->
            val card = Card(cardNumber, SPADE)
            card.score() shouldBe expected
        }
    }
})
