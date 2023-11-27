package blackjack.domain.model

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CardsTest : FunSpec({
    test("카드 덱 생성 정상 반환 테스트") {
        val cards = Cards.create()
        val answer = CardShape.values().size * CardNumber.values().size

        cards.cards.forEach {
            println(it)
        }

        cards.cards.size shouldBe answer
    }
})
