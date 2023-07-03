package blackjack

import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    internal fun `플레이어 입력은 , 로 구분한다`() {
        val actual = Player.of("정석준, Dino")
        val expected = listOf(Player("정석준"), Player("Dino"))
        actual shouldBe expected
    }

    @Test
    internal fun `플레이어는 받은 카드를 가지고 있는다`() {
        val player = Player("정석준")
        player.receiveCard(Card.of("A스페이드"))
        player.receiveCard(Card.of("2스페이드"))
        player.cardList shouldBe listOf(Card.of("A스페이드"), Card.of("2스페이드"))
    }

    @Test
    internal fun `플레이어가 가지고 있는 카드는 score로 표현할 수 있다`() {
        data class TestCase(
            val player: Player,
            val expected: Int,
        )

        val testCaseList = listOf(
            TestCase(
                player = Player("정석준").apply {
                    receiveCard(Card.of("10스페이드"))
                    receiveCard(Card.of("10스페이드"))
                },
                expected = 20
            ),
            TestCase(
                player = Player("정석준").apply {
                    receiveCard(Card.of("3스페이드"))
                    receiveCard(Card.of("2스페이드"))
                },
                expected = 5
            ),
            )
        testCaseList.shouldForAll {
            it.player.score shouldBe it.expected
        }

    }

    @Test
    internal fun `A는 1또는 11로 계산 된다`() {
        data class TestCase(
            val player: Player,
            val expected: Int,
        )

        val testCaseList = listOf(
            TestCase(
                player = Player("정석준").apply {
                    receiveCard(Card.of("10스페이드"))
                    receiveCard(Card.of("10스페이드"))
                    receiveCard(Card.of("A스페이드"))
                },
                expected = 21
            ),
            TestCase(
                player = Player("정석준").apply {
                    receiveCard(Card.of("10스페이드"))
                    receiveCard(Card.of("A스페이드"))
                },
                expected = 21
            ),
            TestCase(
                player = Player("정석준").apply {
                    receiveCard(Card.of("10스페이드"))
                    receiveCard(Card.of("A스페이드"))
                    receiveCard(Card.of("A스페이드"))
                },
                expected = 12
            ),
            )

        testCaseList.shouldForAll {
            it.player.score shouldBe it.expected
        }
    }

    @Test
    internal fun `플레이어가 가지고 있는 카드의 합이 21 미만이면 카드를 더 받을 수 있다`() {
        val player = Player("정석준").apply {
            receiveCard(Card.of("10스페이드"))
            receiveCard(Card.of("10스페이드"))
        }
        player.canReceiveCard shouldBe true
    }

    @Test
    internal fun `플레이어가 가지고 있는 카드의 합이 21 이상이면 카드를 더 받을 수 없다`() {
        val player = Player("정석준").apply {
            receiveCard(Card.of("10스페이드"))
            receiveCard(Card.of("10스페이드"))
            receiveCard(Card.of("A스페이드"))
        }
        player.canReceiveCard shouldBe false
    }


}
