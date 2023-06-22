package blackjack.card

import domain.card.BlackjackCard
import domain.card.BlackjackCards
import domain.card.CardNumber
import domain.card.Suit
import domain.state.Burst
import domain.state.Hit
import domain.state.Stand
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class HitTest {

    @ParameterizedTest
    @MethodSource("getHitStateTestData")
    fun `플레이어는 Hit 상태에서 카드를 더 받았을 때 합이 21 이하라면 Hit 이라는 진행 상태가 된다`(
        cards: BlackjackCards,
        newCard: BlackjackCard,
    ) {
        val hit = Hit(cards)

        val newState = hit.draw(newCard)

        (newState is Hit) shouldBe true
    }

    @ParameterizedTest
    @MethodSource("getBurstStateTestData")
    fun `플레이어는 Hit 상태에서 카드를 더 받았을 때 합이 21 초과라면 Burst 라는 종료 상태`(
        cards: BlackjackCards,
        newCard: BlackjackCard,
    ) {
        val hit = Hit(cards)

        val newState = hit.draw(newCard)

        (newState is Burst) shouldBe true
    }

    @ParameterizedTest
    @MethodSource("getStandStateTestData")
    fun `플레이어는 힛(Hit) 상태에서 더 이상 카드를 받지 않는다고 하면 스탠드(Stand)라는 종료 상태`(cards: BlackjackCards) {
        val hit = Hit(cards)

        val newState = hit.stop()

        (newState is Stand) shouldBe true
    }

    companion object {
        @JvmStatic
        fun getHitStateTestData(): List<Arguments> = listOf(
            Arguments.of(
                BlackjackCards(listOf(BlackjackCard(suit = Suit.CLUB, number = CardNumber.KING))),
                BlackjackCard(suit = Suit.SPADE, number = CardNumber.FIVE),
            ),
            Arguments.of(
                BlackjackCards(
                    listOf(
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.ACE),
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.TWO),
                    ),
                ),
                BlackjackCard(suit = Suit.SPADE, number = CardNumber.FIVE),
            ),
            Arguments.of(
                BlackjackCards(
                    listOf(
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.TEN),
                        BlackjackCard(suit = Suit.HEART, number = CardNumber.TEN),
                    ),
                ),
                BlackjackCard(suit = Suit.SPADE, number = CardNumber.ACE),
            ),
        )

        @JvmStatic
        fun getBurstStateTestData(): List<Arguments> = listOf(
            Arguments.of(
                BlackjackCards(
                    listOf(
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.KING),
                        BlackjackCard(suit = Suit.HEART, number = CardNumber.KING),
                    ),
                ),
                BlackjackCard(suit = Suit.SPADE, number = CardNumber.FIVE),
            ),
            Arguments.of(
                BlackjackCards(
                    listOf(
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.ACE),
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.TWO),
                        BlackjackCard(suit = Suit.SPADE, number = CardNumber.SEVEN),
                        BlackjackCard(suit = Suit.SPADE, number = CardNumber.JACK),
                    ),
                ),
                BlackjackCard(suit = Suit.SPADE, number = CardNumber.TWO),
            ),
            Arguments.of(
                BlackjackCards(
                    listOf(
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.TEN),
                        BlackjackCard(suit = Suit.HEART, number = CardNumber.TEN),
                    ),
                ),
                BlackjackCard(suit = Suit.SPADE, number = CardNumber.TWO),
            ),
        )

        @JvmStatic
        fun getStandStateTestData(): List<Arguments> = listOf(
            Arguments.of(
                BlackjackCards(
                    listOf(
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.KING),
                        BlackjackCard(suit = Suit.HEART, number = CardNumber.KING),
                    ),
                ),
            ),
            Arguments.of(
                BlackjackCards(
                    listOf(
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.ACE),
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.TWO),
                        BlackjackCard(suit = Suit.SPADE, number = CardNumber.SEVEN),
                        BlackjackCard(suit = Suit.SPADE, number = CardNumber.JACK),
                    ),
                ),
            ),
            Arguments.of(
                BlackjackCards(
                    listOf(
                        BlackjackCard(suit = Suit.CLUB, number = CardNumber.TEN),
                        BlackjackCard(suit = Suit.HEART, number = CardNumber.TEN),
                    ),
                ),
            ),
        )
    }
}
