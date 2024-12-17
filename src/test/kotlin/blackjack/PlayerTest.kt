package blackjack

import blackjack.domain.BetAmount
import blackjack.domain.CardMark
import blackjack.domain.CardNumber
import blackjack.domain.Player
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "유저의 이름이 공백일 경우 예외를 반환한다." {
        shouldThrow<IllegalArgumentException> {
            Player("", BetAmount(1000.0))
            Player(" ", BetAmount(1000.0))
        }
    }

    "유저는 한장의 카드를 패에 추가할 수 있다." {
        val player = Player("user1", BetAmount(1000.0))

        player.addCard(createAceCard())

        player.hand.cards.size() shouldBe 1
    }

    "유저의 패가 21을 넘게 되면 버스트가 된다." {
        val player = Player("user1", BetAmount(1000.0))
        val listOfCards =
            listOf(
                createBasicCard(CardNumber.TEN, CardMark.HEART),
                createBasicCard(CardNumber.TEN, CardMark.HEART),
                createBasicCard(CardNumber.TEN, CardMark.HEART),
            )

        listOfCards.forEach { player.addCard(it) }

        player.isBust() shouldBe true
    }

    "유저의 베팅 금액이 0원 이하일 경우 예외를 반환한다." {
        shouldThrow<IllegalArgumentException> {
            Player("player", BetAmount(0.0))
            Player("player", BetAmount(-1000.0))
        }
    }
})
