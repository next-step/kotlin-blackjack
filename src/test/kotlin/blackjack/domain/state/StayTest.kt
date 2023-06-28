package blackjack.domain.state

import blackjack.domain.card.bustCards
import blackjack.domain.card.cards
import blackjack.domain.card.heartFour
import blackjack.domain.card.heartThree
import blackjack.domain.card.heartTwo
import blackjack.domain.card.initCard
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class StayTest : StringSpec({

    "2장 미만의 카드 목록으로 생성 시 RuntimeException 예외 처리를 한다" {
        val cards = cards(heartFour())
        shouldThrow<RuntimeException> {
            Stay(cards)
        }
    }

    "init 함수 사용시 RuntimeException 예외 처리를 한다" {
        shouldThrow<RuntimeException> {
            Stay(bustCards()).init(initCard(heartTwo(), heartThree()))
        }
    }

    "hit 함수 사용시 RuntimeException 예외 처리를 한다" {
        shouldThrow<RuntimeException> {
            Stay(bustCards()).hit(heartTwo())
        }
    }

    "stay 함수 사용시 RuntimeException 예외 처리를 한다" {
        shouldThrow<RuntimeException> {
            Stay(bustCards()).stay()
        }
    }
})
