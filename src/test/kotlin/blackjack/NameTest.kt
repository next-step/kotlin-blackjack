package blackjack

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import blackjack.domain.model.Name

class NameTest : StringSpec({
    "이름에 빈 문자열이 들어올 경우 IllegalArgumentException 예외를 던진다." {
        shouldThrow<IllegalArgumentException> {
            Name.from("")
        }
    }

    "이름에 공백, 개행, 탭이동 문자만 들어올 경우 IllegalArgumentException 예외를 던진다." {
        shouldThrow<IllegalArgumentException> {
            Name.from(" ")
            Name.from("\t")
            Name.from("\n")
        }
    }

    "이름에 11자리 이상이라면 IllegalArgumentException 예외를 던진다." {
        shouldThrow<IllegalArgumentException> {
            Name.from("12345678901")
        }
    }

    "이름에 `kim`가 들어올 경우 `kim`를 가진 이름이 생성되어야 한다." {
        val name = Name.from("kim")
        name.value shouldBe "kim"
    }
})
