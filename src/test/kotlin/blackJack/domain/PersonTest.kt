package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class PersonTest {
    @Test
    fun makePerson() {
        val person = Player("joohan")

        assertThat(person.hands.cards).hasSize(0)
        assertThat(person.name).isEqualTo("joohan")
    }

    @Test
    fun if_name_is_blank() {
        assertThatThrownBy {
            Player("")
        }.isInstanceOf(IllegalArgumentException::class.java).hasMessageContaining("이름은 없을수 없습니다.")
    }

    @Test
    fun add_card() {
        val person = Player("joohan")

        person.addCard(SPADE_ACE)

        assertThat(person.hands.cards).hasSize(1)
        assertThat(person.hands.getFirstCard()).isEqualTo(SPADE_ACE)
    }

    @Test
    fun get_total_score() {
        val person = Player("joohan")
        person.addCard(SPADE_JACK)
        person.addCard(SPADE_KING)

        val totalScore = person.getTotalScore()

        assertThat(totalScore).isEqualTo(20)
    }
}
