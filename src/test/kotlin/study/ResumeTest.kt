package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import resume.Hard
import resume.Language
import resume.Resume
import resume.Soft

class ResumeTest {

    @Test
    fun name() {
        val person = Resume.introduce {
            name("김형준")
        }
        assertThat(person.name).isEqualTo("김형준")
    }

    @Test
    fun company() {
        val person = Resume.introduce {
            name("김형준")
            company("Kaoni")
        }
        assertThat(person.company).isEqualTo("Kaoni")
    }

    @Test
    fun soft() {
        val person = Resume.introduce {
            name("김형준")
            company("Kaoni")
            skills {
                soft("A passion for problem solving")
            }
        }
        assertThat(person.skills).contains(Soft("A passion for problem solving"))
    }

    @Test
    fun hardSkill() {
        val person = Resume.introduce {
            name("김형준")
            company("Kaoni")
            skills {
                soft("A passion for problem solving")
                soft("Spring")
                soft("JPA")
                soft("JAVA")
                soft("CleanCode")
                hard("Kotlin")
            }
        }
        assertThat(person.skills).contains(Hard("Kotlin"))
    }

    @Test
    fun language() {
        val person = Resume.introduce {
            name("김형준")
            company("Kaoni")
            skills {
                soft("A passion for problem solving")
                soft("Spring")
                soft("JPA")
                soft("JAVA")
                soft("CleanCode")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 4
            }
        }
        assertThat(person.languages).contains(Language("Korean", 5))
    }
}
