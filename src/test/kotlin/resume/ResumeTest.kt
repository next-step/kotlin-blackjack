import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import resume.Language
import resume.Person
import resume.Skill.Hard
import resume.Skill.Soft
import resume.introduce

class ResumeTest {

    @Test
    fun `이력서 테스트`() {
        val person: Person = introduce {
            name("전인표")
            company("k사")
            skills {
                soft("Java")
                soft("Android")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        assertThat(person.name).isEqualTo("전인표")
        assertThat(person.company).isEqualTo("k사")
        assertThat(person.languages).containsOnly(Language("Korean", 5), Language("English", 3))
        assertThat(person.skills).containsOnly(Soft("Java"), Soft("Android"), Hard("Kotlin"))
    }
}
