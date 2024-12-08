package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class GamblersTest : FreeSpec({
    "이름 목록을 입력받아 Gamblers를 만든다" {
        val names = listOf("pobi", "jason")
        val gamblers = Gamblers.from(names)

        gamblers shouldHaveSize 2
        gamblers.map { gambler -> gambler.name } shouldBe names
    }
})