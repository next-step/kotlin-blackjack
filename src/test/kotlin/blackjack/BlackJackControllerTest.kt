package blackjack

import blackjack.domain.Player
import io.kotest.core.spec.style.StringSpec

class BlackJackControllerTest : StringSpec({
    "게임을 시작한다" {
        val players = arrayOf(Player("테스터1"), Player("테스터2"))
        BlackJackController.startRound(players)
    }
})
