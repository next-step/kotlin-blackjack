package domain.blackjack.model

import blackjack.domain.model.Card
import blackjack.domain.model.CardNumber
import blackjack.domain.model.CardShape
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CardTest : FunSpec({
    test("카드 이름 정상 반환 테스트") {
        val card = Card(CardNumber.ACE, CardShape.Spade)
        val answer = "A스페이드"

        card.toString() shouldBe answer
    }
})
