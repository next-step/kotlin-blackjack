package blackjack.domain.state

import blackjack.domain.card.CardFixture
import blackjack.domain.card.bustCards
import blackjack.domain.card.cards
import blackjack.domain.card.notBustCards
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class BustTest : StringSpec({

    "카드 목록이 bust가 아니면 RuntimeException 예외 처리를 한다" {
        shouldThrow<RuntimeException> {
            Bust(notBustCards())
        }
    }

    "init 함수 사용시 RuntimeException 예외 처리를 한다" {
        shouldThrow<RuntimeException> {
            Bust(bustCards()).init(cards(CardFixture.heartTwo, CardFixture.heartThree))
        }
    }

    "hit 함수 사용시 RuntimeException 예외 처리를 한다" {
        shouldThrow<RuntimeException> {
            Bust(bustCards()).hit(CardFixture.heartTwo)
        }
    }

    "stay 함수 사용시 RuntimeException 예외 처리를 한다" {
        shouldThrow<RuntimeException> {
            Bust(bustCards()).stay()
        }
    }
})
