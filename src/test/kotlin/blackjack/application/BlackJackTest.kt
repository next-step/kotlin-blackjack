package blackjack.application

import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.player.vo.Name
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

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

    "블랙젝에 참가한 플레이어들의 현재 상태값을 전달 받을수 있다." {
        val setupBlackJackStatus = blackJack().statuses
        val playerStatuses = setupBlackJackStatus.statuses

        playerStatuses.size shouldBe 2

        playerStatuses.forAll {
            it.cards.size shouldBe 0
        }
    }

    "블랙젝에 참가한 플레이어들의 결과를 알수 있다." {
        val setupBlackJackResults = blackJack().results
        val playerResults = setupBlackJackResults.results

        playerResults.size shouldBe 2

        playerResults.forAll {
            it.score.value shouldBe 0
        }
    }
}) {
    companion object {
        fun players(): Players = Players(listOf(Player.sit(Name("dean")), Player.sit(Name("dane"))))

        fun blackJack(): BlackJack = BlackJack.setup(players())
    }
}
