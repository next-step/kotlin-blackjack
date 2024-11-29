package blackjack.domain.deck
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DefaultDeckGeneratorTest : StringSpec({
    "generate()는 52장의 카드로 구성된 덱을 생성한다." {
        DefaultDeckGenerator().generate().cards.size shouldBe 52
    }
})
