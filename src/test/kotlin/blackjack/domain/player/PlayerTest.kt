package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardKind
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardSet
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PlayerTest : FunSpec({
    test("플레이어는 이름을 가진다") {
        val player = Player("cookie")
        player.name shouldBe "cookie"
    }

    test("플레이어의 이름이 없는 경우 예외가 발생한다") {
        shouldThrow<IllegalArgumentException> {
            Player("")
        }
    }

    test("플레이어는 여러장의 카드를 가진다") {
        val cardSet = CardSet(
            listOf(
                Card.of(CardKind.DIAMOND, CardNumber.ACE),
                Card.of(CardKind.DIAMOND, CardNumber.TWO)
            )
        )

        val player = Player("cookie", CardSet(cardSet))

        player.cardSet shouldBe cardSet
    }

    test("플레이어는 카드를 지급받을 수 있다") {
        val player = Player("cookie")

        val cardSet = CardSet(
            listOf(
                Card.of(CardKind.DIAMOND, CardNumber.ACE),
                Card.of(CardKind.DIAMOND, CardNumber.TWO)
            )
        )

        val actual = player.receiveCard(cardSet)

        actual.cardSet shouldBe cardSet
    }
})


