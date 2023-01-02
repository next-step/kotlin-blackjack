package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.holder.Dealer
import blackjack.domain.value.BettingAmount
import blackjack.domain.value.Point
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class BlackJackTest : StringSpec({

    "블랙잭일 때 calculatePoint 함수를 호출하면 포인트 21임을 확인한다." {
        BlackJack(mutableSetOf(Card.CLOVER_A, Card.CLOVER_K)).calculatePoint() shouldBe Point(21)
    }

    "블랙잭일 때 카드를 더 받으려 하면 IllegalStateException이 발생한다." {
        shouldThrow<IllegalStateException> {
            BlackJack(
                mutableSetOf(
                    Card.CLOVER_A,
                    Card.CLOVER_K
                )
            ).draw(setOf(Card.CLOVER_2))
        }.shouldHaveMessage("더이상 카드를 받을 수 없습니다.")
    }

    "10000원을 베팅했을 때 블랙잭으로 승리하면 상금은 15000원 이다." {
        BlackJack(mutableSetOf(Card.CLOVER_A, Card.CLOVER_K)).earning(
            Dealer().firstTurn(
                setOf(
                    Card.CLOVER_J,
                    Card.CLOVER_Q
                )
            ), BettingAmount(10000)
        ) shouldBe 15000
    }
})
