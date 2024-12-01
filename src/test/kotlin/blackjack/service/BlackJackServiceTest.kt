import blackjack.service.BlackJackService
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackServiceTest : StringSpec({
    val blackjackService = BlackJackService()
    val players = blackjackService.createPlayers(listOf("kim", "da"))

    "플레이어 이름을 쉼표로 분리한다." {
        val names = blackjackService.splitPlayerNames("kim, da, bo, mi")
        names shouldBe listOf("kim", "da", "bo", "mi")
    }

    "플레이어를 생성한다." {
        players.size shouldBe 2
        players[0].name shouldBe "kim"
        players[1].name shouldBe "da"
    }

    "초기 카드를 분배한다." {
        blackjackService.distributeInitialCards(players)
        players.forEach { player ->
            player.cards.size shouldBe 2
        }
    }

    "빈 문자열을 분리하면 빈 리스트를 반환한다." {
        val names = blackjackService.splitPlayerNames("")
        names shouldBe emptyList()
    }

    "플레이어 이름에 공백이 포함된 경우 공백을 제거한다." {
        val names = blackjackService.splitPlayerNames(" kim , da , bo , mi")
        names shouldBe listOf("kim", "da", "bo", "mi")
    }
})
