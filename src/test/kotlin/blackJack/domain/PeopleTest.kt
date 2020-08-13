package blackJack.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PeopleTest {
    @Test
    fun give_card() {
        val people = People("test")

        people.addCard(Card(Shape.SPADE, Denomination.TEN))

        Assertions.assertThat(people.hands).hasSize(1)
        Assertions.assertThat(people.hands[0]).isEqualTo(Card(Shape.SPADE, Denomination.TEN))
    }

    @Test
    fun get_score() {
        val people = People("test")
        people.addCard(Card(Shape.SPADE, Denomination.TEN))
        people.addCard(Card(Shape.SPADE, Denomination.TEN))

        Assertions.assertThat(people.getTotalScore()).isEqualTo(20)
    }

    @Test
    fun player_is_over_21() {
        val people = People("test")
        people.addCard(Card(Shape.SPADE, Denomination.TEN))
        people.addCard(Card(Shape.SPADE, Denomination.TEN))
        people.addCard(Card(Shape.SPADE, Denomination.TEN))

        Assertions.assertThat(people.isBust()).isTrue()
    }
}
