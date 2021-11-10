package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {

    @Test
    fun name() {
        val person = introduce("김범준")

        assertThat(person.name).isEqualTo("김범준")
    }

    @Test
    fun company() {
        val person = introduce("김범준") {
            company("Kakao")
        }

        assertThat(person.name).isEqualTo("김범준")
        assertThat(person.company).isEqualTo("Kakao")
    }

    @Test
    fun skills() {
        val person = introduce("김범준") {
            company("Kakao")
            skills {
                soft("love kotlin")
                hard("love mountain")
            }
        }

        assertThat(person.name).isEqualTo("김범준")
        assertThat(person.company).isEqualTo("Kakao")
        assertThat(person.skills).hasSize(2)
        assertThat(person.skills[0].type).isEqualTo("soft")
        assertThat(person.skills[0].content).isEqualTo("love kotlin")
    }

    @Test
    fun languages() {
        val person = introduce("김범준") {
            company("Kakao")
            skills {
                soft("love kotlin")
                hard("love mountain")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        assertThat(person.name).isEqualTo("김범준")
        assertThat(person.company).isEqualTo("Kakao")
        assertThat(person.skills).hasSize(2)
        assertThat(person.skills[0].type).isEqualTo("soft")
        assertThat(person.skills[0].content).isEqualTo("love kotlin")
        assertThat(person.languages.size).isEqualTo(2)
        assertThat(person.languages[0].name).isEqualTo("Korean")
        assertThat(person.languages[0].score).isEqualTo(5)
    }
}

fun introduce(name: String, dsl: PersonDsl.() -> Unit = {}): Person {
    return PersonDsl(name).apply(dsl).toPerson()
}
