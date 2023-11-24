package blackjack

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import blackjack.domain.model.Name
import blackjack.domain.model.Names

class NamesTest: StringSpec({
    "이름들 생성시 빈리스트가 들어올 경우 IllegalArgumentException 예외를 던진다." {
        shouldThrow<IllegalArgumentException> {
            Names.from(emptyList())
        }
    }

    "이름들 생성시 사이즈가 11개인 리스트가 들어올 경우 IllegalArgumentException 예외를 던진다." {
        shouldThrow<IllegalArgumentException> {
            Names.from(List(11) { Name.from(it.toString()) })
        }
    }

    "이름들 생성시 [aaa, bbb]가 들어올 경우 [aaa, bbb]를 가진 이름들이 생성되어야 한다." {
        val names = Names.from(listOf(Name.from("aaa"), Name.from("bbb")))
        names.size shouldBe 2
        names shouldContainExactly listOf(Name.from("aaa"), Name.from("bbb"))
    }
})
