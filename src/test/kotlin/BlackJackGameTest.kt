import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : StringSpec({
    "카드를 뽑을 플레이어를 반환한다." {
        val sut = BlackJackGame(
            listOf(Player("테스트1"), Player("테스트2"))
        )
        val player1 = sut.drawPlayer()
        player1.name shouldBe "테스트1"

        val player2 = sut.drawPlayer()
        player2.name shouldBe "테스트2"
    }
})