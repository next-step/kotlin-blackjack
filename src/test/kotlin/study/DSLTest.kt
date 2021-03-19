package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DSLTest {

    @Test
    fun name() {
        val person: Person = introduce {
            name("김수현")
        }
        assertThat(person.name).isEqualTo("김수현")
    }

    @Test
    fun company() {
        val person: Person = introduce {
            company("스포카")
        }
        assertThat(person.company).isEqualTo("스포카")
    }

    @Test
    fun softSkills() {
        val person: Person = introduce {
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
            }
        }
        assertThat(person.skills.toList()).containsExactlyInAnyOrder(
            Soft("A passion for problem solving"),
            Soft("Good communication skills")
        )
    }

    @Test
    fun hardSkills() {
        val person: Person = introduce {
            skills {
                hard("Kotlin")
            }
        }
        assertThat(person.skills.toList()).contains(Hard("Kotlin"))
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(person.languages.toList()).containsExactlyInAnyOrder(
            Language("Korean", 5),
            Language("English", 3)
        )
    }

    @Test
    fun all() {
        val person: Person = introduce {
            name("김수현")
            company("스포카")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        assertThat(person.name).isEqualTo("김수현")
        assertThat(person.company).isEqualTo("스포카")
        assertThat(person.skills.toList()).containsExactlyInAnyOrder(
            Soft("A passion for problem solving"),
            Soft("Good communication skills"),
            Hard("Kotlin")
        )
        assertThat(person.languages.toList()).containsExactlyInAnyOrder(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
}
