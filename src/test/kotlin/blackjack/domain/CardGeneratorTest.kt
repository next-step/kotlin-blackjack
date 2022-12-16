package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardGeneratorTest : StringSpec({

    val cardGenerator = CardGenerator()

    "카드 생성 시 총 52장이 생성된다." {
        // given
        // when
        val cards = cardGenerator.generate()
        // then
        cards.size shouldBe 52
    }
})
