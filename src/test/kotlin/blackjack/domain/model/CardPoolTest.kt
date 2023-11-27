package blackjack.domain.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec

class CardPoolTest : FunSpec({
    test("카드 풀을 모두 소진 후 뽑기 시도 시 IllegalStateException 예외 발생 테스트") {
        val cardPoolSize = Cards.create().cards.size
        val cardPool = CardPool.create()

        shouldThrow<IllegalStateException> {
            repeat(cardPoolSize + 1) {
                cardPool.pickAndRemove()
            }
        }
    }
})
