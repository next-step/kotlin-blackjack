package blackjack.domain.state

import blackjack.domain.card.bustCards
import blackjack.domain.card.cards
import blackjack.domain.card.heartFour
import blackjack.domain.card.heartJack
import blackjack.domain.card.heartKing
import blackjack.domain.card.heartQueen
import blackjack.domain.card.heartThree
import blackjack.domain.card.heartTwo
import blackjack.domain.card.initCard
import blackjack.domain.card.notBustCards
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class HitTest : StringSpec({

    "2장 미만의 카드 목록으로 생성 시 RuntimeException 예외 처리를 한다" {
        val cards = cards(heartFour())
        shouldThrow<RuntimeException> { Hit(cards) }
    }

    "카드 목록이 bust면 RuntimeException 예외 처리를 한다" {
        shouldThrow<RuntimeException> {
            Hit(bustCards())
        }
    }

    "init 함수 사용시 RuntimeException 예외 처리를 한다" {
        shouldThrow<RuntimeException> {
            Hit(notBustCards()).init(initCard(heartTwo(), heartThree()))
        }
    }

    "hit 함수 사용시 21점을 초과하면 bust를 반환한다" {
        val firstCards = cards(heartJack(), heartQueen()) // j(10) + q(10) = 20
        val hit = Hit(firstCards)
        val state = hit.hit(heartKing()) // 20 + k(10) = 30
        state.isBust() shouldBe true
    }

    "hit 함수 사용시 21점 이하면 hit를 반환한다" {
        val firstCards = cards(heartTwo(), heartThree()) // 2 + 3 = 5
        val hit = Hit(firstCards)
        val state = hit.hit(heartFour()) // 5 + 4 = 9
        state.isHit() shouldBe true
    }

    "stay 함수 사용시 stay를 반환한다" {
        (Hit(notBustCards()).stay() is Stay) shouldBe true
    }
})
