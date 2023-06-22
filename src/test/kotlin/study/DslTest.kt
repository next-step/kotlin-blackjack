package study

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/*
introduce {
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
 */
class DslTest {

    @ValueSource(strings = ["홍길동", "한맹희"])
    @ParameterizedTest
    internal fun intruduce(value: String) {
        val person: Person = introduce {
            name(value)
        }
        person.name shouldBe value
        person.company.shouldBeNull()
    }

    @Test
    internal fun company() {
        val person = introduce {
            name("한맹희")
            company("카카오")
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
        person.name shouldBe "한맹희"
        person.company shouldBe "카카오"
        person.skills shouldContainExactly listOf(
            SoftSkill("A passion for problem solving"),
            SoftSkill("Good communication skills"),
            HardSkill("Kotlin"),
        )
        person.languages shouldContainExactly listOf(
            Language("Korean", 5),
            Language("English", 3),
        )
    }
}
