package player

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class NameTest : FunSpec({
    context("init") {
        forAll(
            row("jeff.42"),
            row("clo.ddd"),
            row("hellomy"),
        ) { input ->
            test("이름은 5글자 이상이 되면 안됩니다.") {
                val exception = shouldThrowExactly<IllegalArgumentException> { Name(input) }
                exception.message shouldBe "플레이어의 이름은 5글자를 초과할 수 없습니다."
            }
        }

        forAll(
            row("jeff", "jeff"),
            row("clo", "clo"),
            row("hell", "hell"),
        ) { input, expected ->
            test("이름은 5글자 이상이 되면 안됩니다.") {
                val actual = Name(input)
                actual.value shouldBe expected
            }
        }
    }
})
