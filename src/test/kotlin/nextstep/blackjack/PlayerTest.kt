package nextstep.blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import nextstep.blackjack.Card.CLOVER_ACE
import nextstep.blackjack.Card.CLOVER_JACK
import nextstep.blackjack.Card.CLOVER_TEN
import nextstep.blackjack.Card.DIAMOND_ACE
import nextstep.blackjack.Card.HEART_ACE
import nextstep.blackjack.Card.HEART_NINE
import nextstep.blackjack.Card.HEART_TEN
import nextstep.blackjack.Card.SPADE_ACE

class PlayerTest : StringSpec({

    "플레이어는 N장의 카드를 가진다." {
        val player = Player(setOf(SPADE_ACE, HEART_ACE, DIAMOND_ACE, CLOVER_ACE))

        player.cards shouldBe setOf(SPADE_ACE, HEART_ACE, DIAMOND_ACE, CLOVER_ACE)
    }

    "플레이어는 가진 카드의 총합 점수를 계산할 수 있다.2" {
        listOf(
            setOf(CLOVER_JACK, CLOVER_ACE) to 21,
            setOf(CLOVER_JACK, HEART_NINE, DIAMOND_ACE) to 20,
            setOf(CLOVER_JACK, HEART_NINE, DIAMOND_ACE, SPADE_ACE) to 21,
            setOf(CLOVER_ACE, SPADE_ACE) to 12,
            setOf(CLOVER_ACE, SPADE_ACE, HEART_NINE) to 21,
            setOf(CLOVER_ACE, SPADE_ACE, HEART_TEN) to 12,
        ).forAll { (cards: Set<Card>, totalPoint: Int) ->
            val player = Player(cards)

            player.calculateTotalPoint() shouldBe totalPoint
        }
    }

    "플레이어는 가진 카드의 총합이 21점이 이하라면 언제든지 1장의 카드를 추가로 뽑을 수 있다." {
        val player = Player(setOf(CLOVER_JACK, CLOVER_TEN))

        player.hit(HEART_ACE)

        player.cards shouldBe setOf(CLOVER_JACK, CLOVER_TEN, HEART_ACE)
    }
})
