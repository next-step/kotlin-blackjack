package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class KotlinDslTest {

    @Test
    internal fun person() {
        val person = introduce {
            name("Minjeong Kim")
            company("Kakao")
            skills {
                soft("spring")
                hard("kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        assertThat(person.name).isEqualTo("Minjeong Kim")
        assertThat(person.company).isEqualTo("Kakao")
        assertThat(person.skills.getSkills()).contains(Soft("spring"), Hard("kotlin"))
        assertThat(person.languages.getLanguages()).containsEntry("Korean", 5)
        assertThat(person.languages.getLanguages()).containsEntry("English", 3)
    }

    @Test
    internal fun skills() {
        val skills = Person().skills {
            soft("spring")
            hard("kotlin")
        }
        assertThat(skills.getSkills()).contains(Soft("spring"), Hard("kotlin"))
    }

    @Test
    internal fun languages() {
        val languages = Person().languages {
            "Korean" level 5
            "English" level 3
        }
        assertThat(languages.getLanguages()).containsEntry("Korean", 5)
        assertThat(languages.getLanguages()).containsEntry("English", 3)
    }
}
