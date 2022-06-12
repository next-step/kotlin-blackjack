package blackjack.domain.participant.vo

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class NameTest : StringSpec({
    "이름 객체를 생성할수 있다." {
        shouldNotThrow<Throwable> { Name("dean") }
    }

    "이름이 비어있으면 Exception을 던진다." {
        shouldThrow<IllegalArgumentException> { Name("") }
    }

    "딜러 이름을 가진 객체를 생성할수 있다" {
        Name.dealer().value shouldBe "딜러"
    }
})
