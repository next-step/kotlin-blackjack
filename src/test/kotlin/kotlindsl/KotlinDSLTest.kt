package kotlindsl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class KotlinDSLTest {
    @Test
    internal fun `이름을 입력한다`() {
        val person: Person = introduce {
            name("성재우")
        }

        assertThat(person.name).isEqualTo("성재우")
    }

    @Test
    internal fun `회사를 입력한다`() {
        val person: Person = introduce {
            company("회사")
        }

        assertThat(person.company).isEqualTo("회사")
    }

    @Test
    internal fun `스킬을 입력한다`() {
        val person: Person = introduce {
            skills {
                hard("Kotlin")
                soft("Java")
            }
        }

        assertThat(person.skills.skills).containsExactlyInAnyOrder(
            Hard("Kotlin"),
            Soft("Java")
        )
    }

    @Test
    internal fun `언어를 입력한다`() {
        val person: Person = introduce {
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        assertThat(person.languages.languages).containsExactlyInAnyOrder(
            Language("Korean", 5),
            Language("English", 3)
        )
    }

    @Test
    internal fun `전체를 테스트한다`() {
        val person: Person = introduce {
            name("박재성")
            company("우아한형제들")
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

        assertThat(person.name).isEqualTo("박재성")
        assertThat(person.company).isEqualTo("우아한형제들")
        assertThat(person.skills.skills).containsExactlyInAnyOrder(
            Soft("A passion for problem solving"),
            Soft("Good communication skills"),
            Hard("Kotlin"),
        )
        assertThat(person.languages.languages).containsExactlyInAnyOrder(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
}
