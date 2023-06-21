package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class UserTest : BehaviorSpec({

    given("비어있는 이름이 주어졌다") {
        val name = "  "
        `when`("해당 이름으로 User를 생성하면") {
            then("에러가 던져진다.") {
                shouldThrow<IllegalArgumentException> { User(name) }
            }
        }
    }

    given("정상적인 이름이 주어졌다") {
        val name = "홍길동"
        `when`("해당 이름으로 User를 생성하면") {
            then("정상적으로 생성된다") {
                User(name).name shouldBe name
            }
        }
    }
})
