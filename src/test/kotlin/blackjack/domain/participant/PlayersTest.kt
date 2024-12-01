package blackjack.domain.participant

import blackjack.domain.betting.Betting
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class PlayersTest : StringSpec({
    "블랙잭 플레이어는 1~6명 이어야 합니다." {
        shouldThrow<IllegalArgumentException> {
            Participants(
                listOf(
                    Player(name = "1", betting = Betting(100)),
                    Player(name ="2", betting = Betting(100)),
                    Player(name = "3", betting = Betting(100)),
                    Player(name ="4", betting = Betting(100)),
                    Player(name = "5", betting = Betting(100)),
                    Player(name ="6", betting = Betting(100)),
                    Player(name = "7", betting = Betting(100)),
                ),
            )
        }

        shouldThrow<IllegalArgumentException> { Participants(emptyList()) }
    }
})
