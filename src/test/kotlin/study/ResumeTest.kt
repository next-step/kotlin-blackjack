package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/*
introduce {
  name("박재성")
  company("우아한형제들")
  skills {
    soft ("A passion for problem solving")
    soft ("Good communication skills")
    hard ("Kotlin")
  }
  languages {
    "Korean" level 5
    "English" level 3
  }
}
*/
class ResumeTest {

    @Test
    fun name() {
        val person: Person = introduce {
            name("박동욱")
        }

        assertThat(person.name).isEqualTo("박동욱")
    }

    @Test
    fun company() {
        val person: Person = introduce {
            name("박동욱")
            company("펀엔씨")
        }

        assertThat(person.name).isEqualTo("박동욱")
        assertThat(person.company).isEqualTo("펀엔씨")
    }

    @Test
    fun hard() {
        val person: Person = introduce {
            name("박동욱")
            company("펀엔씨")
            skills {
                hard("Kotlin")
            }
        }

        assertThat(person.name).isEqualTo("박동욱")
        assertThat(person.company).isEqualTo("펀엔씨")
        assertThat(person.skills.toList()).contains(Hard("Kotlin"))
    }

    @Test
    fun soft() {
        val person: Person = introduce {
            name("박동욱")
            company("펀엔씨")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }

        assertThat(person.name).isEqualTo("박동욱")
        assertThat(person.company).isEqualTo("펀엔씨")
        assertThat(person.skills.toList()).containsExactlyInAnyOrder(
            Soft("A passion for problem solving"),
            Soft("Good communication skills"),
            Hard("Kotlin")
        )
    }

    @Test
    fun languages() {
        val person: Person = introduce {
            name("박동욱")
            company("펀엔씨")
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

        assertThat(person.name).isEqualTo("박동욱")
        assertThat(person.company).isEqualTo("펀엔씨")
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
