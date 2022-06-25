package blackjack

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

internal class BlackjackDealerTest : FunSpec({
    test("게임이 시작하면 모든 플레이어에게 두 장의 카드를 나눠준다.") {
        // given
        val players: List<Player> = listOf(Player(name = "홍길동"), Player(name = "둘리"))
        val sut = BlackjackDealer()

        // when
        sut.startTheGame(players)

        // then
        players.forEach() {
            it.handSize() shouldBe 2
        }
    }

    test("플레이어에게 카드를 한 장 준다.") {
        // given
        val player = Player(name = "홍길동")
        val sut = BlackjackDealer()

        // when
        sut.sendCard(player)

        // then
        player.handSize() shouldBe 1
    }
})
