package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class PlayerTest : StringSpec({
    "참여자 이름은 공백이면 예외가 발생한다." {
        val cardList = listOf(
            Card(Suit.SPADE, Number.TWO),
            Card(Suit.HEART, Number.TWO)
        )
        val cards = Cards(cardList)

        val message = shouldThrow<IllegalArgumentException> { Player("", cards) }
        message shouldHaveMessage "Player 이름은 필수 입력입니다."
    }

    "참여자의 카드 포인트가 21을 넘기면 Bust 이다." {
        forAll(
            row(
                listOf(
                    Card(Suit.CLUB, Number.SIX),
                    Card(Suit.DIAMOND, Number.SIX),
                    Card(Suit.HEART, Number.SIX),
                    Card(Suit.SPADE, Number.SIX)
                ),
                true
            ),
            row(
                listOf(
                    Card(Suit.CLUB, Number.SIX),
                    Card(Suit.DIAMOND, Number.SIX)
                ),
                false
            )
        ) { cardList, isBust ->
            val cards = Cards(cardList)
            val player = Player("테스터", cards)

            player.isBust() shouldBe isBust
        }
    }
})
