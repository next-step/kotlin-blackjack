package blackjack.domain.player

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class NameTest : StringSpec({
    "이름 객체를 생성할수 있다." {
        shouldNotThrow<Throwable> { Name("dean") }
    }

    "이름이 비어있으면 Exception을 던진다." {
        shouldThrow<IllegalArgumentException> { Name("") }
    }
})
