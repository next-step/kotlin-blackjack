package study

import dsl.Person
import dsl.introduce
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

/*
introduce {
  name("이광재")
  company("SK플래닛")
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
 */

class DslTest {

    @Test
    fun nameAndCompany() {
        val person: Person = introduce {
            name("이광재")
            company("SK플래닛")
        }
        Assertions.assertThat(person.name).isEqualTo("이광재")
        Assertions.assertThat(person.company).isEqualTo("SK플래닛")
    }

    @Test
    fun nameAndCompanySkills() {
        val person: Person = introduce {
            name("이광재")
            company("SK플래닛")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        Assertions.assertThat(person.name).isEqualTo("이광재")
        Assertions.assertThat(person.company).isEqualTo("SK플래닛")
        Assertions.assertThat(person.skills.soft.size).isEqualTo(2)
        Assertions.assertThat(person.skills.hard.size).isEqualTo(1)
        Assertions.assertThat(person.skills.hard[0].skill).isEqualTo("Kotlin")
        Assertions.assertThat(person.skills.soft[0].skill).isEqualTo("A passion for problem solving")
        Assertions.assertThat(person.skills.soft[1].skill).isEqualTo("Good communication skills")
    }
}

