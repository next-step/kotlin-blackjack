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
    fun company() {
        val person: Person = introduce {
            name("이광재")
            company("SK플래닛")
        }
        Assertions.assertThat(person.name).isEqualTo("이광재")
        Assertions.assertThat(person.company).isEqualTo("SK플래닛")
    }
}

