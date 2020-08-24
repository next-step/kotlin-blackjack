package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

val SPADE_ACE = Card(Shape.SPADE, Denomination.ACE)
val SPADE_KING = Card(Shape.SPADE, Denomination.KING)
val SPADE_QUEEN = Card(Shape.SPADE, Denomination.QUEEN)
val SPADE_JACK = Card(Shape.SPADE, Denomination.JACK)

class PersonTest {
    @Test
    fun makePerson() {
        val person = Person("joohan")

        assertThat(person.hands).hasSize(0)
        assertThat(person.name).isEqualTo("joohan")
    }

    @Test
    fun if_name_is_blank() {
        assertThatThrownBy {
            Person("")
        }.isInstanceOf(IllegalArgumentException::class.java).hasMessageContaining("이름은 없을수 없습니다.")
    }

    @Test
    fun add_card() {
        val person = Person("joohan")

        person.addCard(SPADE_ACE)

        assertThat(person.hands).hasSize(1)
        assertThat(person.hands[0]).isEqualTo(SPADE_ACE)
    }

    @Test
    fun get_total_score() {
        val person = Person("joohan")
        person.addCard(SPADE_JACK)
        person.addCard(SPADE_KING)

        val totalScore = person.getTotalScore()

        assertThat(totalScore).isEqualTo(20)
    }
}
