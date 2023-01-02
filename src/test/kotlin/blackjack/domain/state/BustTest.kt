package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.holder.Dealer
import blackjack.domain.value.BettingAmount
import blackjack.domain.value.Point
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class BustTest : StringSpec({

    "bust 일 때 calculatePoint 함수를 호출하면 포인트 21임을 확인한다." {
        Bust(mutableSetOf(Card.CLOVER_J, Card.CLOVER_K, Card.CLOVER_2)).calculatePoint() shouldBe Point(0)
    }

    "bust 일 때 카드를 더 받으려 하면 IllegalStateException이 발생한다." {
        shouldThrow<IllegalStateException> {
            Bust(
                mutableSetOf(
                    Card.CLOVER_J,
                    Card.CLOVER_K,
                )
            ).draw(setOf(Card.CLOVER_2))
        }.shouldHaveMessage("더이상 카드를 받을 수 없습니다.")
    }

    "bust 일 때 10000원을 베팅했을 때 패배하면 상금은 -10000원 이다." {
        Bust(mutableSetOf(Card.CLOVER_J, Card.CLOVER_K, Card.CLOVER_2)).earning(
            Dealer().firstTurn(
                setOf(
                    Card.CLOVER_J,
                    Card.CLOVER_Q
                )
            ), BettingAmount(10000)
        ) shouldBe -10000
    }

})
