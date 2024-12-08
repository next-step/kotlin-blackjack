package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class GamblersTest : FreeSpec({
    "이름 목록을 입력받아 Gamblers를 만든다" {
        val name1 = "pobi"
        val name2 = "jason"
        val gamblers = Gamblers(name1, name2)

        gamblers shouldHaveSize 2
        gamblers.map { gambler -> gambler.name } shouldBe listOf(name1, name2)
    }
})