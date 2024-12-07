package blackjack.domain

import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CardTest {
    @MethodSource("카드 모양, 카드 숫자(잭,퀸,킹) 제공")
    @ParameterizedTest
    fun `모든 카드는 중복이 불가능 하도록 미리 생성하여 관리한다`(suit: Suit, rank: Rank) {
        val cardInstance1 = Card.getCard(suit, rank)
        val cardInstance2 = Card.getCard(suit, rank)
        cardInstance1 shouldBeSameInstanceAs cardInstance2
    }

    fun `카드 모양, 카드 숫자(잭,퀸,킹) 제공`(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(Suit.CLUBS, Rank.ACE),
            Arguments.of(Suit.HEARTS, Rank.NINE),
            Arguments.of(Suit.SPADES, Rank.JACK),
            Arguments.of(Suit.DIAMONDS, Rank.QUEEN),
        )
    }
}
