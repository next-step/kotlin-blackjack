package blackjack

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import blackjack.domain.model.Gamblers
import blackjack.domain.model.Name
import blackjack.domain.model.Names

class GamblersTest : StringSpec({
    "겜블러들 생성시 빈리스트가 들어올 경우 IllegalArgumentException 예외를 던진다." {
        shouldThrow<IllegalArgumentException> {
            Gamblers(emptyList())
        }
    }

    "겜블러들 생성시 사이즈가 11개인 리스트가 들어올 경우 IllegalArgumentException 예외를 던진다." {
        shouldThrow<IllegalArgumentException> {
            Names.from(List(11) { Name.from(it.toString()) })
        }
    }

    "겜블러들 생성시 이름이 [kim, lee]가 들어올 경우 [kim, lee]를 가진 겜블러들이 생성되어야 한다." {
        val gamblers = Gamblers.from(
            Names.from(
                listOf(
                    Name.from("kim"),
                    Name.from("lee")
                )
            )
        )
        gamblers.size shouldBe 2
        gamblers[0].name shouldBe Name("kim")
        gamblers[1].name shouldBe Name("lee")
    }
})
