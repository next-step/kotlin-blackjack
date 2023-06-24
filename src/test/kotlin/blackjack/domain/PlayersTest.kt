package blackjack.domain

import blackjack.domain.card.CardTest.Companion.SPADE_ACE
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class PlayersTest : FunSpec({

    context("init") {
        test("플레이어가 아무도 없으면 생성되지 않는다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { Players(listOf()) }
            exception.message shouldBe "플레이어는 최소 1명이 되어야한다."
        }
    }

    context("drawAllPlayer") {
        test("모든 플레이어에게 카들르 드로우한다.") {
            val players = Players(listOf(Player("a"), Player("b")))
            players.drawAllPlayer { SPADE_ACE }

            players.values[0].gameState.cards() shouldContain SPADE_ACE
            players.values[1].gameState.cards() shouldContain SPADE_ACE
        }
    }

    context("from") {
        test("플레이어의 이름을 받아 생성할 수 있다.") {
            val actual = Players.from(listOf("최진영"))
            actual.values shouldHaveSize 1
        }
    }
})
