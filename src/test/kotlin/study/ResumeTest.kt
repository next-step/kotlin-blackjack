package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResumeTest {

    @Test
    fun name() {
        val person: Person = introduce {
            name { "이기원" }
        }

        assertThat(person.name).isEqualTo("이기원")
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name { "이기원" }
            company { "중견기업" }
        }
        assertThat(person.name).isEqualTo("이기원")
        assertThat(person.company).isEqualTo("중견기업")
    }

    @Test
    fun hardSkill() {
        val person: Person = introduce {
            name { "이기원" }
            company { "중견기업" }
            skills {
                hard("kotlin")
            }
        }
        assertThat(person.name).isEqualTo("이기원")
        assertThat(person.company).isEqualTo("중견기업")
        assertThat(person.skills.toList()).contains(Hard("kotlin"))
    }

    @Test
    fun soft() {
        val person: Person = introduce {
            name { "이기원" }
            company { "중견기업" }
            skills {
                hard("kotlin")
                soft("A passion for problem solving")
                soft("Good communications Skill")
            }
        }
        assertThat(person.name).isEqualTo("이기원")
        assertThat(person.company).isEqualTo("중견기업")
        assertThat(person.skills.toList()).containsExactlyInAnyOrder(
            Hard("kotlin"),
            Soft("A passion for problem solving"),
            Soft("Good communications Skill")
        )
    }

    @Test
    fun language() {
        val person: Person = introduce {
            name { "이기원" }
            company { "중견기업" }
            skills {
                hard("kotlin")
                soft("A passion for problem solving")
                soft("Good communications Skill")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(person.name).isEqualTo("이기원")
        assertThat(person.company).isEqualTo("중견기업")
        assertThat(person.skills.toList()).containsExactlyInAnyOrder(
            Hard("kotlin"),
            Soft("A passion for problem solving"),
            Soft("Good communications Skill")
        )

        assertThat(person.languages.toList()).containsExactlyInAnyOrder(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
}
