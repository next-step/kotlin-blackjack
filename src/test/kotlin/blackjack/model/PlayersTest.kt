package blackjack.model

import blackjack.model.blackjack.BlackJackStatus
import blackjack.model.player.Players
import blackjack.model.player.playable.impl.Player
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class PlayersTest : StringSpec({

    "플레이어들의 이름은 중복을 허용하지 않는다. 중복시 throw IllegalArgumentException" {
        shouldThrow<IllegalArgumentException> {
            Players(Player("hana", status = BlackJackStatus.ALIVE), Player("hana", status = BlackJackStatus.ALIVE))
        }
    }

    "플레이어들의 이름이 중복되지 않으면 정상 동작 해야한다" {
        shouldNotThrow<IllegalArgumentException> {
            Players(Player("hana", status = BlackJackStatus.ALIVE), Player("numa", status = BlackJackStatus.ALIVE))
        }
    }
})
