package blackjack.domain.player

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec

class PlayerGroupTest : FunSpec({
    test("블랙잭 게임의 최대 인원은 8명이다") {
        val players = listOf(
            Player("pobi"),
            Player("hojun"),
            Player("jason"),
            Player("jack"),
            Player("max"),
            Player("john"),
            Player("harry"),
            Player("honux")
        )
        shouldNotThrowAny { PlayerGroup(players) }
    }


    test("블랙잭 참가 인원이 8명을 초과하면 예외가 발생한다") {
        val players = listOf(
            Player("pobi"),
            Player("hojun"),
            Player("jason"),
            Player("jack"),
            Player("max"),
            Player("john"),
            Player("harry"),
            Player("honux"),
            Player("crong")
        )
        shouldThrow<IllegalArgumentException> { PlayerGroup(players) }
    }
})
