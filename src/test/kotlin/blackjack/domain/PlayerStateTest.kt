package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class PlayerStateTest : FunSpec({
    context("isDone()") {
        test("플레이어 게임 진행 여부를 반환한다.") {
            table(
                headers("state", "expectedResult"),
                row(PlayerState.Play.Idle, false),
                row(PlayerState.Play.Hit, false),
                row(PlayerState.Done.Stay, true),
                row(PlayerState.Done.BlackJack, true),
                row(PlayerState.Done.Burst, true),
            ).forAll { playerState, expectedResult ->
                playerState.isDone() shouldBe expectedResult
            }
        }
    }
})
