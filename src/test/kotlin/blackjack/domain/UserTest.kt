package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.util.LinkedList

class UserTest : BehaviorSpec({
    val deck = Deck(
        LinkedList(
            listOf(
                Card(Suit.SPADE, AceCardNumber(1)), // 이때는 11
                Card(Suit.SPADE, NumberCardNumber(9)),
                Card(Suit.HEART, AceCardNumber(1)), // 이때는 1
            ),
        ),
    )

    given("비어있는 이름이 주어졌다") {
        val name = "  "
        `when`("해당 이름으로 User를 생성하면") {
            then("에러가 던져진다.") {
                shouldThrow<IllegalArgumentException> { User(name, deck) }
            }
        }
    }

    given("정상적인 정보가 주어졌다") {
        val name = "홍길동"
        `when`("해당 이름으로 User를 생성하면") {
            then("정상적으로 생성된다") {
                User(name, deck).name shouldBe name
            }
        }
    }
})
