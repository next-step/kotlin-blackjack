package blackjack.domain

import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.matchers.equals.shouldBeEqual
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlayerTest {
    @Test
    fun `플레이어가 카드를 추가할 때 21을 넘어서면 즉시 패배한다`() {
        val player = Player(PlayerName("pablo"), MutableCards(mutableListOf()))
        player.addCard(Card(Suit.SPADES, Rank.TWO))
        player.addCard(Card(Suit.HEARTS, Rank.QUEEN))
        shouldThrowWithMessage<IllegalArgumentException>(message = "pablo Bust! / 2스페이드, Q하트, Q다이아몬드") {
            player.addCard(Card(Suit.DIAMONDS, Rank.QUEEN))
        }
    }

    @Test
    fun `플레이어가 가진 카드를 문자열로 나열할 수 있다`() {
        val player = Player(PlayerName("pablo"), MutableCards(mutableListOf()))
        player.addCard(Card(Suit.SPADES, Rank.TWO))
        player.addCard(Card(Suit.HEARTS, Rank.QUEEN))
        player.cardsToString() shouldBeEqual "2스페이드, Q하트"
    }

    @MethodSource("카드와 카드 합산 제공")
    @ParameterizedTest
    fun `플레이어가 가진 카드의 합계를 계산할 수 있다`(card1: Card, card2: Card, card3: Card, sum: Int) {
        val player = Player(PlayerName("pablo"), MutableCards(mutableListOf()))
        player.addCard(card1)
        player.addCard(card2)
        player.addCard(card3)
        player.sumCardValues() shouldBeEqual sum
    }

    fun `카드와 카드 합산 제공`(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(Card(Suit.CLUBS, Rank.ACE), Card(Suit.SPADES, Rank.ACE), Card(Suit.DIAMONDS, Rank.ACE), 13),
            Arguments.of(Card(Suit.HEARTS, Rank.JACK), Card(Suit.SPADES, Rank.QUEEN), Card(Suit.DIAMONDS, Rank.ACE), 21),
            Arguments.of(Card(Suit.SPADES, Rank.TWO), Card(Suit.DIAMONDS, Rank.TWO), Card(Suit.DIAMONDS, Rank.TWO), 6),
        )
    }
}
