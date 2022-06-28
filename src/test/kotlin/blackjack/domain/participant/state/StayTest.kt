package blackjack.domain.participant.state

import blackjack.domain.deck.Card
import blackjack.domain.deck.CardNumber
import blackjack.domain.deck.CardPattern
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

internal class StayTest : FreeSpec({

    "스테이에서" - {
        // given
        val cards = mutableListOf(
            Card(pattern = CardPattern.CLOVER, number = CardNumber.TEN)
        )

        val stay = Stay(hand = Hand(cards = cards))

        "카드 받기를 요청하면 예외가 발생한다." {
            shouldThrowExactly<IllegalStateException> { stay.receiveCard(card = Card(CardPattern.HEART, CardNumber.TWO)) }
        }

        "스테이로 상태변화를 요청하면 예외가 발생한다." {
            shouldThrowExactly<IllegalStateException> { stay.stay() }
        }

        "종료되었는지 물으면 True를 반환한다." {
            stay.isFinished() shouldBe true
        }

        "카드 목록을 반환할 수 있다." {
            stay.cards().shouldContainExactly(cards)
        }

        "점수를 반환할 수 있다." {
            stay.score() shouldBe 10
        }
    }
})
