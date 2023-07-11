package study

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
introduce {
name("안태선")
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
 */
class DslTest {
    @ValueSource(strings = ["안태선", "제이슨"])
    @ParameterizedTest
    fun introduce(value: String) {
        val person: Person = introduce {
            name(value)
        }
        person.name shouldBe value
        person.company.shouldBeNull()
    }

    @Test
    fun company() {
        val person = introduce {
            name("안태선")
            company("카카오")
        }
        person.name shouldBe "안태선"
        person.company shouldBe "카카오"
    }

    @Test
    fun skills() {
        val person = introduce {
            name("안태선")
            company("카카오")
            skills {
                soft("소프트")
                hard("하드")
            }
        }
        with(person.skills) {
            softSkills shouldBe listOf("소프트")
            hardSkills shouldBe listOf("하드")
        }
    }

    @Test
    fun languages() {
        val person = introduce {
            name("안태선")
            company("카카오")
            skills {
                soft("소프트")
                hard("하드")
            }
            languages {
                "korean" level 5
                "english" level 4
            }
        }
        person.languages shouldBe listOf(
            Language("korean", 5),
            Language("english", 4)
        )
    }

    @Test
    fun noParameter() {
        shouldNotThrowAny {
            introduce {
            }
        }
    }
}
