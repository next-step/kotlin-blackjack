package blackjack

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "유저의 이름이 공백일 경우 예외를 반환한다." {
        shouldThrow<IllegalArgumentException> {
            Player("")
            Player(" ")
        }
    }

    "유저의 패가 21이 넘어갈 경우 패를 추가할 수 없다." {
        val player = Player("user1")

        player.addCard(createAceCard())
        player.addCard(createBasicCard(CardNumber.TEN, CardMark.HEART))
        player.addCard(createBasicCard(CardNumber.TEN, CardMark.HEART))

        player.couldAddCard() shouldBe false
    }

    "유저는 한장의 카드를 패에 추가할 수 있다." {
        val player = Player("user1")

        player.addCard(createAceCard())

        player.getCards() shouldBe listOf(createAceCard())
    }
})
