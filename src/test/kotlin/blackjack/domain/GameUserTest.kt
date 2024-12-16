package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameUserTest {
    @Test
    fun `오버포인트와 비교시 승리 함을 확인한다`() {
        val user1 = GameUser("A")
        user1.addCard(BlackJackCard(CardType.SPADE, CardInfo.CARD_10))
        user1.addCard(BlackJackCard(CardType.SPADE, CardInfo.CARD_6))
        val user2 = GameUser("B")
        user2.addCard(BlackJackCard(CardType.HEART, CardInfo.CARD_10))
        user2.addCard(BlackJackCard(CardType.HEART, CardInfo.CARD_9))
        user2.addCard(BlackJackCard(CardType.HEART, CardInfo.CARD_6))
        user1.comparePoints(user2) shouldBe true
        user2.comparePoints(user1) shouldBe false
    }

    @Test
    fun `둘 모두 오버포인트 시 패로 한다`() {
        val user1 = GameUser("A")
        user1.addCard(BlackJackCard(CardType.SPADE, CardInfo.CARD_10))
        user1.addCard(BlackJackCard(CardType.SPADE, CardInfo.CARD_9))
        user1.addCard(BlackJackCard(CardType.SPADE, CardInfo.CARD_6))

        val user2 = GameUser("B")
        user2.addCard(BlackJackCard(CardType.HEART, CardInfo.CARD_10))
        user2.addCard(BlackJackCard(CardType.HEART, CardInfo.CARD_9))
        user2.addCard(BlackJackCard(CardType.HEART, CardInfo.CARD_6))
        user1.comparePoints(user2) shouldBe false
        user2.comparePoints(user1) shouldBe false
    }

    @ParameterizedTest
    @ValueSource(strings = ["A", "B", "C"])
    fun `사용자를 생성한다`(name: String) {
        val user = GameUser(name)
        (user.getName() == name) shouldBe true
    }

    @ParameterizedTest
    @MethodSource("generateCardNumbers")
    fun `점수를 계산한다`(
        numbers: List<BlackJackCard>,
        points: Int,
    ) {
        val user = GameUser("홍길동")
        numbers.forEach {
            user.addCard(it)
        }
        user.getPoints() shouldBe points
    }

    @ParameterizedTest
    @MethodSource("generateBlackjackCard")
    fun `블랙잭이 맞는지 확인`(numbers: List<BlackJackCard>) {
        val user = GameUser("홍길동")
        numbers.forEach {
            user.addCard(it)
        }
        user.getPoints() shouldBe 21
    }

    private fun generateCardNumbers(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(
                listOf(
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_A),
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_2),
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_3),
                ),
                16,
            ),
            Arguments.of(
                listOf(
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_J),
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_Q),
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_K),
                ),
                30,
            ),
            Arguments.of(
                listOf(
                    BlackJackCard(CardType.CLOVER, CardInfo.CARD_A),
                    BlackJackCard(CardType.CLOVER, CardInfo.CARD_2),
                    BlackJackCard(CardType.CLOVER, CardInfo.CARD_3),
                ),
                16,
            ),
            Arguments.of(
                listOf(
                    BlackJackCard(CardType.DIAMOND, CardInfo.CARD_A),
                    BlackJackCard(CardType.DIAMOND, CardInfo.CARD_2),
                    BlackJackCard(CardType.DIAMOND, CardInfo.CARD_3),
                ),
                16,
            ),
            Arguments.of(
                listOf(
                    BlackJackCard(CardType.HEART, CardInfo.CARD_A),
                    BlackJackCard(CardType.HEART, CardInfo.CARD_2),
                    BlackJackCard(CardType.HEART, CardInfo.CARD_3),
                ),
                16,
            ),
            Arguments.of(
                listOf(
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_J),
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_Q),
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_10),
                ),
                30,
            ),
            Arguments.of(
                listOf(
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_A),
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_K),
                ),
                21,
            ),
        )
    }

    private fun generateBlackjackCard(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(
                listOf(
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_A),
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_K),
                ),
            ),
            Arguments.of(
                listOf(
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_5),
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_4),
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_3),
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_2),
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_A),
                    BlackJackCard(CardType.CLOVER, CardInfo.CARD_A),
                    BlackJackCard(CardType.CLOVER, CardInfo.CARD_5),
                ),
            ),
            Arguments.of(
                listOf(
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_A),
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_K),
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_J),
                ),
            ),
            Arguments.of(
                listOf(
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_A),
                    BlackJackCard(CardType.CLOVER, CardInfo.CARD_A),
                    BlackJackCard(CardType.SPADE, CardInfo.CARD_9),
                ),
            ),
        )
    }
}
