package step2.blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import step2.blackjack.domain.model.Gambler
import step2.blackjack.domain.model.Name

class GamblerTest: StringSpec({
    "갬블러 생성시 카드를 가지고 있지 말아야 한다." {
        val gambler = Gambler.from(Name.from("aaa"))
        gambler.cards.cards.size shouldBe 0
    }
})
