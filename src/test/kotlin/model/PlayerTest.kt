package model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({

    "플레이어 객체를 생성하면, 이름과 카드를 가진다" {
        // when
        val player = Player("나잘함")

        // then
        player.name shouldBe "나잘함"
        player.cards.shouldNotBeNull()
    }
})
