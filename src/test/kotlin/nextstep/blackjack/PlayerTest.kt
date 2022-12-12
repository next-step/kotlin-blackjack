package nextstep.blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import nextstep.blackjack.Card.*

class PlayerTest : StringSpec({

    "플레이어는 N장의 카드를 가진다." {
        val player = Player(setOf(SPADE_ACE, HEART_ACE, DIAMOND_ACE, CLOVER_ACE))

        player.cards shouldBe setOf(SPADE_ACE, HEART_ACE, DIAMOND_ACE, CLOVER_ACE)
    }
})
