package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.holder.Dealer
import blackjack.domain.value.BettingAmount
import blackjack.domain.value.Point
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class HitTest : StringSpec({
    "Hit 일 때 calculatePoint 함수를 호출하면 계산된 점수를 리턴함을 확인한다." {
        Hit(mutableSetOf(Card.CLOVER_J, Card.CLOVER_K)).calculatePoint() shouldBe Point(20)
        Hit(mutableSetOf(Card.CLOVER_2, Card.CLOVER_5)).calculatePoint() shouldBe Point(7)
        Hit(mutableSetOf(Card.CLOVER_A, Card.CLOVER_5)).calculatePoint() shouldBe Point(16)
        Hit(mutableSetOf(Card.CLOVER_J, Card.CLOVER_2)).calculatePoint() shouldBe Point(12)
        Hit(mutableSetOf(Card.CLOVER_5, Card.DIAMOND_5, Card.CLOVER_A)).calculatePoint() shouldBe Point(21)
    }

    "Hit 일 때 draw함수 호출 시 카드가 추가된 객체가 반환된다." {
        //given
        val hit = Hit(
            mutableSetOf(
                Card.CLOVER_J,
                Card.CLOVER_5,
            )
        )
        //when
        val draw = hit.draw(setOf(Card.CLOVER_2))
        //then
        draw.cards shouldBe mutableSetOf(
            Card.CLOVER_J,
            Card.CLOVER_5,
            Card.CLOVER_2,
        )

    }

    "Hit 일 때 10000원을 베팅, 플레이어 J, 7, 딜러 블랙잭이면 상금은 -10000원 이다." {
        Hit(mutableSetOf(Card.CLOVER_J, Card.CLOVER_7)).earning(
            Dealer().firstTurn(
                setOf(
                    Card.CLOVER_Q,
                    Card.CLOVER_A
                )
            ), BettingAmount(10000)
        ) shouldBe -10000
    }

    "Hit 일 때 10000원을 베팅, 플레이어 J, 7, 딜러 Q, 8이면 상금은 -10000원 이다." {
        Hit(mutableSetOf(Card.CLOVER_J, Card.CLOVER_7)).earning(
            Dealer().firstTurn(
                setOf(
                    Card.CLOVER_Q,
                    Card.CLOVER_8
                )
            ), BettingAmount(10000)
        ) shouldBe -10000
    }

    "Hit 일 때 10000원을 베팅, 플레이어 J, 9, 딜러 Q, 8이면 상금은 +10000원 이다." {
        Hit(mutableSetOf(Card.CLOVER_J, Card.CLOVER_9)).earning(
            Dealer().firstTurn(
                setOf(
                    Card.CLOVER_Q,
                    Card.CLOVER_8
                )
            ), BettingAmount(10000)
        ) shouldBe 10000
    }

    "Hit 일 때 10000원을 베팅, 플레이어 J, 9, 딜러 Q, 9이면 상금은 0원 이다." {
        Hit(mutableSetOf(Card.CLOVER_J, Card.CLOVER_9)).earning(
            Dealer().firstTurn(
                setOf(
                    Card.CLOVER_Q,
                    Card.DIAMOND_9
                )
            ), BettingAmount(10000)
        ) shouldBe 0
    }
})
