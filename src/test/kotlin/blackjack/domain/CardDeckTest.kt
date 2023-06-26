package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CardDeckTest {
    private lateinit var sut: CardDeck

    @BeforeEach
    fun setUp() {
        sut = ShuffledCardDeck()
    }

    @Test
    fun `초기에 카드는 52장이 남아있어야 한다`() {
        // given
        // when
        // then
        assertThat(sut.remainCardsCount()).isEqualTo(52)
    }

    @Test
    fun `카드를 뽑을 수 있다`() {
        // given
        // when
        // then
        sut.pickCard()
    }

    @Test
    fun `카드를 뽑은 수 만큼 남아있는 카드 수는 줄어들어야 한다`() {
        // given
        sut.pickCard()

        // when
        val remainCount = sut.remainCardsCount()

        // then
        assertThat(remainCount).isEqualTo(52 - 1)
    }

    @Test
    fun `52장의 카드를 모두 뽑을 수 있다`() {
        // given
        val pickCount = 52

        // when
        val pickedCard = mutableListOf<Card>().apply {
            repeat(pickCount) { sut.pickCard().also { add(it) } }
        }

        // then
        assertThat(pickedCard.size).isEqualTo(pickCount)
    }

    @Test
    fun `52장의 카드는 모두 서로 다르다`() {
        // given
        val pickCount = 52

        // when
        val pickedCard = mutableListOf<Card>().apply {
            repeat(pickCount) { sut.pickCard().also { add(it) } }
        }

        // then
        assertThat(pickedCard.toSet().size).isEqualTo(pickCount)
    }

    @Test
    fun `52장의 카드를 모두 뽑았다면 더 이상 카드를 뽑을 수 없다`() {
        // given
        val pickCount = 52

        // when
        repeat(pickCount) { sut.pickCard() }

        // then
        assertThrows(CardExceptions::class.java) {
            sut.pickCard()
        }
    }
}
