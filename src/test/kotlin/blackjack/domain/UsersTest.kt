package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class UsersTest : BehaviorSpec({

    given("비어있는 유저 배열이 있다.") {
        val userList = emptySet<User>()
        `when`("해당 배열로 Users를 만들면") {
            then("에러가 던져진다.") {
                shouldThrow<IllegalArgumentException> { Users(userList) }
            }
        }
    }

    given("중복된 유저가 존재하는 유저 배열이 있다.") {
        val userList = listOf("홍길동", "홍길동")
        `when`("해당 배열로 Users를 만들면") {
            then("에러가 던져진다.") {
                shouldThrow<IllegalArgumentException> { Users(userList) }
            }
        }
    }

    given("비어있지 않은 유저 배열이 있다.") {
        val userList = setOf(User("홍길동"), User("김한빈"))
        `when`("해당 배열로 Users를 만들면") {
            then("정상적으로 생성된다.") {
                Users(userList).size shouldBe 2
            }
        }
    }
})
