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
})
