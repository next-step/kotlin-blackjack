package blackjack.domain

import blackjack.test.TestDeckGenerator
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.ExpectSpec

class DealerTest : ExpectSpec({

    expect("53번 카드를 발급해도 예외가 발생하지 않는다.") {
        val deck = TestDeckGenerator.generate(Symbol.SPADE withRank Rank.ACE)
        val dealer = Dealer(deck)

        shouldNotThrow<IllegalStateException> {
            repeat(53) { dealer.dealCard() }
        }
    }
})
