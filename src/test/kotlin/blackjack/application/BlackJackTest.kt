package blackjack.application

import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.player.vo.Name
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll

class BlackJackTest : StringSpec({
    "블랙젝 게임을 생성할수 있다." {
        shouldNotThrow<Throwable> { BlackJack.setup(players()) }
    }

    "블랙젝 게임 준비를 할수 있다." {
        shouldNotThrow<Throwable> { blackJack().ready() }
    }

    "블랙젝 게임 실행을 할수 있다." {
        players().players.forAll {
            shouldNotThrow<Throwable> { blackJack().play(it) }
        }
    }
}) {
    companion object {
        fun players(): Players = Players(listOf(Player.sit(Name("dean")), Player.sit(Name("dane"))))

        fun blackJack(): BlackJack = BlackJack.setup(players())
    }
}
