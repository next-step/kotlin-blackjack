package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayerTest {

    private lateinit var player: Player

    @BeforeEach
    fun setup() {
        player = Player.from(Name.valueOf("laco"), Cards.empty())
    }

    @Test
    fun `플레이어는 이름과 카드들을 가진다`() {
        assertAll(
            { assertThat(player.name).isEqualTo(Name.valueOf("laco")) },
            { assertThat(player.cards).isEqualTo(Cards.empty()) }
        )
    }

    @Test
    fun `플레이어는 카드를 받을 수 있다`() {
        assertThat(
            player
                .receive(Card(Denomination.ACE, Suit.HEART))
                .cards
        ).isEqualTo(Cards(listOf(Card(Denomination.ACE, Suit.HEART))))
    }

    @Test
    fun `플레이어는 조건에따라 카드를 계속해서 받을 수 있다`() {
        val cards = listOf(
            Card(Denomination.ACE, Suit.HEART),
            Card(Denomination.ACE, Suit.DIAMOND),
        )
        var count = 0
        assertThat(
            player
                .receiveWhile(next = { cards.getOrNull(count++) })
                .cards
        ).isEqualTo(Cards(cards))
    }

    @Test
    fun `점수가 21점보다 크면 패배한다`() {
        val actual = result(
            dealerScore = 20,
            Card(Denomination.TEN, Suit.HEART),
            Card(Denomination.TEN, Suit.CLOVER),
            Card(Denomination.FIVE, Suit.CLOVER)
        )
        assertThat(actual).isEqualTo(Result.LOSE)
    }

    @Test
    fun `딜러 점수가 21점보다 크면 승리한다`() {
        val actual = result(
            dealerScore = 25,
            Card(Denomination.TEN, Suit.HEART),
            Card(Denomination.FIVE, Suit.CLOVER)
        )
        assertThat(actual).isEqualTo(Result.WIN)
    }

    @Test
    fun `딜러보다 점수가 낮으면 패배한다`() {
        val actual = result(
            dealerScore = 20,
            Card(Denomination.TEN, Suit.HEART),
            Card(Denomination.FIVE, Suit.CLOVER)
        )
        assertThat(actual).isEqualTo(Result.LOSE)
    }

    @Test
    fun `딜러와 점수가 같으면 비긴다`() {
        val actual = result(
            dealerScore = 15,
            Card(Denomination.TEN, Suit.HEART),
            Card(Denomination.FIVE, Suit.CLOVER)
        )
        assertThat(actual).isEqualTo(Result.PUSH)
    }

    private fun result(dealerScore: Int, vararg card: Card): Result = player
        .copy(cards = Cards(card.toList()))
        .result(dealerScore)
}
