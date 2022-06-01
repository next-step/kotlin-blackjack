package blackjack.domain

import blackjack.domain.player.Name
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec

class BlackJackGameTest : StringSpec({
    "블랙젝 게임을 생성할수 있다." {
        shouldNotThrow<Throwable> { BlackJackGame.setup(players) }
    }
}) {
    companion object {
        val players = Players(listOf(Player.sit(Name("dean")), Player.sit(Name("dane"))))
    }
}
