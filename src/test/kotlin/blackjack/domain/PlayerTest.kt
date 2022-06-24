package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PlayerTest {

    @Test
    fun `플레이어는 이름과 카드 정보를 갖는다`() {
        Player(
            name = "justin",
            cards = listOf(Card(Suit.SPADE, Denomination.ACE))
        )
    }

    @Test
    fun `플레이어는 카드 정보를 추가할 수 있다`() {
        val player = Player(
            name = "justin",
            cards = listOf(Card(Suit.SPADE, Denomination.ACE))
        )
        player.addCard(listOf(Card(Suit.SPADE, Denomination.ACE)))

        assertThat(player.cards).contains(Card(Suit.SPADE, Denomination.ACE))
    }

    @Test
    fun `ScoreCalculator 는 카드 목록을 전달 받아 total 점수를 계산한다`() {
        assertThat(
            Player(
                cards = listOf(
                    Card(Suit.DIA, Denomination.FIVE),
                    Card(Suit.DIA, Denomination.SIX),
                    Card(Suit.DIA, Denomination.SEVEN),
                )
            ).score()
        ).isEqualTo(Score(18))
    }

    @Test
    fun `King, Queen, Jack은  10으로 계산한다`() {
        assertThat(Player(cards = listOf(Card(Suit.DIA, Denomination.FIVE), Card(Suit.DIA, Denomination.KING))).score()).isEqualTo(Score(15))
        assertThat(Player(cards = listOf(Card(Suit.DIA, Denomination.FIVE), Card(Suit.DIA, Denomination.QUEEN))).score()).isEqualTo(Score(15))
        assertThat(Player(cards = listOf(Card(Suit.DIA, Denomination.FIVE), Card(Suit.DIA, Denomination.JACK))).score()).isEqualTo(Score(15))
    }

    @ParameterizedTest
    @MethodSource("over21CardList")
    fun `누적 점수 + Ace 합이 21이 넘을것 같으면 Ace는 1로 계산한다`(expectScore: Score, cards: List<Card>) {
        assertThat(Player(cards = cards).score()).isEqualTo(expectScore)
    }

    @Test
    fun `누적 점수 + Ace 합이 21을 넘지 않을 경우 Ace는 11로 계산한다`() {
        assertThat(Player(cards = listOf(Card(Suit.DIA, Denomination.KING), Card(Suit.DIA, Denomination.ACE))).score()).isEqualTo(Score(21))
    }

    @ParameterizedTest
    @MethodSource("cardList")
    fun `Score 점수에 따라 다른 카드를 받을 수 있는지 확인`(expect: Boolean, cads: List<Card>) {
        assertThat(Player(cards = cads).canReceive()).isEqualTo(expect)
    }

    companion object {
        @JvmStatic
        fun over21CardList() = listOf(
            Arguments.of(Score(21), listOf(Card(Suit.DIA, Denomination.KING), Card(Suit.DIA, Denomination.QUEEN), Card(Suit.DIA, Denomination.ACE))),
            Arguments.of(Score(15), listOf(Card(Suit.DIA, Denomination.KING), Card(Suit.DIA, Denomination.FOUR), Card(Suit.DIA, Denomination.ACE))),
            Arguments.of(
                Score(20),
                listOf(Card(Suit.DIA, Denomination.KING), Card(Suit.DIA, Denomination.FOUR), Card(Suit.DIA, Denomination.FIVE), Card(Suit.DIA, Denomination.ACE))
            ),
        )

        @JvmStatic
        private fun cardList() = listOf(
            Arguments.of(true, listOf(Card(Suit.DIA, Denomination.KING), Card(Suit.DIA, Denomination.TWO), Card(Suit.DIA, Denomination.THREE))),
            Arguments.of(true, listOf(Card(Suit.DIA, Denomination.KING), Card(Suit.DIA, Denomination.QUEEN))),
            Arguments.of(false, listOf(Card(Suit.DIA, Denomination.KING), Card(Suit.DIA, Denomination.QUEEN), Card(Suit.DIA, Denomination.JACK))),
        )
    }
}
