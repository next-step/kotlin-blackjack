package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class PersonTest {
    @Test
    fun add_card() {
        val person = Player("test")

        person.addCard(Card(Shape.SPADE, Denomination.TEN))

        assertThat(person.hands).hasSize(1)
        assertThat(person.hands[0]).isEqualTo(Card(Shape.SPADE, Denomination.TEN))
    }

    @Test
    fun get_score() {
        val person = Player("test")
        val cards = listOf(Card(Shape.SPADE, Denomination.TEN), Card(Shape.SPADE, Denomination.TEN))

        assertThat(person.getTotalScore(cards)).isEqualTo(20)
    }

    @Test
    fun player_is_over_21() {
        val person = Player("test")

        assertThat(person.isBust(22)).isTrue()
    }

    @Test
    fun player_name_is_blank() {
        assertThatThrownBy {
            Player("")
        }.isInstanceOf(IllegalArgumentException::class.java).hasMessageContaining("이름은 없을수 없습니다.")
    }

    @Test
    fun person_get_hands_size() {
        val person = Player("Test")
        person.addCard(Card(Shape.SPADE, Denomination.TEN))
        person.addCard(Card(Shape.SPADE, Denomination.TEN))

        val size = person.getHandsSize()

        assertThat(size).isEqualTo(2)
    }
}
