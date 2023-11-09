package step1.study

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import step1.study.builder.introduce

/**
 * DslTest
 * */
class DslTest {
    @ValueSource(strings = ["홍길동", "김정식"])
    @ParameterizedTest
    fun name(name: String) {
        val person = introduce {
            name(name)
        }
        person.name shouldBe name
    }

    @ValueSource(strings = ["카카오", "네이버"])
    @ParameterizedTest
    fun company(company: String) {
        val person = introduce {
            company(company)
        }
        person.company shouldBe company
    }

    @Test
    fun skills() {
        val person = introduce {
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
        }
        person.skills[0].second shouldBe "A passion for problem solving"
        person.skills[1].second shouldBe "Good communication skills"
        person.skills[2].second shouldBe "Kotlin"
    }

    @ValueSource(strings = ["Kotlin", "Android"])
    @ParameterizedTest
    fun languages(language: String) {
        val person = introduce {
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        person.languages["Korean"] shouldBe 5
        person.languages["English"] shouldBe 3
    }
}
