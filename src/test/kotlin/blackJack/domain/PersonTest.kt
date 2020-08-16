package blackJack.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PersonTest {
    @Test
    fun give_card() {
        val person = Player("test")

        person.addCard(Card(Shape.SPADE, Denomination.TEN))

        Assertions.assertThat(person.hands).hasSize(1)
        Assertions.assertThat(person.hands[0]).isEqualTo(Card(Shape.SPADE, Denomination.TEN))
    }

    @Test
    fun get_score() {
        val person = Player("test")
        person.addCard(Card(Shape.SPADE, Denomination.TEN))
        person.addCard(Card(Shape.SPADE, Denomination.TEN))

        Assertions.assertThat(person.getTotalScore()).isEqualTo(20)
    }

    @Test
    fun player_is_over_21() {
        val person = Player("test")
        person.addCard(Card(Shape.SPADE, Denomination.TEN))
        person.addCard(Card(Shape.SPADE, Denomination.TEN))
        person.addCard(Card(Shape.SPADE, Denomination.TEN))

        Assertions.assertThat(person.isBust()).isTrue()
    }
}
