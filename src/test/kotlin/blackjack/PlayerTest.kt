package blackjack

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PlayerTest : FunSpec({
    test("이름을 가진 플레이어를 생성한다.") {
        val player = Player("pobi")
        player.name shouldBe "pobi"
    }
})
