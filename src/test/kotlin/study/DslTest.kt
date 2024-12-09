package study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/*
introduce {
  name("박효재")
  company("우리집")
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
    @ValueSource(strings = ["홍길동", "홍"])
    @ParameterizedTest
    fun name(name: String) {
        val person =
            introduce {
                name(name)
            }
        person.name shouldBe name
    }

    @Test
    fun commpany() {
        val person =
            introduce {
                name("홍길동")
                company("활빈당")
            }
        person.company shouldBe "활빈당"
    }

    @Test
    fun skills() {
        // soft("A passion for problem solving")
        val person =
            introduce {
                name("홍길동")
                skills {
                    soft("A passion for problem solving")
                    soft("Good communication skills")
                    hard("Kotlin")
                }
            }

        person.skills[0] shouldBe Skill("soft", "A passion for problem solving")
        person.skills[1] shouldBe Skill("soft", "Good communication skills")
        person.skills[2] shouldBe Skill("hard", "Kotlin")
    }

    @Test
    fun infixLanguages() {
        val person =
            introduce {
                name("홍길동")
                languages {
                    "Korean" level 5
                    "English" level 3
                }
            }
        person.languages[0] shouldBe Language("Korean", 5)
        person.languages[1] shouldBe Language("English", 3)
    }
}
