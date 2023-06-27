package blackjack.bet

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.StringSpec

class BlackJackBetGameTest : StringSpec({
    "플레이어들이 배팅 금액을 충전한다." {
        val game = BlackJackBetGame(listOf("a", "b"))
        shouldNotThrowAny {
            game.chargePhase { 500 }
        }
    }

    "게임 시작에서 문제가 발생하지 않는다." {
        val game = BlackJackBetGame(listOf("a", "b"))
        shouldNotThrowAny {
            game.startGame()
        }
    }

    "각 딜러와 플레이어의 턴을 문제없이 진행한다." {
        val game = BlackJackBetGame(listOf("a", "b"))
        game.startGame()
        shouldNotThrowAny {
            game.processRound { false }
        }
    }

    "게임이 문제 없이 종료된다." {
        val game = BlackJackBetGame(listOf("a", "b"))
        game.startGame()
        shouldNotThrowAny {
            game.endGame()
        }
    }
})
