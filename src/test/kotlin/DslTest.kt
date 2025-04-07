import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {

    @ParameterizedTest
    @ValueSource(strings = ["Johnny", "Jack"])
    fun introduce(value: String) {
        val person = introduce {
            name(value)
        }
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun company() {
        val person = introduce {
            name("JK")
            company("Delivery Hero")
        }
        assertThat(person.name).isEqualTo("JK")
        assertThat(person.company).isEqualTo("Delivery Hero")
    }

    @ParameterizedTest
    @ValueSource(strings = ["Teamwork", "Communication", "Problem Solving"])
    fun `soft skill`(softSkill: String) {
        val person = introduce {
            name("JK")
            skills {
                soft(softSkill)
            }
        }

        assertTrue(person.skills?.soft?.contains(softSkill) == true)
    }

    @ParameterizedTest
    @ValueSource(strings = ["Kotlin", "Java", "Spring"])
    fun `hard skill`(hardSkill: String) {
        val person = introduce {
            name("Jason")
            skills {
                hard(hardSkill)
            }
        }

        assertTrue(person.skills?.hard?.contains(hardSkill) == true)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 3, 5])
    fun `languages level`(num: Int) {
        val person = introduce {
            name("Jason")
            languages {
                "Korean" level num
            }
        }

        assertThat(person.languages["Korean"]).isEqualTo(num)
    }
}