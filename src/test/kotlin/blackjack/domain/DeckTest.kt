package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe

class DeckTest : ExpectSpec({

    val card = Symbol.SPADE of Rank.ACE

    expect("카드가 52장보다 적으면 예외가 발생한다.") {
        shouldThrow<IllegalArgumentException> {
            deck(FakeShuffleStrategy) {
                repeat(51) { +card }
            }
        }
    }

    expect("카드가 52장보다 많으면 예외가 발생한다.") {
        shouldThrow<IllegalArgumentException> {
            deck(FakeShuffleStrategy) {
                repeat(53) { +card }
            }
        }
    }

    expect("카드를 한 장 뽑으면 카드가 한 장 줄어든다.") {
        val deck = deck(FakeShuffleStrategy) {
            repeat(52) { +card }
        }

        deck.draw()
        deck.size shouldBe 51
    }
})
